package behavioral.strategy.textFormatting.formatStrategy;

public class FormatLowerCase implements Format{

	/*
	 * The input will be formatted to lower case.
	 */
	@Override
	public String format(String toFormat) {
		return toFormat.toLowerCase();
	}

}
