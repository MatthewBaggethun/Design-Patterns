package behavioral.observer.stockTrading.stockMarketObservers;

import java.util.ArrayList;
import java.util.List;

import behavioral.observer.stockTrading.stockMarketSubject.StockSubject;
import behavioral.observer.stockTrading.stockUpdate.StockUpdate;

/**
 * Responsible for storing price history.
 */
public class HistoricalPriceObserver implements StockObserver{
	
	private final List<StockUpdate> history = new ArrayList<>();

	@Override
	public void update(StockUpdate update, StockSubject market) {
		history.add(update);
	}
	
	public void historySize() {
		System.out.println("Logged " + history.size() + " price updates");
	}

}
