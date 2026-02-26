package structural.decorator.textWebFormatting.sharedDecorators;

import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;
import structural.decorator.textWebFormatting.preformattedText.AbstractFormatter;
import structural.decorator.textWebFormatting.preformattedText.InputToFormat;

/**
 * Wraps an InputToFormat object such that once the format method is called, the
 * resultant StringBuilder will be altered to apply bold. The format method will
 * continue to delegate itself down the decorator chain until it reaches the
 * base component.
 */
public class BoldDecorator extends AbstractFormatter {

	public BoldDecorator(InputToFormat inputToFormat) {
		super(inputToFormat);
	}

	@Override
	public StringBuilder format(StringBuilder sb, FormattingStrategy formatStrategy) {
		return formatStrategy.applyBold(super.format(sb, formatStrategy));
	}

}
