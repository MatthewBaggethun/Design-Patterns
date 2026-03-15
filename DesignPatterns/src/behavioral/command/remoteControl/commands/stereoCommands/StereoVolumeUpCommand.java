package behavioral.command.remoteControl.commands.stereoCommands;

import behavioral.command.remoteControl.commands.Command;
import behavioral.command.remoteControl.receivers.Stereo;

public class StereoVolumeUpCommand implements Command {
	private Stereo stereo;
	private int previousVolume;

	public StereoVolumeUpCommand(Stereo stereo) {
		this.stereo = stereo;
		this.previousVolume = stereo.getVolume();
	}

	@Override
	public void execute() {
		previousVolume = stereo.getVolume();
		stereo.setVolume(stereo.getVolume() + 1);
	}

	@Override
	public void undo() {
		stereo.setVolume(previousVolume);
	}

}
