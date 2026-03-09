package creational.singleton.logger.serviceSimulatorsModern;

import creational.singleton.logger.loggers.LoggerModern;

public class TaskManagerSimulatorModern {
	
	private LoggerModern logger = LoggerModern.INSTANCE;
	
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
