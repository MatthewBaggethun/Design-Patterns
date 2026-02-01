package structural.decorator.coffeeShop.order;

import java.util.List;

import structural.decorator.coffeeShop.beverage.Beverage;

/**
 * Interface representing an order in the coffee shop. An order can contain
 * multiple beverages and provides methods to add beverages, retrieve the list
 * of beverages, calculate the total cost, and get a description of the order.
 */
public interface Order {

	/**
	 * Adds a beverage to the order.
	 * 
	 * @param beverage The beverage to be added.
	 */
	void addBeverage(Beverage beverage);

	/**
	 * Retrieves the list of beverages in the order.
	 * 
	 * @return A list of beverages.
	 */
	List<Beverage> getBeverages();

	/**
	 * Calculates the total cost of the order.
	 * 
	 * @return The total cost.
	 */
	double getTotalCost();

	/**
	 * Provides a description of the order, including all beverages.
	 * 
	 * @return A string description of the order.
	 */
	String getOrderDescription();

}
