package structural.decorator.httpPipeline.HttpHandler;

import java.util.concurrent.CompletableFuture;

import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;

/**
 * Defines the contract for handling HTTP requests. An HttpHandler can be
 * wrapped by decorators to add additional behavior such as logging,
 * authentication, or caching. It provides both synchronous and asynchronous
 * methods for processing requests.
 */
public interface HttpHandler {

	/**
	 * Executes the given HTTP request and returns a response.
	 *
	 * @param request The HTTP request to be processed.
	 * @return The HTTP response generated from processing the request.
	 */
	Response execute(Request request);

	/**
	 * Executes the given HTTP request asynchronously and returns a
	 * CompletableFuture that will complete with the response.
	 *
	 * @param request The HTTP request to be processed.
	 * @return A CompletableFuture that will complete with the HTTP response
	 *         generated from processing the request.
	 */
	CompletableFuture<Response> executeAsync(Request request);

}
