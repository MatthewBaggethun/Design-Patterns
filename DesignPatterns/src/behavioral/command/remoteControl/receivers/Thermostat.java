package behavioral.command.remoteControl.receivers;

public class Thermostat {
	private int temperature = 20;

	public void setTemperature(int temperature) {
		this.temperature = temperature;
		System.out.println("Thermostat temperature set to " + temperature + " degrees.");
	}
	
	public int getTemperature() {
		return temperature;
	}

}
