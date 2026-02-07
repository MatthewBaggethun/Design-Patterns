package structural.decorator.httpPipeline.Factory;

import java.time.Duration;
import java.util.Optional;

import structural.decorator.httpPipeline.Strategy.Auth.AuthenticationStrategy;
import structural.decorator.httpPipeline.Strategy.Retry.BackoffStrategy;
import structural.decorator.httpPipeline.Strategy.Retry.FixedDelayStrategy;

/**
 * A configuration class for setting up an HTTP pipeline with various options
 * such as logging, retry policies, timeouts, and authentication strategies.
 * This class uses the Builder pattern to allow for flexible and readable
 * configuration of the pipeline. It encapsulates all the necessary settings
 * that can be applied to an HTTP pipeline, making it easier to create and
 * manage complex pipelines with different behaviors based on the provided
 * configuration. The PipelineConfig class is immutable, ensuring that once a
 * configuration is created, it cannot be modified, which helps maintain
 * consistency and thread safety when used in concurrent environments.
 * 
 * @see PipelineFactory
 * @see Builder
 */
public final class PipelineConfig {

	// Logging
	private final boolean enableLogging;

	// Retry
	private final boolean enableRetry;
	private final int retryAttempts;
	private final BackoffStrategy backoffStrategy;

	// Timeout
	private final boolean enableTimeout;
	private final Duration timeout;

	// Authentication
	private final Optional<AuthenticationStrategy> authStrategy;

	/**
	 * Private constructor that initializes the PipelineConfig with the settings
	 * provided by the Builder. This constructor is called by the Builder's build()
	 * method to create an instance of PipelineConfig with the specified
	 * configuration options.
	 * 
	 * @param builder The Builder instance containing the configuration settings for
	 *                the pipeline. The Builder provides an interface for setting
	 *                various options such as logging, retry policies, timeouts, and
	 *                authentication strategies.
	 */
	private PipelineConfig(Builder builder) {
		// Logging
		this.enableLogging = builder.enableLogging;

		// Retry
		this.enableRetry = builder.enableRetry;
		this.retryAttempts = builder.retryAttempts;
		this.backoffStrategy = builder.backoffStrategy;

		// Timeout
		this.enableTimeout = builder.enableTimeout;
		this.timeout = builder.timeout;

		// Authentication
		this.authStrategy = builder.authStrategy;
	}

	public boolean isEnableLogging() {
		return enableLogging;
	}

	public boolean isEnableRetry() {
		return enableRetry;
	}

	public boolean isEnableTimeout() {
		return enableTimeout;
	}

	public int getRetryAttempts() {
		return retryAttempts;
	}

	public BackoffStrategy getBackoffStrategy() {
		return backoffStrategy;
	}

	public Duration getTimeout() {
		return timeout;
	}

	public Optional<AuthenticationStrategy> getAuthStrategy() {
		return authStrategy;
	}

	/**
	 * A Builder class for constructing instances of PipelineConfig with an
	 * interface. This Builder allows for setting various configuration options for
	 * the HTTP pipeline, such as enabling logging, configuring retry policies,
	 * setting timeouts, and specifying authentication strategies. The Builder
	 * provides methods for each configuration option, allowing for a clear and
	 * concise way to create a PipelineConfig instance with the desired settings.
	 * Once all the desired options are set, the build() method can be called to
	 * create an immutable PipelineConfig instance that can be used to configure an
	 * HTTP pipeline.
	 * 
	 * @see PipelineConfig
	 */
	public static class Builder {

		// Logging
		private boolean enableLogging = false;

		// Retry
		private boolean enableRetry = false;
		private int retryAttempts = 3;
		private BackoffStrategy backoffStrategy = new FixedDelayStrategy(Duration.ofSeconds(1));

		// Timeout
		private boolean enableTimeout = false;
		private Duration timeout = Duration.ofSeconds(5);

		// Authentication
		private Optional<AuthenticationStrategy> authStrategy = Optional.empty();

		/**
		 * Builder method to enable logging in the HTTP pipeline. This method allows the
		 * client to set the enableLogging flag to true, allowing the pipeline to log
		 * request and response details.
		 * 
		 * @param enable A boolean value indicating whether to enable logging. If set to
		 *               true, the pipeline will log relevant information about HTTP
		 *               requests and responses, which can be useful for debugging and
		 *               monitoring purposes.
		 * @return The Builder instance with logging enabled, allowing for method
		 *         chaining when configuring the PipelineConfig.
		 */
		public Builder enableLogging(boolean enable) {
			this.enableLogging = enable;
			return this;
		}

