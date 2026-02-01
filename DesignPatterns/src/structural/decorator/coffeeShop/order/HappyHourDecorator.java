package structural.decorator.coffeeShop.order;

/**
 * Decorator that applies a happy hour discount to an existing order.
 */
public class HappyHourDecorator extends OrderDecorator {

	private double discountRate;

	/**
	 * Constructs a HappyHourDiscount decorator with the specified order and
	 * discount rate.
	 * 
	 * @param order        The order to be decorated.
	 * @param discountRate The discount rate to be applied.
	 */
	public HappyHourDecorator(Order order, double discountRate) {
		super(order);
		this.discountRate = discountRate;
	}

	@Override
	public double getTotalCost() {
		double baseTotal = super.getTotalCost();
		double discountAmount = baseTotal * discountRate;
		return baseTotal - discountAmount;
	}

	@Override
	public String getOrderDescription() {
		return super.getOrderDescription()
				+ String.format("%n %.2f%% Happy Hour Discount Applied: ", discountRate * 100) + "-$"
				+ String.format("%.2f", super.getTotalCost() * discountRate);
	}

}
