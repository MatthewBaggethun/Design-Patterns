package structural.decorator.httpPipeline.Strategy.Auth;

import structural.decorator.httpPipeline.Model.Request;

/**
 * An implementation of the AuthenticationStrategy interface that uses an API
 * key for authentication. This strategy adds a custom header with the specified
 * name and the API key value to the HTTP request. This is commonly used for API
 * authentication where clients are issued an API key that must be included in
 * requests to access protected resources. The header name can be customized to
 * fit the requirements of the specific API being accessed.
 * 
 * @see AuthenticationStrategy
 * @see Request
 */
public final class ApiKeyStrategy implements AuthenticationStrategy {

	private final String apiKey;
	private final String headerName;

	/**
	 * Constructs an ApiKeyStrategy with the specified header name and API key.
	 * 
	 * @param headerName The name of the HTTP header to be used for the API key.
	 *                   This allows for flexibility in how the API key is included
	 *                   in the request, as different APIs may require different
	 *                   header names (e.g., "X-API-Key", "Authorization", etc.).
	 * @param apiKey     The API key to be used for authentication. This key will be
	 *                   included in the specified header of HTTP requests to
	 *                   authenticate with the server.
	 */
	public ApiKeyStrategy(String headerName, String apiKey) {
		this.headerName = headerName;
		this.apiKey = apiKey;
	}

	@Override
	public String headerName() {
		return headerName;
	}

	@Override
	public String headerValue(Request request) {
		return apiKey;
	}

}
