package behavioral.command.textEditor.Client;

import behavioral.command.textEditor.commands.Command;
import behavioral.command.textEditor.commands.textCommands.InsertTextAtEndCommand;
import behavioral.command.textEditor.invoker.CommandManager;
import behavioral.command.textEditor.receivers.Editor;
import behavioral.command.textEditor.receivers.Page;

public class TestHarness {

	public static void main(String[] args) {
		
		// Setup
		Editor editor = Editor.INSTANCE;
		Page pageOne = new Page("Page 1");
		Page pageTwo = new Page("Page 2");
		editor.addPage(pageOne);
		editor.addPage(pageTwo);
		editor.setCurrentPage(pageOne);
		
		// Small example
		CommandManager manager = new CommandManager();
		Command atEnd = new InsertTextAtEndCommand("At the end.");
		manager.executeCommand(atEnd);
		System.out.println(editor.toStringAllPages());
		manager.undo();
		System.out.println(editor.toStringAllPages());
		manager.redo(0);
		System.out.println(editor.toStringAllPages());
		pageOne.setPageName("Page 5");
		System.out.println(editor.toStringAllPages());

		// Try your own commands and undo/redo functionality

	}

}
