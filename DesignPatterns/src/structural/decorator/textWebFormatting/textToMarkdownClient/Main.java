package structural.decorator.textWebFormatting.textToMarkdownClient;

import structural.decorator.textWebFormatting.builders.HtmlBuilder;
import structural.decorator.textWebFormatting.builders.MarkdownBuilder;
import structural.decorator.textWebFormatting.formatStrategy.FormattingFactory;
import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;
import structural.decorator.textWebFormatting.htmlDecorators.h1Decorator;
import structural.decorator.textWebFormatting.htmlDecorators.pDecorator;
import structural.decorator.textWebFormatting.markdownDecorators.HighlightDecorator;
import structural.decorator.textWebFormatting.markdownDecorators.ImageDecorator;
import structural.decorator.textWebFormatting.preformattedText.PlainText;
import structural.decorator.textWebFormatting.sharedDecorators.BoldDecorator;
import structural.decorator.textWebFormatting.sharedDecorators.InlineCodeDecorator;
import structural.decorator.textWebFormatting.sharedDecorators.ItalicDecorator;
import structural.decorator.textWebFormatting.sharedDecorators.LinkDecorator;
import structural.decorator.textWebFormatting.sharedDecorators.StrikethroughDecorator;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("---Markdown Decorator Example---" + "\n");
		
		applyMarkdown();
		
		System.out.println("\n" + "---HTML Decorator Example---" + "\n");
		
		applyHtml();
		
		System.out.println("\n" + "---Markdown Builder Example---" + "\n");
		
		applyMarkdownWithBuilder();
		
		System.out.println("\n" + "---HTML Builder Example---" + "\n");
		
		applyHtmlWithBuilder();
	}
	
	private static void applyMarkdown() {

		// Instantiate required objects to apply decorator pattern.
		FormattingStrategy markdownStrategy = FormattingFactory.MARKDOWN.instance();
		StringBuilder sb = new StringBuilder("Hello");
		PlainText text = new PlainText(sb);

		// Decorate
		BoldDecorator bold = new BoldDecorator(text);
		ItalicDecorator italic = new ItalicDecorator(bold);
		StrikethroughDecorator strikethrough = new StrikethroughDecorator(italic);
		InlineCodeDecorator inlineCode = new InlineCodeDecorator(strikethrough);
		HighlightDecorator highlight = new HighlightDecorator(inlineCode);

		System.out.println(highlight.format(sb, markdownStrategy));

		// Instantiate new PlainText object that will be used as a link.
		PlainText linkHypertext = new PlainText(new StringBuilder("Click Here!"));
		StringBuilder linkBuilder = new StringBuilder("123.com");
		
		// Decorate
		LinkDecorator link = new LinkDecorator(linkHypertext, linkBuilder);
		System.out.println(link.format(new StringBuilder("LinkTest"), markdownStrategy));

		// Instantiate new PlainText object that will be used as an image.
		PlainText imageHypertext = new PlainText(new StringBuilder("This is my image"));
		StringBuilder imageUrl = new StringBuilder("my/image/is/here");
		
		// Decorate
		ImageDecorator image = new ImageDecorator(imageHypertext, imageUrl);
		System.out.println(image.format(new StringBuilder("ImageTest"), markdownStrategy));
		
	}
	
	private static void applyHtml() {
		
		// Instantiate required objects to apply decorator pattern. 
		FormattingStrategy htmlStrategy = FormattingFactory.HTML.instance();
		StringBuilder header = new StringBuilder("My Header");
		PlainText text = new PlainText(header);

		// Decorate
		h1Decorator h1 = new h1Decorator(text);
		System.out.println(h1.format(header, htmlStrategy));
		
		// Instantiate new PlainText object that will be used as a paragraph.
		StringBuilder paragraph = new StringBuilder("Lorem Ipsum dolor set amet....");
		PlainText paragraphText = new PlainText(paragraph);
		
		// Decorate
		pDecorator p = new pDecorator(paragraphText);
		System.out.println(p.format(paragraph, htmlStrategy));
		
		// Instantiate new PlainText object that will be used as a link.
		PlainText linkHypertext = new PlainText(new StringBuilder("Click Here!"));
		StringBuilder linkBuilder = new StringBuilder("123.com");
		
		// Decorate
		LinkDecorator link = new LinkDecorator(linkHypertext, linkBuilder);
		System.out.println(link.format(new StringBuilder("LinkTest"), htmlStrategy));
		
	}
	
	private static void applyMarkdownWithBuilder() {
		
		MarkdownBuilder builder = new MarkdownBuilder("Hello");
		
		System.out.println(builder.applyBold().applyItalic().applyStrikethrough().applyInlineCode().applyHighlight().getFormatted());
		
	}
	
	private static void applyHtmlWithBuilder() {
		
		HtmlBuilder headerBuilder = new HtmlBuilder("Welcome to my website!");
		StringBuilder header = headerBuilder.applyHeader1().getFormatted();
		
		HtmlBuilder paragraphBuilder = new HtmlBuilder("Lorem Ipsum dolor set amet....");
		StringBuilder paragraph = paragraphBuilder.applyParagraph().getFormatted();
		
		HtmlBuilder linkBuilder = new HtmlBuilder("Click this awesome link!");
		StringBuilder link = linkBuilder.applyLink("mylink.com").getFormatted();
		
		System.out.println(header + "\n" + paragraph + "\n" + link);

	}

}
