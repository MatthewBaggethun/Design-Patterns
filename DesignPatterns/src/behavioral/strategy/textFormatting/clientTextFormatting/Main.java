package behavioral.strategy.textFormatting.clientTextFormatting;

import behavioral.strategy.textFormatting.contextTextEditor.ContextTextEditor;
import behavioral.strategy.textFormatting.formatStrategy.Format;
import behavioral.strategy.textFormatting.formatStrategy.FormatLowerCase;
import behavioral.strategy.textFormatting.formatStrategy.FormatUpperCase;

public class Main {

	public static void main(String[] args) {
		
		Format formatUpper = new FormatUpperCase();
		Format formatLower = new FormatLowerCase();
		
		ContextTextEditor textEditor = new ContextTextEditor(formatUpper);
		
		System.out.println(textEditor.formatWithStrategy("format me to upper please."));
		
		textEditor.setFormatter(formatLower);
		
		System.out.println(textEditor.formatWithStrategy("FORMAT ME TO LOWER PLEASE."));

	}

}
