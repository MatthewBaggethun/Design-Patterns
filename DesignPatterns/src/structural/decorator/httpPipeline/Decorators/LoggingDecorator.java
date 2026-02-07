package structural.decorator.httpPipeline.Decorators;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

import structural.decorator.httpPipeline.HttpHandler.HttpHandler;
import structural.decorator.httpPipeline.HttpHandler.HttpHandlerDecorator;
import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;

/**
 * A decorator that logs HTTP requests and responses along with their durations.
 * It logs the request method and URI when a request is made, the response
 * status code and duration when a response is received, and any errors that
 * occur during the request processing. This decorator works for both
 * synchronous and asynchronous HTTP handlers. The order of decorators in the
 * pipeline will affect the logging output, as it will log the request and
 * response at the point where it is placed in the pipeline.
 * 
 * @see HttpHandler
 * @see HttpHandlerDecorator
 * @see Request
 * @see Response
 */
public class LoggingDecorator extends HttpHandlerDecorator {

	/**
	 * Constructs a LoggingDecorator that wraps the given HttpHandler.
	 * 
	 * @param decoratedHandler The HttpHandler to be decorated with logging
	 *                         functionality.
	 */
	public LoggingDecorator(HttpHandler decoratedHandler) {
		super(decoratedHandler);
	}

	@Override
	public Response execute(Request request) {
		Instant start = Instant.now();
		logRequest(request);

		try {
			Response response = super.execute(request);
			logResponse(response, Duration.between(start, Instant.now()));
			return response;
		} catch (RuntimeException e) {
			logFailure(e, Duration.between(start, Instant.now()));
			throw e;
		}
	}

	@Override
	public CompletableFuture<Response> executeAsync(Request request) {
		Instant start = Instant.now();
		logRequest(request);

		return super.executeAsync(request).whenComplete((response, error) -> {
			Duration duration = Duration.between(start, Instant.now());
			if (error != null) {
				logFailure(error, duration);
			} else {
				logResponse(response, duration);
			}
		});
	}

	private void logRequest(Request request) {
		System.out.printf("→ %s %s%n", request.getHttpMethod(), request.getUri());
	}

	private void logResponse(Response response, Duration duration) {
		System.out.printf("← %d (%d ms)%n", response.getStatusCode(), duration.toMillis());
	}

	private void logFailure(Throwable error, Duration duration) {
		System.out.printf("FAILED (%d ms): %s%n", duration.toMillis(), error.getMessage());
	}

}
