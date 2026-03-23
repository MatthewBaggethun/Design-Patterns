package behavioral.command.textEditor.commands.textCommands;

import behavioral.command.textEditor.commands.Command;
import behavioral.command.textEditor.receivers.Editor;

public class InsertTextAtEndCommand implements Command{
	
	Editor editor = Editor.INSTANCE;
	private String newText;
	private int textStartIndex;
	
	public InsertTextAtEndCommand(String newText) {
		this.textStartIndex = editor.getCurrentPage().getPageStringBuilder().length();
		this.newText = newText;
	}

	@Override
	public void execute() {
		editor.insertText(newText);
	}

	@Override
	public void undo() {
		editor.deleteText(textStartIndex, newText.length());
	}

}
