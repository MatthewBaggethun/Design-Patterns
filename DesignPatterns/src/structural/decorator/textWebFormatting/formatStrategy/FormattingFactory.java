package structural.decorator.textWebFormatting.formatStrategy;

import java.util.function.Supplier;

/**
 * 
 */
public enum FormattingFactory {

	MARKDOWN(MarkdownStrategy::new),
	HTML(HtmlStrategy::new)
	;

	private final Supplier<FormattingStrategy> constructor;

	FormattingFactory(Supplier<FormattingStrategy> constructor) {
		this.constructor = constructor;
	}

	public FormattingStrategy instance() {
		return constructor.get();
	}
}
