package behavioral.observer.stockTrading.tradingStrategy;

import behavioral.observer.stockTrading.stockUpdate.StockUpdate;

/**
 * Evaluates if buying aggressively is possible.
 */
public class AggressiveTradingStrategy implements TradingStrategy {

	@Override
	public void evaluate(StockUpdate update) {
		if (update.getPrice() > 150) {
			System.out.println("Aggressive BUY at " + update.getPrice());
		}
	}

}
