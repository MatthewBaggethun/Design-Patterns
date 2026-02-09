package structural.decorator.httpPipeline.Test.TestDecorator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

import org.junit.jupiter.api.Test;

import structural.decorator.httpPipeline.Decorators.TimeoutDecorator;
import structural.decorator.httpPipeline.Model.Headers;
import structural.decorator.httpPipeline.Model.HttpMethod;
import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;
import structural.decorator.httpPipeline.Test.FakeHandler.FakeHandler;

/**
 * These tests verify that the TimeoutDecorator correctly handles both
 * synchronous and asynchronous HTTP handlers, enforcing the specified timeout
 * duration. The tests cover scenarios where the decorated handler takes longer
 * than the timeout to complete, as well as scenarios where it completes
 * successfully within the timeout period.
 */
public class TimeoutDecoratorTest {

	/**
	 * Verifies that the TimeoutDecorator throws a RuntimeException when the
	 * decorated handler takes longer than the specified timeout to complete. The
	 * test uses a TestHandler that simulates a delay of 100 milliseconds, while the
	 * TimeoutDecorator is configured with a timeout of 20 milliseconds. The test
	 * asserts that a RuntimeException is thrown and that the decorated handler was
	 * called exactly once.
	 */
	@Test
	void timesOutSynchronously() {

		FakeHandler fake = new FakeHandler(() -> {
			sleep(Duration.ofMillis(100));
			return successResponse();
		});

		TimeoutDecorator decorator = new TimeoutDecorator(fake, Duration.ofMillis(20));

		assertThrows(RuntimeException.class, () -> decorator.execute(testRequest()));

		assertEquals(1, fake.callCount());
	}

	/**
	 * Verifies that the TimeoutDecorator allows the decorated handler to complete
	 * successfully when it finishes within the specified timeout duration. The test
	 * uses a TestHandler that returns a successful response immediately, while the
	 * TimeoutDecorator is configured with a timeout of 50 milliseconds. The test
	 * asserts that the response has a status code of 200 and that the decorated
	 * handler was called exactly once.
	 */
	@Test
	void completesSynchronouslyBeforeTimeout() {

		FakeHandler fake = new FakeHandler(() -> successResponse());

		TimeoutDecorator decorator = new TimeoutDecorator(fake, Duration.ofMillis(50));

		Response response = decorator.execute(testRequest());

		assertEquals(200, response.getStatusCode());
		assertEquals(1, fake.callCount());
	}

	/**
	 * Verifies that the TimeoutDecorator throws a CompletionException when the
	 * decorated handler takes longer than the specified timeout to complete in an
	 * asynchronous context. The test uses a TestHandler that simulates a delay of
	 * 100 milliseconds, while the TimeoutDecorator is configured with a timeout of
	 * 20 milliseconds. The test asserts that a CompletionException is thrown when
	 * joining the future and that the future is completed exceptionally. It also
	 * verifies that the decorated handler was called exactly once.
	 */
	@Test
	void timesOutAsynchronously() {

		FakeHandler fake = new FakeHandler(() -> {
			sleep(Duration.ofMillis(100));
			return successResponse();
		});

		TimeoutDecorator decorator = new TimeoutDecorator(fake, Duration.ofMillis(20));

		CompletableFuture<Response> future = decorator.executeAsync(testRequest());

		assertThrows(CompletionException.class, future::join);

		assertTrue(future.isCompletedExceptionally());
		assertEquals(1, fake.callCount());
	}

	/**
	 * Verifies that the TimeoutDecorator allows the decorated handler to complete
	 * successfully when it finishes within the specified timeout duration in an
	 * asynchronous context. The test uses a TestHandler that returns a successful
	 * response immediately, while the TimeoutDecorator is configured with a timeout
	 * of 50 milliseconds. The test asserts that the response has a status code of
	 * 200 and that the decorated handler was called exactly once.
	 */
	@Test
	void completesAsynchronouslyBeforeTimeout() {

		FakeHandler fake = new FakeHandler(() -> successResponse());

		TimeoutDecorator decorator = new TimeoutDecorator(fake, Duration.ofMillis(50));

		Response response = decorator.executeAsync(testRequest()).join();

		assertEquals(200, response.getStatusCode());
		assertEquals(1, fake.callCount());
	}

	private static void sleep(Duration duration) {
		try {
			Thread.sleep(duration.toMillis());
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			throw new RuntimeException(e);
		}
	}

	private static Request testRequest() {
		return Request.of(HttpMethod.GET, URI.create("https://example.com"));
	}

	private static Response successResponse() {
		return new Response(200, Headers.empty(), "");
	}

}
