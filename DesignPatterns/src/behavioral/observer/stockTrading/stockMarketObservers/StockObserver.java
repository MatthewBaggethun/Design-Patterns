package behavioral.observer.stockTrading.stockMarketObservers;

import behavioral.observer.stockTrading.stockMarketSubject.StockSubject;
import behavioral.observer.stockTrading.stockUpdate.StockUpdate;

/**
 * Observer interface utilizing push and pull techniques. Updates are coupled
 * with mandatory stock updates and reference to a market.
 */
public interface StockObserver {

	public void update(StockUpdate update, StockSubject market);

}
