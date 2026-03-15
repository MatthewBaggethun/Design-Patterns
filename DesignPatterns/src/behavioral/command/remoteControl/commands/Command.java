package behavioral.command.remoteControl.commands;

/**
 * The Command interface declares a method for executing a command and a method
 * for undoing it. Concrete Command classes will implement this interface and
 * define their own execute and undo methods utilizing receiver objects to
 * perform those actions.
 */
public interface Command {

	/**
	 * Responsible for executing a command by invoking the appropriate action on the
	 * receiver object.
	 */
	public void execute();

	/**
	 * Responsible for undoing a command by invoking the appropriate actions on the
	 * receiver object.
	 */
	public void undo();

}
