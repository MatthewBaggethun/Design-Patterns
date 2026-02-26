package structural.decorator.textWebFormatting.preformattedText;

import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;

/**
 * The base component in the decorator chain. This object is responsible for
 * holding the StringBuilder containing the char sequence that will be
 * decorated. The format method returns the StringBuilder.
 */
public class PlainText implements InputToFormat {

	private StringBuilder plainText;

	public PlainText(StringBuilder plainText) {
		this.plainText = plainText;
	}

	@Override
	public StringBuilder format(StringBuilder sb, FormattingStrategy formatStrategy) {
		return plainText;
	}

}
