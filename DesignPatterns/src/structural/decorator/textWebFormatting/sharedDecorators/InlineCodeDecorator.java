package structural.decorator.textWebFormatting.sharedDecorators;

import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;
import structural.decorator.textWebFormatting.preformattedText.AbstractFormatter;
import structural.decorator.textWebFormatting.preformattedText.InputToFormat;

/**
 * Wraps an InputToFormat object such that once the format method is called, the
 * resultant StringBuilder will be altered to apply inline code. The format
 * method will continue to delegate itself down the decorator chain until it
 * reaches the base component.
 */
public class InlineCodeDecorator extends AbstractFormatter {

	public InlineCodeDecorator(InputToFormat inputToFormat) {
		super(inputToFormat);
	}

	@Override
	public StringBuilder format(StringBuilder sb, FormattingStrategy formatStrategy) {
		return formatStrategy.applyInlineCode(super.format(sb, formatStrategy));
	}

}
