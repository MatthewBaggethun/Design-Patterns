package behavioral.command.remoteControl.commands.ceilingFanCommands;

import behavioral.command.remoteControl.commands.Command;
import behavioral.command.remoteControl.receivers.CeilingFan;
import behavioral.command.remoteControl.receivers.CeilingFan.Speed;

public class CeilingFanOffCommand implements Command {
	private CeilingFan ceilingFan;
	private Speed previousSpeed;

	public CeilingFanOffCommand(CeilingFan ceilingFan) {
		this.ceilingFan = ceilingFan;
		this.previousSpeed = ceilingFan.getSpeed();
	}

	@Override
	public void execute() {
		ceilingFan.off();
	}

	@Override
	public void undo() {
		ceilingFan.setSpeed(previousSpeed);
	}

}
