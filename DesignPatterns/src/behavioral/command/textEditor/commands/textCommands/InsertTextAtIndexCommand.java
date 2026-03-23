package behavioral.command.textEditor.commands.textCommands;

import behavioral.command.textEditor.commands.Command;
import behavioral.command.textEditor.receivers.Editor;

public class InsertTextAtIndexCommand implements Command{
	
	Editor editor = Editor.INSTANCE;
	private String newText;
	private int textStartIndex;
	
	public InsertTextAtIndexCommand(int textStartIndex, String newText) {
		this.textStartIndex = textStartIndex;
		this.newText = newText;
	}

	@Override
	public void execute() {
		editor.insertText(textStartIndex, newText);
	}

	@Override
	public void undo() {
		editor.deleteText(textStartIndex, newText.length());
	}

}
