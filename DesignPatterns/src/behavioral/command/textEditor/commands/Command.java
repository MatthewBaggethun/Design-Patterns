package behavioral.command.textEditor.commands;

/**
 * Following the Command Pattern, this interface is intended to be used to
 * promote receiver methods into first class objects. More specifically,
 * concrete classes will utilize this interface to encapsulate the text editing
 * methods found within Editor.
 * 
 * @see Editor
 */
public interface Command {

	/**
	 * Performs a receiver method.
	 */
	public void execute();

	/**
	 * Performs an inverse operation to revert back to a state before the execute
	 * command was invoked.
	 */
	public void undo();

}
