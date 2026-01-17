package behavioral.observer.weatherMonitor.weatherDataSubject;

import behavioral.observer.weatherMonitor.displaysObserver.Observer;

/**
 * Standard observer-pattern subject interface.
 */
public interface Subject {
	
	void registerObserver(Observer o);
	
	void removeObserver(Observer o);
	
	void notifyObservers();

}
