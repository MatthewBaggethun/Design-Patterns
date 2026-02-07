package structural.decorator.httpPipeline.Decorators;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;

import structural.decorator.httpPipeline.HttpHandler.HttpHandler;
import structural.decorator.httpPipeline.HttpHandler.HttpHandlerDecorator;
import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;
import structural.decorator.httpPipeline.Strategy.Retry.RetryPolicy;

/**
 * A decorator that adds retry logic to an HttpHandler. It uses a RetryPolicy to
 * determine how many times to retry a failed HTTP request and the backoff
 * duration between retries. The retry logic is applied only to idempotent HTTP
 * methods (e.g., GET, PUT, DELETE) to avoid unintended side effects. If a
 * request fails and the retry conditions are met, the decorator will wait for
 * the specified backoff duration before attempting to execute the request
 * again. This process will repeat until the maximum number of retries is
 * reached or the request succeeds. The retry logic is implemented for both
 * synchronous and asynchronous execution of HTTP requests.
 * 
 * @see HttpHandler
 * @see HttpHandlerDecorator
 * @see Request
 * @see Response
 * @see RetryPolicy
 */
public class RetryDecorator extends HttpHandlerDecorator {

	private final RetryPolicy retryPolicy;

	/**
	 * Constructs a RetryDecorator that wraps the given HttpHandler and uses the
	 * specified RetryPolicy for retrying failed HTTP requests.
	 * 
	 * @param decoratedHandler The HttpHandler to be decorated with retry logic.
	 * @param retryPolicy      The RetryPolicy that defines the retry behavior for
	 *                         failed HTTP requests. Must not be null.
	 * @throws IllegalArgumentException if retryPolicy is null.
	 */
	public RetryDecorator(HttpHandler decoratedHandler, RetryPolicy retryPolicy) {
		super(decoratedHandler);
		this.retryPolicy = retryPolicy;
	}

	@Override
	public Response execute(Request request) {
		return executeWithRetry(request, 0);
	}

	@Override
	public CompletableFuture<Response> executeAsync(Request request) {
		return executeAsyncWithRetry(request, 0);
	}

	/**
	 * Executes the given HTTP request with retry logic. If the request fails and
	 * the retry conditions are met, it will wait for the specified backoff duration
	 * before attempting to execute the request again. This process will repeat
	 * until the maximum number of retries is reached or the request succeeds.
	 *
	 * @param request The HTTP request to be executed. Must not be null.
	 * @param attempt The current attempt number, starting from 0. Used internally
	 *                to track the number of retries.
	 * @return The HTTP response if the request succeeds within the allowed number
	 *         of retries.
	 * @throws RuntimeException if the request fails and the retry conditions are
	 *                          not met (e.g., non-idempotent method or max retries
	 *                          exceeded).
	 */
	private Response executeWithRetry(Request request, int attempt) {
		try {
			return super.execute(request);
		} catch (RuntimeException e) {
			if (attempt < retryPolicy.getMaxRetries() && request.getHttpMethod().isIdempotent()) {
				sleep(retryPolicy.getBackoffStrategy().nextDelay(attempt));
				return executeWithRetry(request, attempt + 1);
			} else {
				throw e;
			}
		}
	}

	/**
	 * Executes the given HTTP request asynchronously with retry logic. If the
	 * request fails and the retry conditions are met, it will wait for the
	 * specified backoff duration before attempting to execute the request again.
	 * This process will repeat until the maximum number of retries is reached or
	 * the request succeeds.
	 *
	 * @param request The HTTP request to be executed. Must not be null.
	 * @param attempt The current attempt number, starting from 0. Used internally
	 *                to track the number of retries.
	 * @return A CompletableFuture that completes with the HTTP response if the
	 *         request succeeds within the allowed number of retries, or completes
	 *         exceptionally if the request fails and the retry conditions are not
	 *         met (e.g., non-idempotent method or max retries exceeded).
	 */
	private CompletableFuture<Response> executeAsyncWithRetry(Request request, int attempt) {
		return super.executeAsync(request).handle((response, error) -> {
			if (error == null || attempt >= retryPolicy.getMaxRetries() || !request.getHttpMethod().isIdempotent()) {
				if (error != null)
					throw new RuntimeException(error);
				return response;
			} else {
				return null;
			}
		}).thenCompose(response -> {
			if (response != null) {
				return CompletableFuture.completedFuture(response);
			} else {
				return sleepAsync(retryPolicy.getBackoffStrategy().nextDelay(attempt))
						.thenCompose(v -> executeAsyncWithRetry(request, attempt + 1));
			}
		});
	}

	/**
	 * Helper method to sleep for the specified duration. If the duration is zero,
	 * it returns immediately. This method is used to implement the backoff strategy
	 * between retries in the synchronous execution of HTTP requests.
	 *
	 * @param duration The duration to sleep. Must not be null.
	 */
	private void sleep(Duration duration) {
		if (!duration.isZero()) {
			try {
				Thread.sleep(duration.toMillis());
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}

	/**
	 * Helper method to sleep asynchronously for the specified duration. If the
	 * duration is zero, it returns a completed CompletableFuture immediately. This
	 * method is used to implement the backoff strategy between retries in the
	 * asynchronous execution of HTTP requests.
	 *
	 * @param duration The duration to sleep. Must not be null.
	 * @return A CompletableFuture that completes after the specified duration has
	 *         elapsed.
	 */
	private CompletableFuture<Void> sleepAsync(Duration duration) {
		if (duration.isZero())
			return CompletableFuture.completedFuture(null);
		CompletableFuture<Void> future = new CompletableFuture<>();
		Thread t = new Thread(() -> {
			try {
				Thread.sleep(duration.toMillis());
				future.complete(null);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				future.completeExceptionally(e);
			}
		});
		t.setDaemon(true);
		t.start();
		return future;
	}

}
