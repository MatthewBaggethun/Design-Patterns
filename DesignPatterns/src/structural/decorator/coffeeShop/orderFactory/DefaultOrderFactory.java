package structural.decorator.coffeeShop.orderFactory;

import structural.decorator.coffeeShop.beverage.Beverage;
import structural.decorator.coffeeShop.order.BasicOrder;
import structural.decorator.coffeeShop.order.HappyHourDecorator;
import structural.decorator.coffeeShop.order.Order;
import structural.decorator.coffeeShop.order.TaxDecorator;

/**
 * Default implementation of the OrderFactory interface.
 */
public class DefaultOrderFactory implements OrderFactory {

	@Override
	public Order createOrder(OrderRequest request) {

		Order order = new BasicOrder();

		for (Beverage beverage : request.beverages()) {
			order.addBeverage(beverage);
		}
		
        for (OrderType orderType : request.orderDecorators()) {
        	order = orderType.applyOrderDecorator(order);
		}

		return order;

	}
}
