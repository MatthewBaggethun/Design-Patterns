package behavioral.strategy.textFormatting.formatStrategy;

public class FormatUpperCase implements Format{

	/**
	 * The input will be formatted into upper case.
	 */
	@Override
	public String format(String toFormat) {
		return toFormat.toUpperCase();
	}

}
