package behavioral.observer.stockTrading.stockMarketSubject;

import behavioral.observer.stockTrading.stockMarketObservers.StockObserver;
import behavioral.observer.stockTrading.stockUpdate.StockUpdate;

/**
 * Observer-pattern subject implementation combining push and pull techniques.
 * Used to register, remove and notify observers of stock updates.
 */
public interface StockSubject {

	public void registerObserver(StockObserver o);

	public void removeObserver(StockObserver o);

	public void notifyObservers(StockUpdate update);

}
