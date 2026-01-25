package behavioral.observer.stockTrading.stockMarketObservers;

import behavioral.observer.stockTrading.stockMarketSubject.StockSubject;
import behavioral.observer.stockTrading.stockUpdate.StockUpdate;
import behavioral.observer.stockTrading.tradingStrategy.TradingStrategy;

/**
 * Performs trades automatically as defined by a concrete TradingStrategy.
 */
public class TradingBotObserver implements StockObserver{
	
	private TradingStrategy strategy;
	
	public TradingBotObserver(TradingStrategy strategy) {
		this.strategy = strategy;
	}
	
	public void setTradingStrategy(TradingStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void update(StockUpdate update, StockSubject market) {
		strategy.evaluate(update);
	}

}
