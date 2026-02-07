package structural.decorator.httpPipeline.Strategy.Auth;

import structural.decorator.httpPipeline.Model.Request;

/**
 * An interface that defines the strategy for adding authentication information
 * to HTTP requests. Implementations of this interface will specify how to
 * generate the appropriate authentication header and its value based on the
 * request details. This allows for different authentication mechanisms (e.g.,
 * Basic Auth, Bearer Token, API Key) to be easily integrated into the HTTP
 * pipeline by providing different implementations of this strategy.
 * 
 * @see Request
 */
public interface AuthenticationStrategy {

	/**
	 * Returns the name of the HTTP header that should be used for authentication.
	 * This method is used to identify which header the authentication information
	 * will be placed in when processing the request.
	 * 
	 * @return The name of the HTTP header for authentication.
	 */
	String headerName();

	/**
	 * Returns the value that should be placed in the authentication header for the
	 * given request. This method is responsible for generating or retrieving the
	 * appropriate authentication credentials based on the request details, such as
	 * generating a token, retrieving a session ID, or constructing a basic auth
	 * string.
	 * 
	 * @param request The HTTP request for which the authentication header value is
	 *                being generated. This may contain information needed to
	 *                determine the correct authentication credentials.
	 * @return The value to be placed in the authentication header for the given
	 *         request.
	 */
	String headerValue(Request request);

}
