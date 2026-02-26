package structural.decorator.textWebFormatting.htmlDecorators;

import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;
import structural.decorator.textWebFormatting.formatStrategy.HtmlStrategy;
import structural.decorator.textWebFormatting.preformattedText.AbstractFormatter;
import structural.decorator.textWebFormatting.preformattedText.InputToFormat;

public class h1Decorator extends AbstractFormatter{

	public h1Decorator(InputToFormat inputToFormat) {
		super(inputToFormat);
	}
	
	@Override
	public StringBuilder format(StringBuilder sb, FormattingStrategy formatStrategy) {
		
		return formatStrategy instanceof HtmlStrategy htmlStrategy
				? htmlStrategy.applyH1(super.format(sb, formatStrategy))
				: super.format(sb, formatStrategy);
	}

}
