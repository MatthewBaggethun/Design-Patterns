package structural.decorator.coffeeShop.order;

import java.util.List;

import structural.decorator.coffeeShop.beverage.Beverage;

/**
 * Abstract decorator class for orders that adds additional functionality to an
 * existing order. This class implements the Order interface and holds a
 * reference to an Order object, allowing for dynamic addition of
 * responsibilities.
 */
public abstract class OrderDecorator implements Order {
	
	protected final Order order;

	/**
	 * Constructs an OrderDecorator with the specified order.
	 * 
	 * @param order The order to be decorated.
	 */
	protected OrderDecorator(Order order) {
		this.order = order;
	}

	@Override
	public void addBeverage(Beverage beverage) {
		order.addBeverage(beverage);
	}

	@Override
	public List<Beverage> getBeverages() {
		return order.getBeverages();
	}

	@Override
	public double getTotalCost() {
		return order.getTotalCost();
	}

	@Override
	public String getOrderDescription() {
		return order.getOrderDescription();
	}

}
