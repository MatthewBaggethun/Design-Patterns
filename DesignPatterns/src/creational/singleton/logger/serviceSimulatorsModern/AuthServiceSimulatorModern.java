package creational.singleton.logger.serviceSimulatorsModern;

import creational.singleton.logger.loggers.LoggerModern;

public class AuthServiceSimulatorModern {
	
	private LoggerModern logger = LoggerModern.INSTANCE;
	
	public void login(String username, String password) {
		logger.info("Login attempt for user: " + username);
		
		if (password.equals("mypassword123")) {
			logger.info("Login successful for user: " + username);
		} else {
			logger.warn("Login failed for user: " + username);
		}
	}
	
	public void logout(String username) {
		logger.info("Logout attempt for user: " + username);
		logger.info("Logout succesful for user: " + username);
	}

}
