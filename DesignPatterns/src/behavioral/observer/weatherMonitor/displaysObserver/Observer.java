package behavioral.observer.weatherMonitor.displaysObserver;

/**
 * Observer interface utilizing the pull technique. Observers will ask the
 * subject for the data they need.
 */
public interface Observer {

	void update();

}
