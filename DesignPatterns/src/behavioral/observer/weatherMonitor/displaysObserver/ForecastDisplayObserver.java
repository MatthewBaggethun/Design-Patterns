package behavioral.observer.weatherMonitor.displaysObserver;

import behavioral.observer.weatherMonitor.weatherDataSubject.WeatherDataSubject;

/**
 * Concrete observer intended to mimic an on-screen display providing a forecast
 * prediction via changes in local pressure.
 */
public class ForecastDisplayObserver implements Observer, DisplayElement {

	private float lastPressure;
	private float currentPressure = 1013; // Safe starting value to assume, keeping complexity low.
	private WeatherDataSubject weatherData;

	public ForecastDisplayObserver(WeatherDataSubject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void display() {
		if (currentPressure > lastPressure) {
			System.out.println("Forecast: Improving weather");
		} else if (currentPressure == lastPressure) {
			System.out.println("Forecast: More of the same");
		} else {
			System.out.println("Forecast: Cooler, rainy weather");
		}
	}

	@Override
	public void update() {
		lastPressure = currentPressure;
		currentPressure = weatherData.getPressure();

		display();
	}

}
