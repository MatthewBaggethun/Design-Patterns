package structural.decorator.httpPipeline.Model;

import java.net.URI;
import java.util.Optional;

/**
 * Represents an HTTP request with its method, URI, headers, and optional body.
 * This class is immutable and provides methods to create modified copies of the
 * request with additional headers or a body.
 */
public final class Request {

	private final HttpMethod method;
	private final URI uri;
	private final Headers headers;
	private final Optional<String> body;

	/**
	 * Constructs a new Request with the specified method, URI, headers, and body.
	 *
	 * @param method  The HTTP method of the request.
	 * @param uri     The URI of the request.
	 * @param headers The headers of the request.
	 * @param body    The optional body of the request.
	 */
	private Request(HttpMethod method, URI uri, Headers headers, Optional<String> body) {
		this.method = method;
		this.uri = uri;
		this.headers = headers;
		this.body = body;
	}

	/**
	 * Creates a new Request with the specified HTTP method and URI, and empty
	 * headers and body.
	 *
	 * @param method The HTTP method of the request.
	 * @param uri    The URI of the request.
	 * @return A new Request instance with the specified method and URI.
	 */
	public static Request of(HttpMethod method, URI uri) {
		return new Request(method, uri, Headers.empty(), Optional.empty());
	}

	/**
	 * Returns a new Request instance with the specified header added to the
	 * existing headers.
	 *
	 * @param name  The name of the header to add.
	 * @param value The value of the header to add.
	 * @return A new Request instance with the specified header added.
	 */
	public Request withHeader(String name, String value) {
		return new Request(method, uri, headers.with(name, value), body);
	}

	/**
	 * Returns a new Request instance with the specified body added.
	 *
	 * @param body The body to add to the request.
	 * @return A new Request instance with the specified body added.
	 */
	public Request withBody(String body) {
		return new Request(method, uri, headers, Optional.of(body));
	}

	/**
	 * Returns the HTTP method of the request.
	 *
	 * @return The HTTP method of the request.
	 */
	public HttpMethod getHttpMethod() {
		return method;
	}

	/**
	 * Returns the URI of the request.
	 *
	 * @return The URI of the request.
	 */
	public URI getUri() {
		return uri;
	}

	/**
	 * Returns the headers of the request.
	 *
	 * @return The headers of the request.
	 */
	public Headers getHeaders() {
		return headers;
	}

	/**
	 * Returns the optional body of the request.
	 *
	 * @return An Optional containing the body of the request if present, or an
	 *         empty Optional if no body is present.
	 */
	public Optional<String> getBody() {
		return body;
	}

}
