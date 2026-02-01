package structural.decorator.coffeeShop.orderFactory;

import structural.decorator.coffeeShop.order.HappyHourDecorator;
import structural.decorator.coffeeShop.order.Order;
import structural.decorator.coffeeShop.order.TaxDecorator;

/**
 * Enum representing different types of order decorators.
 */
public enum OrderType {
	
	HAPPY_HOUR {
		@Override
		public Order applyOrderDecorator(Order order) {
			return new HappyHourDecorator(order, 0.10); // Discount is hardcoded here, in production it would be dynamic.
		}
	}, 
	TAX {
		@Override
		public Order applyOrderDecorator(Order order) {
			return new TaxDecorator(order, 0.07); // Tax is hardcoded here, in production it would be dynamic.
		}
	};
	
	/**
	 * Creates an order decorator based on the specific order type.
	 * 
	 * @param order The order to be decorated.
	 * @return The decorated order.
	 */
	public abstract Order applyOrderDecorator(Order order);
}
