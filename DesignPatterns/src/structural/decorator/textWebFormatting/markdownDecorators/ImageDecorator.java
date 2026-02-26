package structural.decorator.textWebFormatting.markdownDecorators;

import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;
import structural.decorator.textWebFormatting.formatStrategy.MarkdownStrategy;
import structural.decorator.textWebFormatting.preformattedText.AbstractFormatter;
import structural.decorator.textWebFormatting.preformattedText.InputToFormat;

public class ImageDecorator extends AbstractFormatter {

	private StringBuilder imageUrl;

	public ImageDecorator(InputToFormat inputToFormat, StringBuilder imageUrl) {
		super(inputToFormat);
		this.imageUrl = imageUrl;
	}

	@Override
	public StringBuilder format(StringBuilder sb, FormattingStrategy formatStrategy) {

		return formatStrategy instanceof MarkdownStrategy markdownStrategy
				? markdownStrategy.applyImage(super.format(sb, formatStrategy), imageUrl)
				: super.format(sb, formatStrategy);
		
	}

}
