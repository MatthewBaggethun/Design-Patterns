package behavioral.observer.stockTrading.stockUpdate;

/**
 * Contains state of the most recent data from the stock API.
 */
public final class StockUpdate {

	private final String symbol;
	private final double price;
	private final long timestamp;

	public StockUpdate(String symbol, double price) {
		this.symbol = symbol;
		this.price = price;
		this.timestamp = System.currentTimeMillis();
	}

	public String getSymbol() {
		return symbol;
	}

	public double getPrice() {
		return price;
	}

	public long getTimestamp() {
		return timestamp;
	}

}
