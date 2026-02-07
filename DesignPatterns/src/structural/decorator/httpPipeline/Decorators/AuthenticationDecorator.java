package structural.decorator.httpPipeline.Decorators;

import java.util.concurrent.CompletableFuture;

import structural.decorator.httpPipeline.HttpHandler.HttpHandler;
import structural.decorator.httpPipeline.HttpHandler.HttpHandlerDecorator;
import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;
import structural.decorator.httpPipeline.Strategy.Auth.AuthenticationStrategy;

/**
 * A decorator that adds authentication to HTTP requests using a specified
 * AuthenticationStrategy. This decorator modifies the HTTP request by adding
 * the appropriate authentication headers based on the provided strategy before
 * passing it to the next handler in the pipeline. It supports both synchronous
 * and asynchronous execution of HTTP requests. The AuthenticationStrategy can
 * be implemented in various ways (e.g., API key, bearer token, basic auth) to
 * accommodate different authentication mechanisms required by different APIs.
 * 
 * @see HttpHandler
 * @see HttpHandlerDecorator
 * @see Request
 * @see Response
 * @see AuthenticationStrategy
 */
public final class AuthenticationDecorator extends HttpHandlerDecorator {

	private final AuthenticationStrategy strategy;

	/**
	 * Constructs an AuthenticationDecorator that wraps the given HttpHandler and
	 * uses the specified AuthenticationStrategy to add authentication information
	 * to HTTP requests.
	 * 
	 * @param decoratedHandler The HttpHandler to be decorated with authentication
	 *                         functionality. This is the next handler in the
	 *                         pipeline that will receive the modified request with
	 *                         authentication headers.
	 * @param strategy         The AuthenticationStrategy that defines how to
	 *                         generate the authentication headers for HTTP
	 *                         requests. This strategy will be used to determine
	 *                         which header to add and what value to include based
	 *                         on the request details.
	 * @throws IllegalArgumentException if the provided AuthenticationStrategy is
	 *                                  null.
	 */
	public AuthenticationDecorator(HttpHandler decoratedHandler, AuthenticationStrategy strategy) {
		super(decoratedHandler);
		if (strategy == null)
			throw new IllegalArgumentException("AuthenticationStrategy cannot be null");
		this.strategy = strategy;
	}

	/**
	 * Modifies the given HTTP request by adding the appropriate authentication
	 * headers based on the provided AuthenticationStrategy. This method creates a
	 * new Request object that includes all the original headers and body, along
	 * with the new authentication header specified by the strategy.
	 * 
	 * @param request The original HTTP request that needs to be modified with
	 *                authentication information.
	 * @return A new Request object that includes the original request details along
	 *         with the added authentication header.
	 */
	private Request withAuth(Request request) {
		Request newRequest = Request.of(request.getHttpMethod(), request.getUri());

		for (var entry : request.getHeaders().asMap().entrySet()) {
			String key = entry.getKey();
			for (String value : entry.getValue()) {
				newRequest = newRequest.withHeader(key, value);
			}
		}

		newRequest = newRequest.withHeader(strategy.headerName(), strategy.headerValue(request));

		if (request.getBody().isPresent()) {
			newRequest = newRequest.withBody(request.getBody().get());
		}

		return newRequest;
	}

	@Override
	public Response execute(Request request) {
		return super.execute(withAuth(request));
	}

	@Override
	public CompletableFuture<Response> executeAsync(Request request) {
		return super.executeAsync(withAuth(request));
	}
}