		/**
		 * Builder method to enable retry functionality in the HTTP pipeline. This
		 * method allows the client to set the enableRetry flag to true and specify the
		 * number of retry attempts. When enabled, the pipeline will automatically retry
		 * failed requests based on the configured retry policy and backoff strategy.
		 * 
		 * @param attempts An integer value specifying the number of retry attempts to
		 *                 make when a request fails. If set to a positive integer, the
		 *                 pipeline will attempt to retry failed requests up to the
		 *                 specified number of times, using the configured backoff
		 *                 strategy between attempts.
		 * @return The Builder instance with retry enabled and the specified number of
		 *         attempts, allowing for method chaining when configuring the
		 *         PipelineConfig.
		 */
		public Builder enableRetry(int attempts) {
			this.enableRetry = true;
			this.retryAttempts = attempts;
			return this;
		}

		/**
		 * Builder method to set the backoff strategy for retrying failed requests in
		 * the HTTP pipeline. This method allows the client to specify a custom
		 * BackoffStrategy that defines how the pipeline should wait between retry
		 * attempts when a request fails. The backoff strategy can be used to implement
		 * various retry policies, such as fixed delay, exponential backoff, or any
		 * custom strategy that implements the BackoffStrategy interface.
		 * 
		 * @param strategy An instance of a class that implements the BackoffStrategy
		 *                 interface, defining the logic for calculating the delay
		 *                 between retry attempts when a request fails.
		 * @return The Builder instance with the specified backoff strategy, allowing
		 *         for method chaining when configuring the PipelineConfig.
		 */
		public Builder withBackoffStrategy(BackoffStrategy strategy) {
			this.backoffStrategy = strategy;
			return this;
		}

		/**
		 * Builder method to enable timeout functionality in the HTTP pipeline. This
		 * method allows the client to set the enableTimeout flag to true and specify a
		 * timeout duration. When enabled, the pipeline will enforce a timeout for each
		 * request, ensuring that requests that take longer than the specified duration
		 * will be aborted and treated as failed.
		 * 
		 * @param timeout A Duration object specifying the timeout duration for each
		 *                request. If set to a positive duration, the pipeline will
		 *                enforce a timeout for requests, aborting any request that
		 *                takes longer than the specified duration and treating it as a
		 *                failure.
		 * @return The Builder instance with timeout enabled and the specified duration,
		 *         allowing for method chaining when configuring the PipelineConfig.
		 */
		public Builder enableTimeout(Duration timeout) {
			this.enableTimeout = true;
			this.timeout = timeout;
			return this;
		}

		/**
		 * Builder method to set the authentication strategy for the HTTP pipeline. This
		 * method allows the client to specify an AuthenticationStrategy that defines
		 * how the pipeline should handle authentication for requests. When an
		 * authentication strategy is provided, the pipeline will apply the specified
		 * authentication mechanism to outgoing requests, allowing for secure access to
		 * protected resources.
		 * 
		 * @param strategy An instance of a class that implements the
		 *                 AuthenticationStrategy interface, defining the logic for
		 *                 applying authentication to outgoing requests in the HTTP
		 *                 pipeline.
		 * @return The Builder instance with the specified authentication strategy,
		 *         allowing for method chaining when configuring the PipelineConfig.
		 */
		public Builder authStrategy(AuthenticationStrategy strategy) {
			this.authStrategy = Optional.of(strategy);
			return this;
		}

		/**
		 * Builds and returns an instance of PipelineConfig based on the settings
		 * provided to the Builder. This method creates a new PipelineConfig object,
		 * passing the Builder instance to its constructor, which initializes the
		 * PipelineConfig with the specified configuration options. The resulting
		 * PipelineConfig instance is immutable and can be used to configure an HTTP
		 * pipeline with the desired behaviors such as logging, retries, timeouts, and
		 * authentication.
		 * 
		 * @return A new instance of PipelineConfig initialized with the settings
		 *         provided to the Builder. This instance can be used to configure an
		 *         HTTP pipeline with the specified options.
		 */
		public PipelineConfig build() {
			return new PipelineConfig(this);
		}

	}

}
