package behavioral.command.remoteControl.commands.stereoCommands;

import behavioral.command.remoteControl.commands.Command;
import behavioral.command.remoteControl.receivers.Stereo;

public class StereoOffCommand implements Command {
	Stereo stereo;

	public StereoOffCommand(Stereo stereo) {
		this.stereo = stereo;
	}

	@Override
	public void execute() {
		stereo.off();
	}

	@Override
	public void undo() {
		stereo.on();
	}

}
