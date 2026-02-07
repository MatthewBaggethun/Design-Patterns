package structural.decorator.httpPipeline.Factory;

import structural.decorator.httpPipeline.Decorators.AuthenticationDecorator;
import structural.decorator.httpPipeline.Decorators.LoggingDecorator;
import structural.decorator.httpPipeline.Decorators.RetryDecorator;
import structural.decorator.httpPipeline.Decorators.TimeoutDecorator;
import structural.decorator.httpPipeline.HttpHandler.HttpHandler;
import structural.decorator.httpPipeline.HttpHandler.JavaHttpHandler;
import structural.decorator.httpPipeline.Strategy.Auth.AuthenticationStrategy;
import structural.decorator.httpPipeline.Strategy.Retry.RetryPolicy;

/**
 * A factory class for creating an HTTP pipeline based on a given
 * PipelineConfig. This class provides a static method to construct an
 * HttpHandler that is decorated with various functionalities such as logging,
 * retry policies, timeouts, and authentication based on the settings specified
 * in the PipelineConfig. The createPipeline method takes a PipelineConfig
 * object as input and applies the appropriate decorators to the base
 * HttpHandler in the correct order, ensuring that authentication is applied
 * last to allow other decorators to function properly. This factory simplifies
 * the creation of complex HTTP pipelines by encapsulating the logic for
 * applying multiple decorators based on configuration settings, making it
 * easier to manage and maintain different pipeline configurations in a clean
 * and modular way.
 * 
 * @see PipelineConfig
 * @see HttpHandler
 */
public final class PipelineFactory {

	private PipelineFactory() {
		// Instance creation is not allowed.
	}

	/**
	 * Creates an HttpHandler pipeline based on the provided PipelineConfig. This
	 * method constructs a base HttpHandler and applies various decorators such as
	 * TimeoutDecorator, RetryDecorator, LoggingDecorator, and
	 * AuthenticationDecorator based on the settings specified in the
	 * PipelineConfig. The decorators are applied in a specific order to ensure that
	 * authentication is applied last, allowing other decorators to function
	 * correctly. The resulting HttpHandler can be used to execute HTTP requests
	 * with the configured behaviors such as logging, retries, timeouts, and
	 * authentication.
	 * 
	 * @param config The PipelineConfig object containing the configuration settings
	 *               for the HTTP pipeline. This includes options for enabling
	 *               logging, retry policies, timeouts, and authentication
	 *               strategies.
	 * @return An HttpHandler that is decorated with the specified functionalities
	 *         based on the provided PipelineConfig. This handler can be used to
	 *         execute HTTP requests with the configured behaviors.
	 */
	public static HttpHandler createPipeline(PipelineConfig config) {
		HttpHandler handler = JavaHttpHandler.defaultClient();

		// Apply TimeoutDecorator
		if (config.isEnableTimeout()) {
			handler = new TimeoutDecorator(handler, config.getTimeout());
		}

		// Apply RetryDecorator
		if (config.isEnableRetry()) {
			handler = new RetryDecorator(handler,
					new RetryPolicy(config.getRetryAttempts(), config.getBackoffStrategy()));
		}

		// Apply LoggingDecorator
		if (config.isEnableLogging()) {
			handler = new LoggingDecorator(handler);
		}

		// Apply AuthenticationDecorator last to ensure it wraps the entire pipeline.
		if (config.getAuthStrategy().isPresent()) {
			AuthenticationStrategy strategy = config.getAuthStrategy().get();
			handler = new AuthenticationDecorator(handler, strategy);
		}

		return handler;
	}

}
