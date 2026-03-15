package behavioral.command.remoteControl.remoteControlClient;

import behavioral.command.remoteControl.commands.ceilingFanCommands.CeilingFanOffCommand;
import behavioral.command.remoteControl.commands.ceilingFanCommands.CeilingFanOnCommand;
import behavioral.command.remoteControl.commands.lightCommands.LightOffCommand;
import behavioral.command.remoteControl.commands.lightCommands.LightOnCommand;
import behavioral.command.remoteControl.commands.stereoCommands.StereoOffCommand;
import behavioral.command.remoteControl.commands.stereoCommands.StereoOnCommand;
import behavioral.command.remoteControl.commands.thermostatCommands.ThermostatDownCommand;
import behavioral.command.remoteControl.commands.thermostatCommands.ThermostatUpCommand;
import behavioral.command.remoteControl.invoker.RemoteControl;
import behavioral.command.remoteControl.receivers.CeilingFan;
import behavioral.command.remoteControl.receivers.Light;
import behavioral.command.remoteControl.receivers.Stereo;
import behavioral.command.remoteControl.receivers.Thermostat;

public class Main {

	public static void main(String[] args) {
		
		RemoteControl remoteControl = new RemoteControl();
		
		Light light = new Light();
		CeilingFan fan = new CeilingFan();
		Stereo stereo = new Stereo();
		Thermostat thermostat = new Thermostat();
		
		LightOffCommand lightOff = new LightOffCommand(light);
		LightOnCommand lightOn = new LightOnCommand(light);
		
		CeilingFanOffCommand ceilingFanOff = new CeilingFanOffCommand(fan);
		CeilingFanOnCommand ceilingFanOn = new CeilingFanOnCommand(fan);
		
		StereoOffCommand stereoOff = new StereoOffCommand(stereo);
		StereoOnCommand stereoOn = new StereoOnCommand(stereo);
		
		ThermostatDownCommand tempDown = new ThermostatDownCommand(thermostat);
		ThermostatUpCommand tempUp = new ThermostatUpCommand(thermostat);
		
		remoteControl.setCommand(0, lightOn, lightOff);
		remoteControl.setCommand(1, ceilingFanOn, ceilingFanOff);
		remoteControl.setCommand(2, stereoOn, stereoOff);
		remoteControl.setCommand(3, tempUp, tempDown);
		
		System.out.println(remoteControl.toString());
		
		remoteControl.onButtonWasPressed(0);
		remoteControl.offButtonWasPushed(0);
		remoteControl.onButtonWasPressed(1);
		remoteControl.offButtonWasPushed(1);
		remoteControl.onButtonWasPressed(2);
		remoteControl.offButtonWasPushed(2);
		remoteControl.onButtonWasPressed(3);
		remoteControl.offButtonWasPushed(3);
		
	}

}
