package structural.decorator.textWebFormatting.builders;

import structural.decorator.textWebFormatting.formatStrategy.FormattingFactory;
import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;
import structural.decorator.textWebFormatting.htmlDecorators.h1Decorator;
import structural.decorator.textWebFormatting.htmlDecorators.pDecorator;
import structural.decorator.textWebFormatting.preformattedText.InputToFormat;
import structural.decorator.textWebFormatting.preformattedText.PlainText;
import structural.decorator.textWebFormatting.sharedDecorators.BoldDecorator;
import structural.decorator.textWebFormatting.sharedDecorators.InlineCodeDecorator;
import structural.decorator.textWebFormatting.sharedDecorators.ItalicDecorator;
import structural.decorator.textWebFormatting.sharedDecorators.LinkDecorator;
import structural.decorator.textWebFormatting.sharedDecorators.StrikethroughDecorator;

public class HtmlBuilder{
	
	private FormattingStrategy fs = FormattingFactory.HTML.instance();
	private StringBuilder sb;
	private InputToFormat wrappedText;
	
	public HtmlBuilder(String toFormat) {
		this.sb = new StringBuilder(toFormat);
		this.wrappedText = new PlainText(sb);
	}
	
	public HtmlBuilder applyBold() {
		wrappedText = new BoldDecorator(wrappedText);
		return this;
	}

	public HtmlBuilder applyInlineCode() {
		wrappedText = new InlineCodeDecorator(wrappedText);
		return this;
	}

	public HtmlBuilder applyItalic() {
		wrappedText = new ItalicDecorator(wrappedText);
		return this;
	}

	public HtmlBuilder applyLink(String url) {
		wrappedText = new LinkDecorator(wrappedText, new StringBuilder(url));
		return this;
	}

	public HtmlBuilder applyStrikethrough() {
		wrappedText = new StrikethroughDecorator(wrappedText);
		return this;
	}

	public HtmlBuilder applyHeader1() {
		wrappedText = new h1Decorator(wrappedText);
		return this;
	}
	
	public HtmlBuilder applyParagraph() {
		wrappedText = new pDecorator(wrappedText);
		return this;
	}
	
	public StringBuilder getFormatted() {
		return wrappedText.format(sb, fs);
	}

}
