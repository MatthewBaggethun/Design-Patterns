package structural.decorator.httpPipeline.Model;

/**
 * Enum representing HTTP methods and their idempotency.
 */
public enum HttpMethod {

	GET(true), POST(false), PUT(true), DELETE(true), PATCH(false), HEAD(true), OPTIONS(true);

	private final boolean idempotent;

	/**
	 * Constructs an HttpMethod with the specified idempotency.
	 *
	 * @param idempotent Indicates whether the HTTP method is idempotent.
	 */
	HttpMethod(boolean idempotent) {
		this.idempotent = idempotent;
	}

	/**
	 * Returns whether the HTTP method is idempotent.
	 *
	 * @return true if the HTTP method is idempotent, false otherwise.
	 */
	public boolean isIdempotent() {
		return idempotent;
	}

}
