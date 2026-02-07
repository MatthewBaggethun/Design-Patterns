package structural.decorator.httpPipeline.HttpHandler;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import structural.decorator.httpPipeline.Model.Headers;
import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;

/**
 * An implementation of the HttpHandler interface that uses Java's built-in
 * HttpClient to execute HTTP requests. This class provides both synchronous and
 * asynchronous methods for processing requests, and it converts between the
 * custom Request and Response models and the HttpClient's HttpRequest and
 * HttpResponse types.
 */
public class JavaHttpHandler implements HttpHandler {

	private final HttpClient client;

	/**
	 * Constructs a new JavaHttpHandler with the given HttpClient.
	 *
	 * @param client The HttpClient to be used for executing HTTP requests. Must not
	 *               be null.
	 * @throws NullPointerException if client is null.
	 */
	public JavaHttpHandler(HttpClient client) {
		this.client = client;
	}

	/**
	 * Creates a new JavaHttpHandler with a default HttpClient instance.
	 *
	 * @return A new JavaHttpHandler using the default HttpClient.
	 */
	public static JavaHttpHandler defaultClient() {
		return new JavaHttpHandler(HttpClient.newHttpClient());
	}

	@Override
	public Response execute(Request request) {
		try {
			HttpRequest httpRequest = toHttpRequest(request);
			HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
			return toResponse(httpResponse);
		} catch (Exception e) {
			throw new RuntimeException("HTTP request failed", e);
		}
	}

	@Override
	public CompletableFuture<Response> executeAsync(Request request) {
		HttpRequest httpRequest = toHttpRequest(request);
		return client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString()).thenApply(this::toResponse);
	}

	/**
	 * Converts a custom Request object to an HttpRequest that can be executed by
	 * the HttpClient. This method maps the HTTP method, URI, headers, and body from
	 * the Request to the corresponding fields in the HttpRequest.
	 *
	 * @param request The custom Request to be converted.
	 * @return An HttpRequest that represents the same HTTP request as the input
	 *         Request.
	 */
	private HttpRequest toHttpRequest(Request request) {
		HttpRequest.Builder builder = HttpRequest.newBuilder().uri(request.getUri());

		switch (request.getHttpMethod()) {
		case GET, DELETE, HEAD, OPTIONS ->
			builder.method(request.getHttpMethod().name(), HttpRequest.BodyPublishers.noBody());

		default -> builder.method(request.getHttpMethod().name(), request.getBody()
				.map(HttpRequest.BodyPublishers::ofString).orElse(HttpRequest.BodyPublishers.noBody()));
		}

		request.getHeaders().asMap().forEach((name, values) -> values.forEach(value -> builder.header(name, value)));

		return builder.build();
	}

	/**
	 * Converts an HttpResponse from the HttpClient into a custom Response object.
	 * This method maps the status code, headers, and body from the HttpResponse to
	 * the corresponding fields in the Response.
	 *
	 * @param httpResponse The HttpResponse to be converted.
	 * @return A Response that represents the same HTTP response as the input
	 *         HttpResponse.
	 */
	private Response toResponse(HttpResponse<String> httpResponse) {
		return new Response(httpResponse.statusCode(), Headers.of(httpResponse.headers().map()), httpResponse.body());
	}

}
