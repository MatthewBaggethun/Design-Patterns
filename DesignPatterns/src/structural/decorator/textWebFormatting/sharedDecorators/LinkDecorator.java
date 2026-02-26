package structural.decorator.textWebFormatting.sharedDecorators;

import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;
import structural.decorator.textWebFormatting.preformattedText.AbstractFormatter;
import structural.decorator.textWebFormatting.preformattedText.InputToFormat;

public class LinkDecorator extends AbstractFormatter{
	
	StringBuilder imageUrl;

	public LinkDecorator(InputToFormat inputToFormat, StringBuilder imageUrl) {
		super(inputToFormat);
		this.imageUrl = imageUrl;
	}

	@Override
	public StringBuilder format(StringBuilder sb, FormattingStrategy formatStrategy) {
		return formatStrategy.applyLink(super.format(sb, formatStrategy), imageUrl);
	}

}
