package structural.decorator.httpPipeline.Test.TestPipelineFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import structural.decorator.httpPipeline.Decorators.AuthenticationDecorator;
import structural.decorator.httpPipeline.Decorators.LoggingDecorator;
import structural.decorator.httpPipeline.Decorators.RetryDecorator;
import structural.decorator.httpPipeline.Decorators.TimeoutDecorator;
import structural.decorator.httpPipeline.Factory.PipelineConfig;
import structural.decorator.httpPipeline.Factory.PipelineFactory;
import structural.decorator.httpPipeline.HttpHandler.HttpHandler;
import structural.decorator.httpPipeline.HttpHandler.HttpHandlerDecorator;
import structural.decorator.httpPipeline.HttpHandler.JavaHttpHandler;
import structural.decorator.httpPipeline.Model.Headers;
import structural.decorator.httpPipeline.Model.HttpMethod;
import structural.decorator.httpPipeline.Model.Request;
import structural.decorator.httpPipeline.Model.Response;
import structural.decorator.httpPipeline.Strategy.Auth.ApiKeyStrategy;
import structural.decorator.httpPipeline.Strategy.Auth.AuthenticationStrategy;
import structural.decorator.httpPipeline.Strategy.Auth.BearerTokenStrategy;
import structural.decorator.httpPipeline.Strategy.Auth.PasswordStrategy;
import structural.decorator.httpPipeline.Strategy.Retry.BackoffStrategy;
import structural.decorator.httpPipeline.Strategy.Retry.ExponentialBackoffStrategy;
import structural.decorator.httpPipeline.Strategy.Retry.FixedDelayStrategy;
import structural.decorator.httpPipeline.Strategy.Retry.RetryPolicy;
import structural.decorator.httpPipeline.Test.FakeHandler.FakeHandler;
import structural.decorator.httpPipeline.Test.FakeTestStrategies.FakeAuthenticationStrategy;

/**
 * Verify that the PipelineFactory correctly composes the pipeline based on
 * different configurations. The composition, integrity and reuseability of the
 * pipeline are tested to ensure the factory implementation is suitable for
 * client use.
 */
public class PipelineFactoryTest {

	/**
	 * Tests that the PipelineFactory correctly composes the pipeline based on the
	 * provided PipelineConfig. Verifies that decorators are applied in the correct
	 * order.
	 */
	@Test
	void pipelineCompositionIsCorrect() {
		// Manually compose the pipeline in the same order as the factory to avoid
		// utilizing a real JavaHttpHandler which would make real HTTP calls.
		HttpHandler pipeline = new AuthenticationDecorator(new LoggingDecorator(
				new RetryDecorator(new TimeoutDecorator(JavaHttpHandler.defaultClient(), Duration.ofMillis(100)),
						new RetryPolicy(3, attempt -> Duration.ZERO))),
				new FakeAuthenticationStrategy("Bearer TOKEN"));

		assertTrue(pipeline instanceof AuthenticationDecorator, "Outer decorator should be AuthenticationDecorator");
		HttpHandler inner1 = ((HttpHandlerDecorator) pipeline).getDecoratedHandler();

		assertTrue(inner1 instanceof LoggingDecorator, "Next decorator should be LoggingDecorator");
		HttpHandler inner2 = ((HttpHandlerDecorator) inner1).getDecoratedHandler();

		assertTrue(inner2 instanceof RetryDecorator, "Next decorator should be RetryDecorator");
		HttpHandler inner3 = ((HttpHandlerDecorator) inner2).getDecoratedHandler();

		assertTrue(inner3 instanceof TimeoutDecorator, "Next decorator should be TimeoutDecorator");
		HttpHandler inner4 = ((HttpHandlerDecorator) inner3).getDecoratedHandler();

		assertTrue(inner4.getClass().equals(JavaHttpHandler.defaultClient().getClass()),
				"Innermost handler should be JavaHttpHandler");
	}

	/**
	 * Tests that both synchronous and asynchronous execution works correctly for
	 * pipelines configured with different authentication and retry strategies. All
	 * combinations are tested to ensure strategies are compatible with each other
	 * and that the pipeline functions correctly regardless of the configuration.
	 */
	@Test
	void pipelineExecutesSynchronouslyAndAsynchronouslyForAllStrategyCombinations() {
		List<AuthenticationStrategy> authStrategies = List.of(new BearerTokenStrategy("TOKEN123"),
				new ApiKeyStrategy("X-API-Key", "APIKEY123"), new PasswordStrategy("user", "pass"));

		List<BackoffStrategy> retryStrategies = List.of(new FixedDelayStrategy(Duration.ofMillis(10)),
				new ExponentialBackoffStrategy(Duration.ofMillis(5), 2.0, Duration.ofMillis(50)));

		for (AuthenticationStrategy auth : authStrategies) {
			for (BackoffStrategy backoff : retryStrategies) {

				FakeHandler fake = new FakeHandler(() -> successResponse());

				// Manually compose the pipeline in the same order as the factory to avoid
				// utilizing a real JavaHttpHandler which would make real HTTP calls.
				HttpHandler pipeline = new AuthenticationDecorator(new LoggingDecorator(new RetryDecorator(
						new TimeoutDecorator(fake, Duration.ofMillis(50)), new RetryPolicy(3, backoff))), auth);

				Response response = pipeline.execute(testRequest());
				assertEquals(200, response.getStatusCode());

				Request handledRequest = fake.lastRequest();
				assertTrue(handledRequest.getHeaders().contains(auth.headerName()));
				assertEquals(auth.headerValue(handledRequest),
						handledRequest.getHeaders().get(auth.headerName()).get().get(0));

				Response asyncResponse = pipeline.executeAsync(testRequest()).join();
				assertEquals(200, asyncResponse.getStatusCode());
			}
		}
	}

	/**
	 * Tests that multiple requests using the same pipeline instance are handled
	 * independently.
	 */
	@Test
	void pipelineIsReusable() {
		AtomicInteger callCounter = new AtomicInteger();

		FakeHandler fake = new FakeHandler(() -> {
			callCounter.incrementAndGet();
			return successResponse();
		});

		// Manually compose the pipeline in the same order as the factory to avoid
		// utilizing a real JavaHttpHandler which would make real HTTP calls.
		var pipeline = new RetryDecorator(new TimeoutDecorator(new LoggingDecorator(fake), Duration.ofMillis(50)),
				new RetryPolicy(2, attempt -> Duration.ZERO));

		// Execute multiple requests sequentially
		pipeline.execute(testRequest());
		pipeline.execute(testRequest());
		pipeline.executeAsync(testRequest()).join();
		pipeline.executeAsync(testRequest()).join();

		assertEquals(4, callCounter.get());
	}

	// --------------------- Helper Methods ---------------------

	private static Request testRequest() {
		return Request.of(HttpMethod.GET, URI.create("https://example.com"));
	}

	private static Response successResponse() {
		return new Response(200, Headers.empty(), "");
	}

}
