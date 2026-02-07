package structural.decorator.httpPipeline.Strategy.Auth;

import java.util.Base64;

import structural.decorator.httpPipeline.Model.Request;

/**
 * An implementation of the AuthenticationStrategy interface that uses basic
 * authentication with a username and password. This strategy adds an
 * Authorization header with the value "Basic {encodedCredentials}" to the HTTP
 * request, where {encodedCredentials} is the Base64-encoded string of
 * "username:password". This is a common authentication method where the client
 * provides a username and password to access protected resources on the server.
 * The credentials are encoded to ensure that they are not sent in plain text,
 * although it is important to note that basic authentication should be used
 * over HTTPS to protect the credentials from being intercepted by attackers.
 * 
 * @see AuthenticationStrategy
 * @see Request
 */
public final class PasswordStrategy implements AuthenticationStrategy {

	private final String username;
	private final String password;

	/**
	 * Constructs a PasswordStrategy with the specified username and password.
	 * 
	 * @param username The username to be used for basic authentication. This will be
	 *                 included in the Authorization header of HTTP requests.
	 * @param password The password to be used for basic authentication. This will be
	 *                 included in the Authorization header of HTTP requests.
	 */
	public PasswordStrategy(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Override
	public String headerName() {
		return "Authorization";
	}

	@Override
	public String headerValue(Request request) {
		String credentials = username + ":" + password;
		String encoded = Base64.getEncoder().encodeToString(credentials.getBytes());
		return "Basic " + encoded;
	}

}
