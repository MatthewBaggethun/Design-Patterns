package structural.decorator.coffeeShop.condiment;

import structural.decorator.coffeeShop.beverage.Beverage;

/**
 * Concrete decorator representing mocha as a condiment for beverages.
 */
public class Mocha extends CondimentDecorator {

	public Mocha(Beverage beverage) {
		super(beverage);
	}

	@Override
	public double cost() {
		
		double cost = beverage.cost();
		
		switch (getSize()) {
			case SMALL:
				cost += 0.20;
				break;
			case MEDIUM:
				cost += 0.25;
				break;
			case LARGE:
				cost += 0.30;
				break;
		}
		return cost;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}

}
