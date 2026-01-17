package behavioral.observer.weatherMonitor.weatherStationClient;

import behavioral.observer.weatherMonitor.displaysObserver.CurrentConditionsDisplayObserver;
import behavioral.observer.weatherMonitor.displaysObserver.ForecastDisplayObserver;
import behavioral.observer.weatherMonitor.displaysObserver.StatisticsDisplayObserver;
import behavioral.observer.weatherMonitor.weatherDataSubject.WeatherDataSubject;

public class WeatherStation {

	public static void main(String[] args) {
		
		WeatherDataSubject weatherData = new WeatherDataSubject();
		
		new CurrentConditionsDisplayObserver(weatherData);
		new StatisticsDisplayObserver(weatherData);
		new ForecastDisplayObserver(weatherData);
		
		weatherData.setWeatherState(14, 69, 1070);
		System.out.println("");
		weatherData.setWeatherState(19, 45, 980);
		System.out.println("");
		weatherData.setWeatherState(29, 15, 950);
	}

}
