package creational.singleton.logger.serviceSimulatorsModern;

import creational.singleton.logger.loggers.LoggerModern;

public class FileProcessorSimulatorModern {
	
	private LoggerModern logger = LoggerModern.INSTANCE;

	public void processFile(String filePath) {

		logger.info("Processing file: " + filePath);

		if (filePath.endsWith(".txt")) {
			logger.info("File processed successfully: " + filePath);
		} else {
			logger.error("Unsupported file format: " + filePath);
		}
	}

}
