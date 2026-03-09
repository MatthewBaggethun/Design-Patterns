package creational.singleton.logger.serviceSimulatorsClassic;

import creational.singleton.logger.loggers.LoggerClassic;

public class TaskManagerSimulatorClassic {
	
	private LoggerClassic logger = LoggerClassic.getInstance();
	
	public void createTask(String taskName) {
		logger.info("Creating task: " + taskName);
	}

	public void completeTask(String taskName) {
		logger.info("Completing task: " + taskName);
	}
	
	public void deleteTask(String taskName) {
		logger.warn("Deleting task: " + taskName);
	}
}
