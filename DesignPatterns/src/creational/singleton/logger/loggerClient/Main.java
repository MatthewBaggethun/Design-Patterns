package creational.singleton.logger.loggerClient;

import creational.singleton.logger.loggers.LoggerClassic;
import creational.singleton.logger.loggers.LoggerModern;

public class Main {

	public static void main(String[] args) {
		
		LoggerClassic logger = LoggerClassic.getInstance();
		
		logger.info("This is an info message.");
		logger.warn("This is a warning message.");
		logger.debug("This is a debug message."); // Should not print.
		
		LoggerModern loggerModern = LoggerModern.INSTANCE;
		
		loggerModern.info("Info message from modern logger.");
		loggerModern.warn("Warning message from modern logger.");
		loggerModern.error("Error message from modern logger.");

	}

}
