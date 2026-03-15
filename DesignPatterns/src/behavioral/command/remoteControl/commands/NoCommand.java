package behavioral.command.remoteControl.commands;

/**
 * Utilizes the Null Object Pattern to avoid null checks in the invoker. This
 * command class does nothing when invoking the execute and undo methods.
 */
public class NoCommand implements Command {

	@Override
	public void execute() {
		return;
	}

	@Override
	public void undo() {
		return;
	}

}
