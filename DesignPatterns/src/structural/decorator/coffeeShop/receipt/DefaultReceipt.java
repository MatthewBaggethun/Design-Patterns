package structural.decorator.coffeeShop.receipt;

import structural.decorator.coffeeShop.order.Order;

/**
 * Default implementation of Receipt that generates a basic receipt for an order.
 */
public class DefaultReceipt implements Receipt {

	private final Order order;

	/**
	 * Constructs a DefaultReceipt with the given order.
	 *
	 * @param order The order for which the receipt is generated
	 */
	public DefaultReceipt(Order order) {
		this.order = order;
	}

	@Override
	public String print() {
		StringBuilder receipt = new StringBuilder();
		receipt.append("---------- Coffee Shop Receipt ----------\n");
		receipt.append(order.getOrderDescription() + "\n");
		receipt.append("--------------------------------------------------------------\n");
		receipt.append(String.format("Total Cost: $%.2f%n", order.getTotalCost()));
		receipt.append("Thank you for your purchase!\n");
		return receipt.toString();
	}

}
//