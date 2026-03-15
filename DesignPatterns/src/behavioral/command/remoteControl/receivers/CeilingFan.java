package behavioral.command.remoteControl.receivers;

public class CeilingFan {
	private Speed speed = Speed.OFF;

	public enum Speed {
		OFF, LOW, MEDIUM, HIGH
	}

	public void off() {
		System.out.println("Ceiling fan is off.");
	}

	public void setSpeed(Speed speed) {
		System.out.println("Ceiling fan speed set to " + speed + ".");
	}

	public Speed getSpeed() {
		return speed;
	}

}
