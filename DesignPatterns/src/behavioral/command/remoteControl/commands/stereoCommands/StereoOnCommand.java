package behavioral.command.remoteControl.commands.stereoCommands;

import behavioral.command.remoteControl.commands.Command;
import behavioral.command.remoteControl.receivers.Stereo;

public class StereoOnCommand implements Command {
	private Stereo stereo;

	public StereoOnCommand(Stereo stereo) {
		this.stereo = stereo;
	}

	@Override
	public void execute() {
		stereo.on();
	}

	@Override
	public void undo() {
		stereo.off();
	}

}
