package structural.decorator.textWebFormatting.formatStrategy;

public class HtmlStrategy implements FormattingStrategy{

	@Override
	public StringBuilder applyBold(StringBuilder text) {
		return text.insert(0, "<b>").append("</b>");
	}

	@Override
	public StringBuilder applyItalic(StringBuilder text) {
		return text.insert(0, "<i>").append("</i>");
	}

	@Override
	public StringBuilder applyStrikethrough(StringBuilder text) {
		return text.insert(0, "<s>").append("</s>");
	}

	@Override
	public StringBuilder applyInlineCode(StringBuilder text) {
		return text.insert(0, "<code>").append("</code>");
	}

	@Override
	public StringBuilder applyLink(StringBuilder text, StringBuilder url) {
		return text.insert(0, "<a href=\"" + url + "\">").append("</a>");
	}
	
	public StringBuilder applyH1(StringBuilder text) {
		return text.insert(0, "<h1>").append("</h1>");
	}
	
	public StringBuilder applyParagraph(StringBuilder text) {
		return text.insert(0, "<p>").append("</p>");
	}

}
