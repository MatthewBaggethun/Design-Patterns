package structural.decorator.httpPipeline.Decorators;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import structural.decorator.httpPipeline.HttpHandler.HttpHandler;
import structural.decorator.httpPipeline.HttpHandler.HttpHandlerDecorator;
import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;

/**
 * A decorator that adds a timeout mechanism to an HTTP handler. It executes the
 * decorated handler in a separate thread and waits for the response for a
 * specified duration. If the response is not received within the timeout
 * period, it throws a RuntimeException indicating that the request has timed
 * out. This decorator works for both synchronous and asynchronous HTTP
 * handlers. The order of decorators in the pipeline will affect the behavior of
 * the timeout, as it will apply to the point where it is placed in the
 * pipeline.
 * 
 * @see HttpHandler
 * @see HttpHandlerDecorator
 * @see Request
 * @see Response
 */
public final class TimeoutDecorator extends HttpHandlerDecorator {

	private final Duration timeout;

	/**
	 * Constructs a TimeoutDecorator that wraps the given HttpHandler and applies
	 * the specified timeout duration.
	 * 
	 * @param decoratedHandler The HttpHandler to be decorated with timeout
	 *                         functionality.
	 * @param timeout          The duration after which the request should time out
	 *                         if no response is received. Must be positive.
	 * @throws IllegalArgumentException if the timeout duration is zero or negative.
	 */
	public TimeoutDecorator(HttpHandler decoratedHandler, Duration timeout) {
		super(decoratedHandler);
		if (timeout.isNegative() || timeout.isZero()) {
			throw new IllegalArgumentException("Timeout must be positive");
		}
		this.timeout = timeout;
	}

	@Override
	public Response execute(Request request) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Future<Response> future = executor.submit(() -> super.execute(request));
		try {
			return future.get(timeout.toMillis(), TimeUnit.MILLISECONDS);
		} catch (TimeoutException e) {
			throw new RuntimeException("Request timed out after " + timeout.toMillis() + "ms", e);
		} catch (ExecutionException e) {
			throw new RuntimeException(e.getCause());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException("Thread interrupted", e);
		} finally {
			executor.shutdownNow();
		}
	}

	@Override
	public CompletableFuture<Response> executeAsync(Request request) {
		return super.executeAsync(request).orTimeout(timeout.toMillis(), TimeUnit.MILLISECONDS).exceptionally(ex -> {
			if (ex instanceof TimeoutException) {
				throw new RuntimeException("Async request timed out after " + timeout.toMillis() + "ms", ex);
			} else {
				throw new RuntimeException(ex);
			}
		});
	}

}
