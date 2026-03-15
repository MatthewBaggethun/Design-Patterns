package behavioral.command.remoteControl.invoker;

import behavioral.command.remoteControl.commands.Command;
import behavioral.command.remoteControl.commands.NoCommand;

/**
 * The invoker class. Holds commands and at some point asks the command to carry
 * out a request by calling its execute() method. The constructor initializes
 * the array size to 7, but it can be any number.
 */
public class RemoteControl {
	private Command[] onCommands; // Can also store up incremental commands.
	private Command[] offCommands; // Can also store down incremental commands.

	/**
	 * Initializes the on and off command arrays with a size of 7 and fills them
	 * with NoCommand objects to avoid null checks.
	 */
	public RemoteControl() {
		onCommands = new Command[7];
		offCommands = new Command[7];

		for (int i = 0; i < 7; i++) {
			onCommands[i] = new NoCommand();
			offCommands[i] = new NoCommand();
		}

	}

	public void setCommand(int slot, Command onCommand, Command offCommand) {
		onCommands[slot] = onCommand;
		offCommands[slot] = offCommand;
	}

	public void onButtonWasPressed(int slot) {
		onCommands[slot].execute();
	}

	public void offButtonWasPushed(int slot) {
		offCommands[slot].execute();
	}

	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\n------ Remote Control -------\n");
		for (int i = 0; i < onCommands.length; i++) {
			stringBuilder.append("[slot " + i + "] " + onCommands[i].getClass().getSimpleName() + "    "
					+ offCommands[i].getClass().getSimpleName() + "\n");
		}
		return stringBuilder.toString();
	}

}
