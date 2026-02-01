package structural.decorator.coffeeShop.orderFactory;

import java.util.List;

import structural.decorator.coffeeShop.beverage.Beverage;

/**
 * Record representing an order request containing beverages and order decorators.
 */
public record OrderRequest(List<Beverage> beverages, List<OrderType> orderDecorators) {

}
