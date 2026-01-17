package behavioral.observer.weatherMonitor.displaysObserver;

import behavioral.observer.weatherMonitor.weatherDataSubject.WeatherDataSubject;

/**
 * Concrete observer intended to mimic an on-screen display providing a
 * statistical analysis of temperature over time.
 */
public class StatisticsDisplayObserver implements Observer, DisplayElement {

	private float maxTemp = Float.MIN_VALUE;
	private float minTemp = Float.MAX_VALUE;
	private float tempSum = 0;
	private int numReadings = 0;
	private WeatherDataSubject weatherData;

	public StatisticsDisplayObserver(WeatherDataSubject weatherData) {
		this.weatherData = weatherData;
		weatherData.registerObserver(this);
	}

	@Override
	public void display() {
		System.out.println("Avg/Max/Min temperature = " + (tempSum / numReadings) + "/" + maxTemp + "/" + minTemp);
	}

	@Override
	public void update() {
		float temp = weatherData.getTemperature();
		tempSum += temp;
		numReadings++;

		maxTemp = Math.max(maxTemp, temp);
		minTemp = Math.min(minTemp, temp);

		display();
	}
	
	/**
	 * Called when the (imaginary) GUI is restarted to avoid time-gap issues.
	 */
	public void resetMeasurementOrigin() {
		maxTemp = Float.MIN_VALUE;
		minTemp = Float.MAX_VALUE;
		tempSum = 0;
		numReadings = 0;
	}

}
