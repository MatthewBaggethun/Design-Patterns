package structural.decorator.httpPipeline.Model;

/**
 * Represents an HTTP response with a status code, headers, and body. This class
 * is immutable and provides methods to access the response details and check if
 * the response indicates a successful outcome.
 */
public final class Response {

	private final int statusCode;
	private final Headers headers;
	private final String body;

	/**
	 * Constructs a new Response with the specified status code, headers, and body.
	 *
	 * @param statusCode The HTTP status code of the response.
	 * @param headers    The headers of the response.
	 * @param body       The body of the response.
	 */
	public Response(int statusCode, Headers headers, String body) {
		this.statusCode = statusCode;
		this.headers = headers;
		this.body = body;
	}

	/**
	 * Checks if the response status code indicates a successful outcome (2xx).
	 *
	 * @return true if the status code is in the range 200-299, false otherwise.
	 */
	public boolean isSuccess() {
		return statusCode >= 200 && statusCode < 300;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public Headers getHeaders() {
		return headers;
	}

	public String getBody() {
		return body;
	}

}
