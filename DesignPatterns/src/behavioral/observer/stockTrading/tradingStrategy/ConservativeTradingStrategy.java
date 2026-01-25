package behavioral.observer.stockTrading.tradingStrategy;

import behavioral.observer.stockTrading.stockUpdate.StockUpdate;

/**
 * Evaluates if buying conservatively is possible.
 */
public class ConservativeTradingStrategy implements TradingStrategy{

	@Override
	public void evaluate(StockUpdate update) {
		if (update.getPrice() < 100) {
            System.out.println("Conservative BUY at " + update.getPrice());
        }
	}

}
