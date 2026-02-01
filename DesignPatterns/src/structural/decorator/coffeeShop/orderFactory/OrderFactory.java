package structural.decorator.coffeeShop.orderFactory;

import structural.decorator.coffeeShop.order.Order;

/**
 * Factory interface for creating Order objects.
 */
public interface OrderFactory {
	
	/**
	 * Creates an Order based on the provided OrderRequest.
	 *
	 * @param request The OrderRequest containing details for the order.
	 * @return The created Order object.
	 */
	Order createOrder(OrderRequest request);

}
