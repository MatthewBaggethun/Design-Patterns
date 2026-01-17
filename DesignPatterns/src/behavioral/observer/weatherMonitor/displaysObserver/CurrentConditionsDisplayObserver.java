package behavioral.observer.weatherMonitor.displaysObserver;

import behavioral.observer.weatherMonitor.weatherDataSubject.WeatherDataSubject;

/**
 * Concrete observer intended to mimic an on-screen display providing current
 * temperature and humidity measurements.
 */
public class CurrentConditionsDisplayObserver implements Observer, DisplayElement {

	private float temperature;
	private float humidity;
	private WeatherDataSubject weatherData;

	public CurrentConditionsDisplayObserver(WeatherDataSubject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Current conditions: " + temperature + "°C and " + humidity + "% humidity");
	}

	@Override
	public void update() {
		this.temperature = weatherData.getTemperature();
		this.humidity = weatherData.getHumidity();

		display();
	}

}
