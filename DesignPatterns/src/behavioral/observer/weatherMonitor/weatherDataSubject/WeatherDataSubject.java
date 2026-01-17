package behavioral.observer.weatherMonitor.weatherDataSubject;

import java.util.ArrayList;
import java.util.List;

import behavioral.observer.weatherMonitor.displaysObserver.Observer;

/**
 * Concrete implementation of the weather-data system. This class contains
 * information provided externally via sensors and their state is stored here.
 */
public class WeatherDataSubject implements Subject {

	private List<Observer> observers;
	private float temperature;
	private float humidity;
	private float pressure;

	public WeatherDataSubject() {
		observers = new ArrayList<>();
	}

	@Override
	public void registerObserver(Observer o) {
		observers.add(o);

	}

	@Override
	public void removeObserver(Observer o) {
		observers.remove(o);

	}

	@Override
	public void notifyObservers() {
		for (Observer o : observers) {
			o.update();
		}
	}

	public void measurementsChanged() { // De-coupled from notifyObservers in case additional logic is added in the future.
		notifyObservers();
	}

	/**
	 * Method called whenever the sensors deliver new state information.
	 * 
	 * @param temperature Latest temperature poll from the sensors.
	 * @param humidity    Latest humidity poll from the sensors.
	 * @param pressure    Latest pressure poll from the sensors.
	 */
	public void setWeatherState(float temperature, float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		this.pressure = pressure;

		measurementsChanged();
	}

	public float getTemperature() {
		return temperature;
	}

	public float getHumidity() {
		return humidity;
	}

	public float getPressure() {
		return pressure;
	}

}
