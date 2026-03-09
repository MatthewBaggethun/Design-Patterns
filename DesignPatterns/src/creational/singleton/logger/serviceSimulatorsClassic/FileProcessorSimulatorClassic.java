package creational.singleton.logger.serviceSimulatorsClassic;

import creational.singleton.logger.loggers.LoggerClassic;

public class FileProcessorSimulatorClassic {

	private LoggerClassic logger = LoggerClassic.getInstance();

	public void processFile(String filePath) {

		logger.info("Processing file: " + filePath);

		if (filePath.endsWith(".txt")) {
			logger.info("File processed successfully: " + filePath);
		} else {
			logger.error("Unsupported file format: " + filePath);
		}
	}
}
