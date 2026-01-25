package behavioral.observer.stockTrading.stockMarketSubject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import behavioral.observer.stockTrading.stockMarketObservers.StockObserver;
import behavioral.observer.stockTrading.stockUpdate.StockUpdate;

/**
 * Concrete subject for classes in the stockMarketObservers package. Responsible
 * for notifying subscribed observers of price updates.
 */
public class StockMarketSubject implements StockSubject {

	private final List<StockObserver> observers = new ArrayList<>();
	private final Map<String, Double> prices = new HashMap<>();

	/**
	 * Stores new prices internally and notifies observers of the change.
	 * 
	 * @param symbol   The stock symbol update.
	 * @param newPrice The stock price update.
	 */
	public void updatePrice(String symbol, double newPrice) {
		prices.put(symbol, newPrice);

		notifyObservers(new StockUpdate(symbol, newPrice));
	}

	@Override
	public void registerObserver(StockObserver o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(StockObserver o) {
		observers.remove(o);
	}

	@Override
	public void notifyObservers(StockUpdate update) {
		for (StockObserver o : observers) {
			o.update(update, this);
		}
	}

}
