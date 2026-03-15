package behavioral.command.remoteControl.commands.stereoCommands;

import behavioral.command.remoteControl.commands.Command;
import behavioral.command.remoteControl.receivers.Stereo;

public class StereoVolumeDownCommand implements Command{
	private Stereo stereo;
	private int previousVolume;

	public StereoVolumeDownCommand(Stereo stereo) {
		this.stereo = stereo;
		previousVolume = stereo.getVolume();
	}
	
	
	@Override
	public void execute() {
		previousVolume = stereo.getVolume();
		stereo.setVolume(stereo.getVolume() - 1);		
	}

	@Override
	public void undo() {
		stereo.setVolume(previousVolume);
		previousVolume = stereo.getVolume();
	}

}
