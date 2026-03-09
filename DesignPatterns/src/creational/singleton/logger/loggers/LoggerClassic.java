package creational.singleton.logger.loggers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Classic logger implementation utilizing the Singleton Pattern. Supports
 * multiple logging levels and timestamps for each entry. Intended for use in
 * single-threaded applications or where thread safety is not a concern.
 */
public class LoggerClassic {

	private static LoggerClassic instance;

	private LoggingLevels logLevel = LoggingLevels.INFO;

	private LoggerClassic() {
	}

	/**
	 * Provides access to the single instance of LoggerClassic.
	 * 
	 * @return The singleton instance of LoggerClassic.
	 */
	public static LoggerClassic getInstance() {
		if (instance == null)
			instance = new LoggerClassic();

		return instance;
	}
	
	/**
	 * Logs a message if the log level is equal to or higher than the current
	 * loggers log level.
	 * 
	 * @param logLevel The level of the log message.
	 * @param message  The message to be logged.
	 */
	private void log(LoggingLevels logLevel, String message) {
		if (logLevel.ordinal() >= this.logLevel.ordinal()) {

			String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			System.out.println(timestamp + " [" + logLevel + "] " + message);
		}
	}

	/**
	 * Dynamically alter the logging level.
	 * 
	 * @param logLevel New logging level for the logger.
	 */
	public void setLogLevel(LoggingLevels logLevel) {
		this.logLevel = logLevel;
	}

	/**
	 * Logs an info message.
	 */
	public void info(String message) {
		log(LoggingLevels.INFO, message);
	}

	/**
	 * Logs a warning message.
	 */
	public void warn(String message) {
		log(LoggingLevels.WARN, message);
	}

	/**
	 * Logs an error message.
	 */
	public void error(String message) {
		log(LoggingLevels.ERROR, message);
	}

	/**
	 * Logs a debug message.
	 */
	public void debug(String message) {
		log(LoggingLevels.DEBUG, message);
	}

}
