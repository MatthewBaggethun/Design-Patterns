package structural.decorator.coffeeShop.beverageFactory;

import structural.decorator.coffeeShop.beverage.Beverage;
import structural.decorator.coffeeShop.beverage.DarkRoast;
import structural.decorator.coffeeShop.beverage.Espresso;
import structural.decorator.coffeeShop.beverage.HouseBlend;
import structural.decorator.coffeeShop.beverage.Size;
import structural.decorator.coffeeShop.condiment.Milk;
import structural.decorator.coffeeShop.condiment.Mocha;
import structural.decorator.coffeeShop.condiment.Whip;

/**
 * Default implementation of the BeverageFactory interface. Creates Beverage
 * objects based on the provided BeverageRequest.
 */
public class DefaultBeverageFactory implements BeverageFactory {

	@Override
	public Beverage createBeverage(BeverageRequest request) {

		Beverage beverage = request.beverageType().createBeverage(request.size());

		for (CondimentType condiment : request.condiments()) {
			beverage = condiment.applyCondimentDecorator(beverage);
		}

		return beverage;
	}
}
