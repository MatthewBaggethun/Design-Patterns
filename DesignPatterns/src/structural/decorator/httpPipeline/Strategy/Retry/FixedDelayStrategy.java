package structural.decorator.httpPipeline.Strategy.Retry;

import java.time.Duration;

/**
 * A BackoffStrategy that always returns a fixed delay duration for each retry
 * attempt. This strategy can be used when a constant wait time is desired
 * between retries, regardless of the number of attempts made. The delay
 * duration is specified at the time of construction and must be non-null and
 * non-negative.
 * 
 * @see BackoffStrategy
 */
public final class FixedDelayStrategy implements BackoffStrategy {

	private final Duration delay;

	/**
	 * Constructs a FixedDelay strategy with the specified delay duration.
	 *
	 * @param delay the fixed delay duration to wait before each retry attempt. Must
	 *              not be null.
	 * @throws IllegalArgumentException if delay is null or negative.
	 */
	public FixedDelayStrategy(Duration delay) {
		if (delay == null || delay.isNegative())
			throw new IllegalArgumentException("Delay must be non-null and non-negative.");
		this.delay = delay;
	}

	@Override
	public Duration nextDelay(int attempt) {
		return delay;
	}

}
