package structural.decorator.textWebFormatting.preformattedText;

import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;

/**
 * Extends the InputToFormat interface for later use by decorators. Extensions
 * of this class pass formatting along to a root InputToFormat object. This is a
 * prerequisite requirement for decorator pattern.
 * 
 * @see InputToFormat
 */
public abstract class AbstractFormatter implements InputToFormat {

	private final InputToFormat inputToFormat;

	protected AbstractFormatter(InputToFormat inputToFormat) {
		this.inputToFormat = inputToFormat;
	}

	@Override
	public StringBuilder format(StringBuilder sb, FormattingStrategy formatStrategy) {
		return inputToFormat.format(sb, formatStrategy);
	}

}
