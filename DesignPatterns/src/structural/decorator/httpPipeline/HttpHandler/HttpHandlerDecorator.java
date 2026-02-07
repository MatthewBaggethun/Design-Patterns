package structural.decorator.httpPipeline.HttpHandler;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;

/**
 * An abstract decorator for HttpHandler that allows for extending the behavior
 * of an existing HttpHandler without modifying its code. This class implements
 * the HttpHandler interface and delegates all method calls to the decorated
 * HttpHandler instance. Subclasses can override these methods to add additional
 * functionality before or after delegating to the decorated handler.
 */
public abstract class HttpHandlerDecorator implements HttpHandler {

	protected final HttpHandler decoratedHandler;

	/**
	 * Constructs a new HttpHandlerDecorator that wraps the given HttpHandler.
	 *
	 * @param decoratedHandler The HttpHandler to be decorated. Must not be null.
	 * @throws NullPointerException if decoratedHandler is null.
	 */
	protected HttpHandlerDecorator(HttpHandler decoratedHandler) {
		this.decoratedHandler = Objects.requireNonNull(decoratedHandler, "Decorated HttpHandler cannot be null");
	}

	@Override
	public Response execute(Request request) {
		return decoratedHandler.execute(request);
	}

	@Override
	public CompletableFuture<Response> executeAsync(Request request) {
		return decoratedHandler.executeAsync(request);
	}

}
