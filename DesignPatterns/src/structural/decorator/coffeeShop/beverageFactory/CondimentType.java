package structural.decorator.coffeeShop.beverageFactory;

import structural.decorator.coffeeShop.beverage.Beverage;
import structural.decorator.coffeeShop.condiment.Milk;
import structural.decorator.coffeeShop.condiment.Mocha;
import structural.decorator.coffeeShop.condiment.Whip;

/**
 * Enum representing different types of condiments. Each type can apply its
 * corresponding condiment to a Beverage instance.
 */
public enum CondimentType {

	MILK {
		@Override
		public Beverage applyCondimentDecorator(Beverage beverage) {
			return new Milk(beverage);
		}
	},
	MOCHA {
		@Override
		public Beverage applyCondimentDecorator(Beverage beverage) {
			return new Mocha(beverage);
		}
	},
	WHIP

	{
		@Override
		public Beverage applyCondimentDecorator(Beverage beverage) {
			return new Whip(beverage);
		}
	};

	/**
	 * Applies the condiment to the given Beverage.
	 *
	 * @param beverage The Beverage to which the condiment will be applied.
	 * @return The Beverage with the condiment applied.
	 */
	public abstract Beverage applyCondimentDecorator(Beverage beverage);
}
