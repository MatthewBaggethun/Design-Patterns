package structural.decorator.httpPipeline.Strategy.Retry;

import java.time.Duration;

/**
 * A strategy for determining the backoff duration between retry attempts. This
 * interface can be implemented to provide different backoff strategies, such as
 * fixed delay or exponential backoff. The nextDelay method takes the current
 * retry attempt (0-based) as input and returns the Duration to wait before the
 * next retry attempt. This allows for flexible and customizable retry behavior
 * in HTTP request handling.
 * 
 * @see RetryDecorator
 */
public interface BackoffStrategy {

	/**
	 * Returns the delay before the next retry attempt.
	 *
	 * @param delay the 0-based retry attempt
	 * @return Duration to wait before retry
	 */
	Duration nextDelay(int delay);

}
