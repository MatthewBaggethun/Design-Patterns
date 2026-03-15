package behavioral.command.remoteControl.invoker;

import behavioral.command.remoteControl.commands.Command;

public class SimpleRemoteControl {
	private Command slot;

	public void setCommand(Command command) {
		this.slot = command;
	}

	public void buttonWasPressed() {
		slot.execute();
	}
	
	public void undoButtonWasPressed() {
		slot.undo();
	}
}
