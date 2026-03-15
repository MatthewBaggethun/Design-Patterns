package behavioral.command.remoteControl.commands.ceilingFanCommands;

import behavioral.command.remoteControl.commands.Command;
import behavioral.command.remoteControl.receivers.CeilingFan;
import behavioral.command.remoteControl.receivers.CeilingFan.Speed;

public class CeilingFanOnCommand implements Command {
	private CeilingFan ceilingFan;

	public CeilingFanOnCommand(CeilingFan ceilingFan) {
		this.ceilingFan = ceilingFan;
	}

	@Override
	public void execute() {
		ceilingFan.setSpeed(Speed.LOW);
	}

	@Override
	public void undo() {
		ceilingFan.off();
	}

}
