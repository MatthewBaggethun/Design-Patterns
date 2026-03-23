package behavioral.command.textEditor.commands;

/**
 * Utilizes the null object pattern to create a null Command object, which
 * contains no concrete implementation. A NoCommand object can be used in place
 * of null to avoid null checks.
 */
public class NoCommand implements Command {

	@Override
	public void execute() {
	}

	@Override
	public void undo() {
	}

}
