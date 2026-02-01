package structural.decorator.coffeeShop.beverageFactory;

import structural.decorator.coffeeShop.beverage.Beverage;

/**
 * Factory interface for creating Beverage objects based on a BeverageRequest.
 */
public interface BeverageFactory {
	
	/**
	 * Creates a Beverage based on the provided BeverageRequest.
	 *
	 * @param request The BeverageRequest containing details for the beverage.
	 * @return The created Beverage object.
	 */
	Beverage createBeverage(BeverageRequest request);

}
