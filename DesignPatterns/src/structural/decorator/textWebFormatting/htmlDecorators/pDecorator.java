package structural.decorator.textWebFormatting.htmlDecorators;

import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;
import structural.decorator.textWebFormatting.formatStrategy.HtmlStrategy;
import structural.decorator.textWebFormatting.preformattedText.AbstractFormatter;
import structural.decorator.textWebFormatting.preformattedText.InputToFormat;

public class pDecorator extends AbstractFormatter{

	public pDecorator(InputToFormat inputToFormat) {
		super(inputToFormat);
	}
	
	@Override
	public StringBuilder format(StringBuilder sb, FormattingStrategy formatStrategy) {
		
		return formatStrategy instanceof HtmlStrategy htmlStrategy
				? htmlStrategy.applyParagraph(super.format(sb, formatStrategy))
				: super.format(sb, formatStrategy);
		
	}

}
