package structural.decorator.textWebFormatting.preformattedText;

import structural.decorator.textWebFormatting.formatStrategy.FormattingStrategy;

/**
 * Defines the format method, responsible for modifying a StringBuilder by
 * applying formatting as defined by a FormattingStrategy. The StringBuilder
 * object holds the wrapped object and its context class containing the string
 * to apply formatting to. The FormattingStrategy will dictate the type of
 * formatting methods available.
 * 
 * Concrete classes should maintain a state variable to hold this object. The
 * Decorator Pattern can be utilized to apply multiple formattings in a
 * specified order.
 * 
 * Lambdas can be utilized to instantiate custom implementations.
 * 
 * @see FormattingStrategy
 * @see StringBuilder
 * @see PlainText
 */
@FunctionalInterface
public interface InputToFormat {

	/**
	 * Applies formatting as defined by the FormattingStrategy method invocation.
	 * Formatting is applied to a StringBuilder to avoid unnecessary String
	 * instantiations. This method is intended to be invoked sequentially via the
	 * decorator pattern.
	 * 
	 * @param sb             The StringBuilder containing the char sequence to be
	 *                       formatted.
	 * @param formatStrategy Defines which method invocations are applicable to
	 *                       format the char sequence.
	 * @return Returns a StringBuilder containing a formatted char sequence.
	 */
	public StringBuilder format(StringBuilder sb, FormattingStrategy formatStrategy);

}
	