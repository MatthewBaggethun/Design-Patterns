package creational.singleton.logger.serviceSimulatorsClassic;

import creational.singleton.logger.loggers.LoggerClassic;

public class AuthServiceSimulatorClassic {
	
	private LoggerClassic logger = LoggerClassic.getInstance();
	
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
