package behavioral.observer.stockTrading.stockMarketObservers;

import behavioral.observer.stockTrading.stockMarketSubject.StockSubject;
import behavioral.observer.stockTrading.stockUpdate.StockUpdate;

/**
 * Announces when high volatility is detected.
 */
public class RiskAnalysisEngineObserver implements StockObserver {

	private double lastPrice = -1;

	@Override
	public void update(StockUpdate update, StockSubject market) {
		
		if (lastPrice < 0) {
			lastPrice = update.getPrice();
		}

		double change = Math.abs(update.getPrice() - lastPrice);
		
		if (change > 5.0) {
			System.out.println("Risk Alert: High volatility detected");
		}
		
		lastPrice = update.getPrice();
	}

}
