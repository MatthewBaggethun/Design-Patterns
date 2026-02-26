package structural.decorator.textWebFormatting.sharedDecorators;

import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;
import structural.decorator.textWebFormatting.preformattedText.AbstractFormatter;
import structural.decorator.textWebFormatting.preformattedText.InputToFormat;

public class ItalicDecorator extends AbstractFormatter{

	public ItalicDecorator(InputToFormat inputToFormat) {
		super(inputToFormat);
	}

	@Override
	public StringBuilder format(StringBuilder sb, FormattingStrategy formatStrategy) {
		return formatStrategy.applyItalic(super.format(sb, formatStrategy));
	}

}
