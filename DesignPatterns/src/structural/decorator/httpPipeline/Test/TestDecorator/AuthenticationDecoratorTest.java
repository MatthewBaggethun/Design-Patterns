package structural.decorator.httpPipeline.Test.TestDecorator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.util.Base64;

import org.junit.jupiter.api.Test;

import structural.decorator.httpPipeline.Decorators.AuthenticationDecorator;
import structural.decorator.httpPipeline.Model.Headers;
import structural.decorator.httpPipeline.Model.HttpMethod;
import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;
import structural.decorator.httpPipeline.Strategy.Auth.ApiKeyStrategy;
import structural.decorator.httpPipeline.Strategy.Auth.AuthenticationStrategy;
import structural.decorator.httpPipeline.Strategy.Auth.BearerTokenStrategy;
import structural.decorator.httpPipeline.Strategy.Auth.PasswordStrategy;
import structural.decorator.httpPipeline.Test.FakeHandler.FakeHandler;
import structural.decorator.httpPipeline.Test.FakeTestStrategies.FakeAuthenticationStrategy;

/**
 * Verify that the decorator correctly modifies HTTP requests by adding the
 * appropriate authentication headers for both synchronous and asynchronous
 * execution. The tests cover bearer token, API key, and password
 * authentication.
 * 
 * @see AuthenticationDecorator
 * @see AuthenticationStrategy
 */
public class AuthenticationDecoratorTest {

	/**
	 * Test that the AuthenticationDecorator correctly adds the authentication
	 * header to the HTTP request when executed synchronously. This test uses a
	 * TestAuthenticationStrategy that returns a fixed header value and verifies
	 * that the modified request contains the expected "Authorization" header with
	 * the correct value.
	 */
	@Test
	void addsAuthHeaderSynchronously() {
		FakeHandler fake = new FakeHandler(() -> successResponse());
		FakeAuthenticationStrategy strategy = new FakeAuthenticationStrategy("Bearer TOKEN123");

		AuthenticationDecorator decorator = new AuthenticationDecorator(fake, strategy);

		Response response = decorator.execute(testRequest());

		assertEquals(200, response.getStatusCode());

		Request handledRequest = fake.lastRequest();
		assertTrue(handledRequest.getHeaders().contains("Authorization"));
		assertEquals("Bearer TOKEN123", handledRequest.getHeaders().get("Authorization").get().get(0));
	}

	/**
	 * Test that the AuthenticationDecorator correctly adds the authentication
	 * header to the HTTP request when executed asynchronously. Similar to the
	 * synchronous test, this test uses a TestAuthenticationStrategy and verifies
	 * that the modified request contains the expected "Authorization" header with
	 * the correct value after the asynchronous execution completes.
	 */
	@Test
	void addsAuthHeaderAsynchronously() {
		FakeHandler fake = new FakeHandler(() -> successResponse());
		AuthenticationStrategy strategy = new FakeAuthenticationStrategy("Bearer ASYNC_TOKEN");

		AuthenticationDecorator decorator = new AuthenticationDecorator(fake, strategy);

		Response response = decorator.executeAsync(testRequest()).join();

		assertEquals(200, response.getStatusCode());

		Request handledRequest = fake.lastRequest();
		assertTrue(handledRequest.getHeaders().contains("Authorization"));
		assertEquals("Bearer ASYNC_TOKEN", handledRequest.getHeaders().get("Authorization").get().get(0));
	}

	/**
	 * Test that the AuthenticationDecorator correctly applies the
	 * BearerTokenStrategy by adding the appropriate "Authorization" header with the
	 * "Bearer" scheme and token value. This test verifies that when the
	 * BearerTokenStrategy is used, the modified request contains the expected
	 * header format.
	 */
	@Test
	void appliesBearerTokenStrategy() {
		FakeHandler fake = new FakeHandler(() -> successResponse());
		AuthenticationDecorator decorator = new AuthenticationDecorator(fake, new BearerTokenStrategy("TOKEN123"));

		decorator.execute(testRequest());
		Request handled = fake.lastRequest();

		assertTrue(handled.getHeaders().contains("Authorization"));
		assertEquals("Bearer TOKEN123", handled.getHeaders().get("Authorization").get().get(0));
	}

	/**
	 * Test that the AuthenticationDecorator correctly applies the ApiKeyStrategy by
	 * adding the appropriate header with the specified API key. This test verifies
	 * that when the ApiKeyStrategy is used, the modified request contains the
	 * expected header name and value as defined by the strategy.
	 */
	@Test
	void appliesApiKeyStrategy() {
		FakeHandler fake = new FakeHandler(() -> successResponse());
		AuthenticationDecorator decorator = new AuthenticationDecorator(fake,
				new ApiKeyStrategy("X-API-Key", "API123"));

		decorator.execute(testRequest());
		Request handled = fake.lastRequest();

		assertTrue(handled.getHeaders().contains("X-API-Key"));
		assertEquals("API123", handled.getHeaders().get("X-API-Key").get().get(0));
	}

	/**
	 * Test that the AuthenticationDecorator correctly applies the PasswordStrategy
	 * by adding the appropriate "Authorization" header with the "Basic" scheme and
	 * base64-encoded credentials. This test verifies that when the PasswordStrategy
	 * is used, the modified request contains the expected header format with the
	 * correct encoding of the username and password.
	 */
	@Test
	void appliesPasswordStrategy() {
		FakeHandler fake = new FakeHandler(() -> successResponse());
		AuthenticationDecorator decorator = new AuthenticationDecorator(fake, new PasswordStrategy("user", "pass"));

		decorator.execute(testRequest());
		Request handled = fake.lastRequest();

		assertTrue(handled.getHeaders().contains("Authorization"));
		String expected = "Basic " + Base64.getEncoder().encodeToString("user:pass".getBytes());
		assertEquals(expected, handled.getHeaders().get("Authorization").get().get(0));
	}

	private static Request testRequest() {
		return Request.of(HttpMethod.GET, URI.create("https://example.com"));
	}

	private static Response successResponse() {
		return new Response(200, Headers.empty(), "OK");
	}

}
