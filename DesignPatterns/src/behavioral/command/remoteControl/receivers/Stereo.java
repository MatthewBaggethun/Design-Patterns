package behavioral.command.remoteControl.receivers;

public class Stereo {
	private int volume = 10;

	public void on() {
		System.out.println("Stereo is on.");
	}

	public void off() {
		System.out.println("Stereo is off.");
	}

	public void setCD() {
		System.out.println("Stereo is set and playing.");
	}

	public void setVolume(int volume) {
		this.volume = volume;
		System.out.println("Stereo volume is: " + this.volume);
	}

	public int getVolume() {
		return volume;
	}

}
