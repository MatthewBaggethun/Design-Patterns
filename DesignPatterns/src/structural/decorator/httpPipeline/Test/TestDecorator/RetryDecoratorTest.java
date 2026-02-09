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
import structural.decorator.httpPipeline.Strategy.Retry.ExponentialBackoffStrategy;
import structural.decorator.httpPipeline.Strategy.Retry.FixedDelayStrategy;
import structural.decorator.httpPipeline.Strategy.Retry.RetryPolicy;
import structural.decorator.httpPipeline.Test.FakeHandler.FakeHandler;

/**
 * Verify that the RetryDecorator correctly retries failed HTTP requests
 * according to the specified RetryPolicy and that it interacts properly with
 * the BackoffStrategy between retry attempts. The tests cover both synchronous
 * and asynchronous execution of HTTP requests, as well as scenarios where the
 * request eventually succeeds or fails after reaching the maximum number of
 * retry attempts. Fixed and exponential backoff strategies are tested in
 * various scenarios to ensure that the RetryDecorator behaves as expected under
 * different retry conditions.
 */
public class RetryDecoratorTest {

	// ----------------------FixedDelayStrategy--------------------------

	/**
	 * Tests that the RetryDecorator retries a failed request until it succeeds
	 * synchronously utilizing a fixed delay strategy.
	 */
	@Test
	void retriesUntilSuccessWithFixedDelaySynchronously() {
		AtomicInteger attempts = new AtomicInteger();

		FakeHandler fake = new FakeHandler(() -> {
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
	 * asynchronously utilizing a fixed delay strategy.
	 */
	@Test
	void retriesUntilSuccessWithFixedDelayAsynchronously() {
		AtomicInteger attempts = new AtomicInteger();

		FakeHandler fake = new FakeHandler(() -> {
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
	 * Tests that the RetryDecorator fails with an exception after reaching the
	 * maximum number of retry attempts utilizing a fixed delay strategy.
	 */
	@Test
	void failsAfterMaxAttemptsWithFixedDelay() {
		FakeHandler fake = new FakeHandler(() -> {
			throw new RuntimeException("Always fails");
		});

		RetryPolicy policy = new RetryPolicy(5, new FixedDelayStrategy(Duration.ZERO));

		RetryDecorator decorator = new RetryDecorator(fake, policy);

		assertThrows(RuntimeException.class, () -> decorator.execute(testRequest()));

		assertEquals(5, fake.callCount());
	}

	// ----------------------ExponentialBackoffStrategy--------------------------

	/**
	 * Tests that the RetryDecorator retries a failed request with increasing delays
	 * according to an exponential backoff strategy until it succeeds.
	 */
	@Test
	void retriesWithExponentialBackoffSynchronously() {
		AtomicInteger attempts = new AtomicInteger();

		FakeHandler fake = new FakeHandler(() -> {
			if (attempts.incrementAndGet() < 4) {
				throw new RuntimeException("Fail");
			}
			return successResponse();
		});

		RetryPolicy policy = RetryPolicy.exponentialBackoff(5, Duration.ZERO, 2.0, Duration.ofSeconds(5));

		RetryDecorator decorator = new RetryDecorator(fake, policy);
		Response response = decorator.execute(testRequest());

		assertEquals(4, fake.callCount());
		assertEquals(200, response.getStatusCode());
	}

	/**
	 * Tests that the RetryDecorator retries a failed request with increasing delays
	 * according to an exponential backoff strategy until it succeeds
	 * asynchronously.
	 */
	@Test
	void retriesUntilSuccessWithExponentialBackoffAsynchronously() {
		AtomicInteger attempts = new AtomicInteger();

		FakeHandler fake = new FakeHandler(() -> {
			if (attempts.incrementAndGet() < 4) {
				throw new RuntimeException("Fail");
			}
			return successResponse();
		});

		BackoffStrategy strategy = new ExponentialBackoffStrategy(Duration.ofMillis(1), 2.0, Duration.ofMillis(10));

		RetryPolicy policy = new RetryPolicy(5, strategy);
		RetryDecorator decorator = new RetryDecorator(fake, policy);

		Response response = decorator.executeAsync(testRequest()).join();

		assertEquals(4, fake.callCount());
		assertEquals(200, response.getStatusCode());
	}

	/**
	 * Tests that the RetryDecorator fails with an exception after reaching the
	 * maximum number of retry attempts utilizing an exponential backoff strategy
	 * asynchronously.
	 */
	@Test
	void failsWithExponentialBackoffAsynchronously() {
		FakeHandler fake = new FakeHandler(() -> {
			throw new RuntimeException("Always fails");
		});

		RetryPolicy policy = RetryPolicy.exponentialBackoff(5, Duration.ofMillis(1), 2.0, Duration.ofMillis(10));
		RetryDecorator decorator = new RetryDecorator(fake, policy);

		assertThrows(RuntimeException.class, () -> decorator.executeAsync(testRequest()).join());
		assertEquals(5, fake.callCount());
	}

	/**
	 * Tests that the RetryDecorator fails with an exception after reaching the
	 * maximum number of retry attempts utilizing an exponential backoff strategy.
	 */
	@Test
	void failsAfterMaxAttemptsAsynchronously() {
		FakeHandler fake = new FakeHandler(() -> {
			throw new RuntimeException("Fail");
		});

		RetryPolicy policy = RetryPolicy.exponentialBackoff(5, Duration.ofMillis(10), 2.0, Duration.ofSeconds(1));

		RetryDecorator decorator = new RetryDecorator(fake, policy);

		assertThrows(RuntimeException.class, () -> decorator.execute(testRequest()));
		assertEquals(5, fake.callCount());
	}

	// ------------Core Functionality & Edge Cases------------

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

		FakeHandler fake = new FakeHandler(() -> {
			throw new RuntimeException("Fail");
		});

		RetryPolicy policy = new RetryPolicy(5, delayStrategy);
		RetryDecorator decorator = new RetryDecorator(fake, policy);

		assertThrows(RuntimeException.class, () -> decorator.execute(testRequest()));

		assertEquals(4, delayCalls.get());
	}

	/**
	 * Tests that the RetryDecorator does not retry non-idempotent requests like
	 * POST and only attempts them once.
	 */
	@Test
	void doesNotRetryNonIdempotentRequests() {
		AtomicInteger attempts = new AtomicInteger();
		FakeHandler fake = new FakeHandler(() -> {
			attempts.incrementAndGet();
			throw new RuntimeException("Fail");
		});

		RetryPolicy policy = RetryPolicy.fixedDelay(5, Duration.ZERO);
		RetryDecorator decorator = new RetryDecorator(fake, policy);

		Request postRequest = Request.of(HttpMethod.POST, URI.create("https://example.com"));
		assertThrows(RuntimeException.class, () -> decorator.execute(postRequest));

		assertEquals(1, fake.callCount());
	}

	/**
	 * Tests that the RetryDecorator does not retry when the maximum number of retry
	 * attempts is set to zero and only attempts the request once.
	 */
	@Test
	void noRetriesWhenMaxRetriesZero() {
		AtomicInteger attempts = new AtomicInteger();
		FakeHandler fake = new FakeHandler(() -> {
			attempts.incrementAndGet();
			throw new RuntimeException("Fail");
		});

		RetryPolicy policy = RetryPolicy.fixedDelay(0, Duration.ZERO);
		RetryDecorator decorator = new RetryDecorator(fake, policy);

		assertThrows(RuntimeException.class, () -> decorator.execute(testRequest()));
		assertEquals(1, fake.callCount());
	}

	// ------------Helpers------------

	private static Request testRequest() {
		return Request.of(HttpMethod.GET, URI.create("https://example.com"));
	}

	private static Response successResponse() {
		return new Response(200, Headers.empty(), "");
	}

}
