package structural.decorator.httpPipeline.Strategy.Retry;

import java.time.Duration;

/**
 * Implements an exponential backoff strategy for retrying failed HTTP requests.
 * The delay between retries increases exponentially based on the initial delay
 * and a multiplier. The backoff duration is capped at a maximum delay to prevent
 * excessively long wait times. This strategy is commonly used to handle transient
 * errors in network communication, allowing for graceful recovery while avoiding
 * overwhelming the server with rapid retries.
 * 
 * @see RetryPolicy
 */
public final class ExponentialBackoffStrategy implements BackoffStrategy {

	private final Duration initialDelay;
	private final double multiplier;
	private final Duration maxDelay;

	/**
	 * Constructs an ExponentialBackoff strategy with the specified initial delay,
	 * multiplier, and maximum delay.
	 * 
	 * @param initialDelay The initial delay before the first retry. Must be
	 *                     non-negative.
	 * @param multiplier   The factor by which the delay increases after each retry.
	 *                     Must be greater than or equal to 1.0.
	 * @param maxDelay     The maximum delay between retries. Must be non-negative.
	 * @throws IllegalArgumentException if initialDelay is negative, multiplier is
	 *                                  less than 1.0, or maxDelay is negative.
	 */
	public ExponentialBackoffStrategy(Duration initialDelay, double multiplier, Duration maxDelay) {
		if (initialDelay.isNegative())
			throw new IllegalArgumentException("Initial delay must be non-negative");
		if (multiplier < 1.0)
			throw new IllegalArgumentException("Multiplier must be >= 1.0");
		if (maxDelay.isNegative())
			throw new IllegalArgumentException("Max delay must be non-negative");

		this.initialDelay = initialDelay;
		this.multiplier = multiplier;
		this.maxDelay = maxDelay;
	}

	@Override
	public Duration nextDelay(int attempt) {
		double delayMillis = initialDelay.toMillis() * Math.pow(multiplier, attempt);
		long boundedMillis = Math.min((long) delayMillis, maxDelay.toMillis());
		return Duration.ofMillis(boundedMillis);
	}

}
