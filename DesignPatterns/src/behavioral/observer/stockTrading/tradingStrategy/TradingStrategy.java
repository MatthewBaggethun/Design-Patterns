package behavioral.observer.stockTrading.tradingStrategy;

import behavioral.observer.stockTrading.stockUpdate.StockUpdate;

/**
 * Strategy pattern implementation defining how analysis of stock updates will
 * occur.
 */
public interface TradingStrategy {

	public void evaluate(StockUpdate update);
	
}
