package structural.decorator.coffeeShop.condiment;

import structural.decorator.coffeeShop.beverage.Beverage;

/**
 * Concrete decorator representing milk as a condiment for beverages.
 */
public class Milk extends CondimentDecorator {

	public Milk(Beverage beverage) {
		super(beverage);
	}

	@Override
	public double cost() {
		
		double cost = beverage.cost();
		
		switch (getSize()) {
			case SMALL:
				cost += 0.10;
				break;
			case MEDIUM:
				cost += 0.15;
				break;
			case LARGE:
				cost += 0.20;
				break;
		}
		return cost;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Milk";
	}

}
