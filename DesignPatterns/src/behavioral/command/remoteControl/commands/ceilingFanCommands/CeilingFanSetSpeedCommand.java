package behavioral.command.remoteControl.commands.ceilingFanCommands;

import behavioral.command.remoteControl.commands.Command;
import behavioral.command.remoteControl.receivers.CeilingFan;
import behavioral.command.remoteControl.receivers.CeilingFan.Speed;

public class CeilingFanSetSpeedCommand implements Command {
	private CeilingFan ceilingFan;
	private Speed speed;
	private Speed previousSpeed;

	public CeilingFanSetSpeedCommand(CeilingFan ceilingFan, Speed speed) {
		this.ceilingFan = ceilingFan;
		this.speed = speed;
		this.previousSpeed = ceilingFan.getSpeed();
	}

	@Override
	public void execute() {
		previousSpeed = ceilingFan.getSpeed();
		ceilingFan.setSpeed(speed);
	}

	@Override
	public void undo() {
		ceilingFan.setSpeed(previousSpeed);
		previousSpeed = ceilingFan.getSpeed();
	}

}
