package structural.decorator.textWebFormatting.formatStrategy;

public interface FormattingStrategy {

    StringBuilder applyBold(StringBuilder text);

    StringBuilder applyItalic(StringBuilder text);

    StringBuilder applyStrikethrough(StringBuilder text);

    StringBuilder applyInlineCode(StringBuilder text);

    StringBuilder applyLink(StringBuilder text, StringBuilder url);

}
