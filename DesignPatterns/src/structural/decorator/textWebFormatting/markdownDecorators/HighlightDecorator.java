package structural.decorator.textWebFormatting.markdownDecorators;

import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;
import structural.decorator.textWebFormatting.formatStrategy.MarkdownStrategy;
import structural.decorator.textWebFormatting.preformattedText.AbstractFormatter;
import structural.decorator.textWebFormatting.preformattedText.InputToFormat;

public class HighlightDecorator extends AbstractFormatter {

	public HighlightDecorator(InputToFormat inputToFormat) {
		super(inputToFormat);
	}

	@Override
	public StringBuilder format(StringBuilder sb, FormattingStrategy formatStrategy) {

		return formatStrategy instanceof MarkdownStrategy markdownStrategy
				? markdownStrategy.applyHighlight(super.format(sb, formatStrategy))
				: super.format(sb, formatStrategy);
		
	}

}
