package structural.decorator.coffeeShop.order;

/**
 * Decorator that adds tax calculation to an existing order.
 */
public class TaxDecorator extends OrderDecorator {

	private double taxRate = 0.07;

	/**
	 * Constructs a TaxDecorator with the specified order and tax rate.
	 * 
	 * @param order   The order to be decorated.
	 * @param taxRate The tax rate to be applied.
	 */
	public TaxDecorator(Order order, double taxRate) {
		super(order);
		this.taxRate = taxRate;
	}

	@Override
	public double getTotalCost() {
		double baseTotal = super.getTotalCost();
		double taxAmount = baseTotal * taxRate;
		return baseTotal + taxAmount;
	}
	
	@Override
	public String getOrderDescription() {
		return super.getOrderDescription() + String.format("%n %.2f%% Tax Applied: ", taxRate * 100) + "+$" + String.format("%.2f", super.getTotalCost() * taxRate);
	}

}
