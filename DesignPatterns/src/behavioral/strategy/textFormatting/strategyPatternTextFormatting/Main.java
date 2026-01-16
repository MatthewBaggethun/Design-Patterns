package behavioral.strategy.textFormatting.strategyPatternTextFormatting;

import behavioral.strategy.textFormatting.formatStrategy.Format;
import behavioral.strategy.textFormatting.formatStrategy.FormatLowerCase;
import behavioral.strategy.textFormatting.formatStrategy.FormatUpperCase;
import behavioral.strategy.textFormatting.textEditor.TextEditor;

public class Main {

	public static void main(String[] args) {
		
		Format formatUpper = new FormatUpperCase();
		Format formatLower = new FormatLowerCase();
		
		TextEditor textEditor = new TextEditor(formatUpper);
		
		System.out.println(textEditor.formatWithStrategy("format me to upper please."));
		
		textEditor.setFormatter(formatLower);
		
		System.out.println(textEditor.formatWithStrategy("FORMAT ME TO LOWER PLEASE."));

	}

}
