package structural.decorator.httpPipeline.Strategy.Auth;

import structural.decorator.httpPipeline.Model.Request;

/**
 * An implementation of the AuthenticationStrategy interface that uses a bearer
 * token for authentication. This strategy adds an Authorization header with the
 * value " Bearer {token}" to the HTTP request, where {token} is the bearer
 * token provided during the construction of this strategy. This is commonly
 * used for API authentication where a token is issued by the server and must be
 * included in subsequent requests to access protected resources.
 * 
 * @see AuthenticationStrategy
 * @see Request
 */
public final class BearerTokenStrategy implements AuthenticationStrategy {

	private final String token;

	/**
	 * Constructs a BearerTokenStrategy with the specified token.
	 * 
	 * @param token The bearer token to be used for authentication. This token will
	 *              be included in the Authorization header of HTTP requests.
	 */
	public BearerTokenStrategy(String token) {
		this.token = token;
	}

	@Override
	public String headerName() {
		return "Authorization";
	}

	@Override
	public String headerValue(Request request) {
		return "Bearer " + token;
	}

}
