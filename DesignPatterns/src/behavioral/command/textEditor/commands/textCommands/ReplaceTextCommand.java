package behavioral.command.textEditor.commands.textCommands;

import behavioral.command.textEditor.commands.Command;
import behavioral.command.textEditor.receivers.Editor;

public class ReplaceTextCommand implements Command {

	Editor editor = Editor.INSTANCE;
	private int textStartIndex;
	private int textEndIndex;
	private String newText;
	private String replacedText;

	public ReplaceTextCommand(int textStartIndex, int textEndIndex, String newText) {
		this.textStartIndex = textStartIndex;
		this.textEndIndex = textEndIndex;
		this.newText = newText;
	}

	@Override
	public void execute() {
		replacedText = editor.replaceText(textStartIndex, textEndIndex, newText);
	}

	@Override
	public void undo() {
		editor.replaceText(textStartIndex, newText.length() + textStartIndex, replacedText);
	}

}
