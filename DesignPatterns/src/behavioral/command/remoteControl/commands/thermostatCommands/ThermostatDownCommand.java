package behavioral.command.remoteControl.commands.thermostatCommands;

import behavioral.command.remoteControl.commands.Command;
import behavioral.command.remoteControl.receivers.Thermostat;

public class ThermostatDownCommand implements Command {
	Thermostat thermostat;
	private int previousTemperature;

	public ThermostatDownCommand(Thermostat thermostat) {
		this.thermostat = thermostat;
		this.previousTemperature = thermostat.getTemperature();
	}

	@Override
	public void execute() {
		previousTemperature = thermostat.getTemperature();
		thermostat.setTemperature(thermostat.getTemperature() - 1);
	}

	@Override
	public void undo() {
		thermostat.setTemperature(previousTemperature);
		previousTemperature = thermostat.getTemperature();
	}

}
