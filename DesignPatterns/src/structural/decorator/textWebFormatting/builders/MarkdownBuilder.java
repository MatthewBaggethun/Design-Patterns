package structural.decorator.textWebFormatting.builders;

import structural.decorator.textWebFormatting.formatStrategy.FormattingFactory;
import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;
import structural.decorator.textWebFormatting.markdownDecorators.HighlightDecorator;
import structural.decorator.textWebFormatting.markdownDecorators.ImageDecorator;
import structural.decorator.textWebFormatting.preformattedText.InputToFormat;
import structural.decorator.textWebFormatting.preformattedText.PlainText;
import structural.decorator.textWebFormatting.sharedDecorators.BoldDecorator;
import structural.decorator.textWebFormatting.sharedDecorators.InlineCodeDecorator;
import structural.decorator.textWebFormatting.sharedDecorators.ItalicDecorator;
import structural.decorator.textWebFormatting.sharedDecorators.LinkDecorator;
import structural.decorator.textWebFormatting.sharedDecorators.StrikethroughDecorator;

public class MarkdownBuilder {

	private FormattingStrategy fs = FormattingFactory.MARKDOWN.instance();
	private StringBuilder sb;
	private InputToFormat wrappedText;

	public MarkdownBuilder(String toFormat) {
		this.sb = new StringBuilder(toFormat);
		this.wrappedText = new PlainText(sb);
	}

	public MarkdownBuilder applyBold() {
		wrappedText = new BoldDecorator(wrappedText);
		return this;
	}

	public MarkdownBuilder applyInlineCode() {
		wrappedText = new InlineCodeDecorator(wrappedText);
		return this;
	}

	public MarkdownBuilder applyItalic() {
		wrappedText = new ItalicDecorator(wrappedText);
		return this;
	}

	public MarkdownBuilder applyLink(String url) {
		wrappedText = new LinkDecorator(wrappedText, new StringBuilder(url));
		return this;
	}

	public MarkdownBuilder applyStrikethrough() {
		wrappedText = new StrikethroughDecorator(wrappedText);
		return this;
	}

	public MarkdownBuilder applyHighlight() {
		wrappedText = new HighlightDecorator(wrappedText);
		return this;
	}

	public MarkdownBuilder applyImage(String url) {
		wrappedText = new ImageDecorator(wrappedText, new StringBuilder(url));
		return this;
	}

	public StringBuilder getFormatted() {
		return wrappedText.format(sb, fs);
	}

}