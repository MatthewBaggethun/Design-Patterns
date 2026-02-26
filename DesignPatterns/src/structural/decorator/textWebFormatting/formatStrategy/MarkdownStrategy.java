package structural.decorator.textWebFormatting.formatStrategy;

/**
 * Defines markdown type text decorators. Decorators can be applied by invoking
 * the required method. A StringBuilder is required for all method inputs.
 * Modifications are made to the char sequence within the StringBuilder inputs
 * such that the output char sequence is decorated appropriately.
 * 
 * @see FormattingStrategy
 * @see StringBuilder
 */
public class MarkdownStrategy implements FormattingStrategy {

	@Override
	public StringBuilder applyBold(StringBuilder text) {
		text.insert(0, "**");
		text.append("**");
		return text;
	}

	@Override
	public StringBuilder applyItalic(StringBuilder text) {
		text.insert(0, '*');
		text.append('*');
		return text;
	}

	@Override
	public StringBuilder applyStrikethrough(StringBuilder text) {
		text.insert(0, "~~");
		text.append("~~");
		return text;
	}

	@Override
	public StringBuilder applyInlineCode(StringBuilder text) {
		text.insert(0, '`');
		text.append('`');
		return text;
	}

	@Override
	public StringBuilder applyLink(StringBuilder text, StringBuilder url) {
		text.insert(0, '[');
		text.append("](" + url + ")");
		return text;
	}

	/**
	 * Markdown specific implementation of the highlight text decorator.
	 * 
	 * @param text Contains the char sequence to apply the text decorator to.
	 * @return The input StringBuilder with appropriate text decoration applied for
	 *         highlighting.
	 */
	public StringBuilder applyHighlight(StringBuilder text) {
		text.insert(0, "==");
		text.append("==");
		return text;
	}

	/**
	 * Markdown specific implementation of the image text decorator.
	 * 
	 * @param text          Contains the char sequence of the image name.
	 * @param imageLocation Contains the char sequence of the image location.
	 * @return The text StringBuilder modified to be used an a markdown image.
	 */
	public StringBuilder applyImage(StringBuilder text, StringBuilder imageLocation) {
		text.insert(0, "![");
		text.append("](" + imageLocation + ")");
		return text;
	}

}
