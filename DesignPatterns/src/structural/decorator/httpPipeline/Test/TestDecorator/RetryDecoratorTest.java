package structural.decorator.httpPipeline.Test.TestDecorator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import structural.decorator.httpPipeline.Decorators.RetryDecorator;
import structural.decorator.httpPipeline.Model.Headers;
import structural.decorator.httpPipeline.Model.HttpMethod;
import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;
import structural.decorator.httpPipeline.Strategy.Retry.BackoffStrategy;
import structural.decorator.httpPipeline.Strategy.Retry.FixedDelayStrategy;
import structural.decorator.httpPipeline.Strategy.Retry.RetryPolicy;
import structural.decorator.httpPipeline.Test.TestHandler.TestHandler;

/**
 * Unit tests for the RetryDecorator class. These tests verify that the
 * RetryDecorator correctly retries failed HTTP requests according to the
 * specified RetryPolicy and that it interacts properly with the BackoffStrategy
 * between retry attempts. The tests cover both synchronous and asynchronous
 * execution of HTTP requests, as well as scenarios where the request eventually
 * succeeds or fails after reaching the maximum number of retry attempts.
 */
public class RetryDecoratorTest {

	/**
	 * Tests that the RetryDecorator retries a failed request until it succeeds
	 * synchronously.
	 */
	@Test
	void retriesUntilSuccessSynchronously() {

		AtomicInteger attempts = new AtomicInteger();

		TestHandler fake = new TestHandler(() -> {
			if (attempts.incrementAndGet() < 5) {
				throw new RuntimeException("Simulated failure");
			}
			return successResponse();
		});

		RetryPolicy policy = new RetryPolicy(5, new FixedDelayStrategy(Duration.ZERO));

		RetryDecorator decorator = new RetryDecorator(fake, policy);

		Response response = decorator.execute(testRequest());

		assertEquals(5, fake.callCount());
		assertEquals(200, response.getStatusCode());
	}

	/**
	 * Tests that the RetryDecorator retries a failed request until it succeeds
	 * asynchronously.
	 */
	@Test
	void retriesUntilSuccessAsynchronously() {

		AtomicInteger attempts = new AtomicInteger();

		TestHandler fake = new TestHandler(() -> {
			if (attempts.incrementAndGet() < 5) {
				throw new RuntimeException("Fail");
			}
			return successResponse();
		});

		RetryPolicy policy = new RetryPolicy(5, new FixedDelayStrategy(Duration.ZERO));

		RetryDecorator decorator = new RetryDecorator(fake, policy);

		Response response = decorator.executeAsync(testRequest()).join();

		assertEquals(5, fake.callCount());
		assertEquals(200, response.getStatusCode());
	}

	/**
	 * Tests that the RetryPolicy invokes the BackoffStrategy between retry
	 * attempts.
	 */
	@Test
	void invokesBackoffStrategyBetweenRetries() {

		AtomicInteger delayCalls = new AtomicInteger();

		BackoffStrategy delayStrategy = attempt -> {
			delayCalls.incrementAndGet();
			return Duration.ZERO;
		};

		TestHandler fake = new TestHandler(() -> {
			throw new RuntimeException("Fail");
		});

		RetryPolicy policy = new RetryPolicy(5, delayStrategy);
		RetryDecorator decorator = new RetryDecorator(fake, policy);

		assertThrows(RuntimeException.class, () -> decorator.execute(testRequest()));

		// Delay is called between attempts (maxAttempts - 1)
		assertEquals(4, delayCalls.get());
	}

	private static Request testRequest() {
		return Request.of(HttpMethod.GET, URI.create("https://example.com"));
	}

	private static Response successResponse() {
		return new Response(200, Headers.empty(), "");
	}

	/**
	 * Tests that the RetryDecorator fails with an exception after reaching the
	 * maximum number of retry attempts.
	 */
	@Test
	void failsAfterMaxAttempts() {

		TestHandler fake = new TestHandler(() -> {
			throw new RuntimeException("Always fails");
		});

		RetryPolicy policy = new RetryPolicy(5, new FixedDelayStrategy(Duration.ZERO));

		RetryDecorator decorator = new RetryDecorator(fake, policy);

		assertThrows(RuntimeException.class, () -> decorator.execute(testRequest()));

		assertEquals(5, fake.callCount());
	}

}
