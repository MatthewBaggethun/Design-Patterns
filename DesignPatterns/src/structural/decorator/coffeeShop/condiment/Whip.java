package structural.decorator.coffeeShop.condiment;

import structural.decorator.coffeeShop.beverage.Beverage;

/**
 * Concrete decorator representing whip as a condiment for beverages.
 */
public class Whip extends CondimentDecorator {

	public Whip(Beverage beverage) {
		super(beverage);
	}

	@Override
	public double cost() {
		
		double cost = beverage.cost();
		
		switch (getSize()) {
			case SMALL:
				cost += 0.15;
				break;
			case MEDIUM:
				cost += 0.20;
				break;
			case LARGE:
				cost += 0.25;
				break;
		}
		return cost;
	}

	@Override
	public String getDescription() {
		return beverage.getDescription() + ", Whip";
	}

}
