package behavioral.observer.stockTrading.stockTradingClient;

import behavioral.observer.stockTrading.stockMarketObservers.HistoricalPriceObserver;
import behavioral.observer.stockTrading.stockMarketObservers.PriceTickerDisplayObserver;
import behavioral.observer.stockTrading.stockMarketObservers.RiskAnalysisEngineObserver;
import behavioral.observer.stockTrading.stockMarketObservers.TradingBotObserver;
import behavioral.observer.stockTrading.stockMarketSubject.StockMarketSubject;
import behavioral.observer.stockTrading.tradingStrategy.AggressiveTradingStrategy;
import behavioral.observer.stockTrading.tradingStrategy.ConservativeTradingStrategy;

public class Main {

	public static void main(String[] args) {
		// Set up concrete subject and observers
		StockMarketSubject market = new StockMarketSubject();
		PriceTickerDisplayObserver priceTicker = new PriceTickerDisplayObserver();
		RiskAnalysisEngineObserver riskAnalysis = new RiskAnalysisEngineObserver();
		TradingBotObserver bot = new TradingBotObserver(new ConservativeTradingStrategy());
		HistoricalPriceObserver historicalPrice = new HistoricalPriceObserver();

		market.registerObserver(priceTicker);
		market.registerObserver(riskAnalysis);
		market.registerObserver(bot);
		market.registerObserver(historicalPrice);

		market.updatePrice("AAPL", 120);
		System.out.println("");
		market.updatePrice("AAPL", 155);
		historicalPrice.historySize();
		System.out.println("");


		// Runtime behavior change
		bot.setTradingStrategy(new AggressiveTradingStrategy());
		market.updatePrice("AAPL", 180);
		System.out.println("");
		market.updatePrice("AAPL", 185);
		System.out.println("");
	}

}
