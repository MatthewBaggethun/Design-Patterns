package structural.decorator.httpPipeline.Strategy.Retry;

import java.time.Duration;

/**
 * Represents a retry policy that defines how many times to retry a failed HTTP
 * request and the backoff strategy to use between retries. The retry policy is
 * used by the RetryDecorator to determine the retry behavior for failed HTTP
 * requests. It includes a maximum number of retries and a backoff strategy that
 * determines the delay between retry attempts. The retry logic is applied only
 * to idempotent HTTP methods (e.g., GET, PUT, DELETE) to avoid unintended side
 * effects. This class provides factory methods for creating common retry
 * policies such as fixed delay and exponential backoff.
 * 
 * @see BackoffStrategy
 * @see RetryDecorator
 */
public final class RetryPolicy {

	private final int maxRetries;
	private final BackoffStrategy backoffStrategy;

	/**
	 * Constructs a RetryPolicy with the specified maximum number of retries and
	 * backoff strategy.
	 * 
	 * @param maxRetries      The maximum number of retry attempts. Must be
	 *                        non-negative.
	 * @param backoffStrategy The backoff strategy to use between retries. Must not
	 *                        be null.
	 * @throws IllegalArgumentException if maxRetries is negative or backoffStrategy
	 *                                  is null.
	 */
	public RetryPolicy(int maxRetries, BackoffStrategy backoffStrategy) {
		if (maxRetries < 0)
			throw new IllegalArgumentException("maxRetries >= 0 required");
		if (backoffStrategy == null)
			throw new IllegalArgumentException("backoffStrategy cannot be null");

		this.maxRetries = maxRetries;
		this.backoffStrategy = backoffStrategy;
	}

	/**
	 * Creates an instance of RetryPolicy with a fixed delay backoff strategy. The
	 * delay between retries will be constant, as specified by the delay parameter.
	 * 
	 * @param maxRetries Maximum number of retry attempts. Must be non-negative.
	 * @param delay      The fixed delay duration between retries. Must be
	 *                   non-negative.
	 * @return A RetryPolicy instance configured with a fixed delay backoff
	 *         strategy.
	 */
	public static RetryPolicy fixedDelay(int maxRetries, Duration delay) {
		return new RetryPolicy(maxRetries, new FixedDelayStrategy(delay));
	}

	/**
	 * Creates an instance of RetryPolicy with an exponential backoff strategy. The
	 * delay between retries will increase exponentially based on the initial delay
	 * and multiplier, up to a maximum delay.
	 * 
	 * @param maxRetries   Maximum number of retry attempts. Must be non-negative.
	 * @param initialDelay The initial delay duration before the first retry. Must
	 *                     be non-negative.
	 * @param multiplier   The factor by which the delay increases after each retry.
	 *                     Must be greater than or equal to 1.0.
	 * @param maxDelay     The maximum delay duration between retries. Must be
	 *                     non-negative.
	 * @return A RetryPolicy instance configured with an exponential backoff
	 *         strategy.
	 */
	public static RetryPolicy exponentialBackoff(int maxRetries, Duration initialDelay, double multiplier,
			Duration maxDelay) {
		return new RetryPolicy(maxRetries, new ExponentialBackoffStrategy(initialDelay, multiplier, maxDelay));
	}

	public int getMaxRetries() {
		return maxRetries;
	}

	public BackoffStrategy getBackoffStrategy() {
		return backoffStrategy;
	}

}
