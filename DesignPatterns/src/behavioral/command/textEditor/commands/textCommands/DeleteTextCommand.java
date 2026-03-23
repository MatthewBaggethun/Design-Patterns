package behavioral.command.textEditor.commands.textCommands;

import behavioral.command.textEditor.commands.Command;
import behavioral.command.textEditor.receivers.Editor;

public class DeleteTextCommand implements Command{
	
	Editor editor = Editor.INSTANCE;
	private int deletionStartIndex;
	private int deletionLength;
	private String deletedText;
	
	public DeleteTextCommand(int deletionStartIndex, int deletionLength) {
		this.deletionStartIndex = deletionStartIndex;
		this.deletionLength = deletionLength;
	}
	

	@Override
	public void execute() {
		deletedText = editor.deleteText(deletionStartIndex, deletionLength);
	}

	@Override
	public void undo() {
		editor.insertText(deletionStartIndex, deletedText);
	}

}
