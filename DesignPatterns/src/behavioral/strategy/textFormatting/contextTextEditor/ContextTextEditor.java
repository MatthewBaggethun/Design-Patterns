package behavioral.strategy.textFormatting.contextTextEditor;

import behavioral.strategy.textFormatting.formatStrategy.Format;

/**
 * Context class containing a Format strategy. Used to format Strings as defined
 * by the Format provided in the constructor.
 */
public class ContextTextEditor {
	private Format formatter;
	
	/**
	 * Defines the formatting strategy. Call formatWithStrategy to return the formatted String.
	 * @param format The formatting strategy which will be used.
	 */
	public ContextTextEditor(Format format) {
		this.formatter = format;
	}
	
	public void setFormatter(Format format) {
		this.formatter = format;
	}

	/**
	 * Utilize the formatter to format an input.
	 * @param toFormat The text to be formatted as a String.
	 * @return A String with formatting applied.
	 */
	public String formatWithStrategy(String toFormat) {
		return this.formatter.format(toFormat);
	}
}
