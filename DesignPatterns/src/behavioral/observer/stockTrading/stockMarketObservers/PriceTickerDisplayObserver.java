package behavioral.observer.stockTrading.stockMarketObservers;

import behavioral.observer.stockTrading.stockMarketSubject.StockSubject;
import behavioral.observer.stockTrading.stockUpdate.StockUpdate;

/**
 * Visualizes (prints to console) stock updates.
 */
public class PriceTickerDisplayObserver implements StockObserver{

	@Override
	public void update(StockUpdate update, StockSubject market) {
		System.out.println("Ticker: " + update.getSymbol() + " @ " + update.getPrice());
	}

}
