package behavioral.command.remoteControl.commands.lightCommands;

import behavioral.command.remoteControl.commands.Command;
import behavioral.command.remoteControl.receivers.Light;

public class LightOnCommand implements Command {
	private Light light;

	public LightOnCommand(Light light) {
		this.light = light;
	}

	@Override
	public void execute() {
		light.on();
	}

	@Override
	public void undo() {
		light.off();
	}

}
