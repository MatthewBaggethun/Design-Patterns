package structural.decorator.coffeeShop.beverageFactory;

import structural.decorator.coffeeShop.beverage.Beverage;
import structural.decorator.coffeeShop.beverage.DarkRoast;
import structural.decorator.coffeeShop.beverage.Espresso;
import structural.decorator.coffeeShop.beverage.HouseBlend;
import structural.decorator.coffeeShop.beverage.Size;

/**
 * Enum representing different types of beverages. Each type can create its
 * corresponding Beverage instance with a defined size.
 */
public enum BeverageType {

	ESPRESSO {
		@Override
		public Beverage createBeverage(Size size) {
			return new Espresso(size);
		}
	},

	HOUSE_BLEND {
		@Override
		public Beverage createBeverage(Size size) {
			return new HouseBlend(size);
		}
	},

	DARK_ROAST {
		@Override
		public Beverage createBeverage(Size size) {
			return new DarkRoast(size);
		}
	};

	/**
	 * Creates a Beverage of the specified size.
	 *
	 * @param size The size of the Beverage to create.
	 * @return The created Beverage instance.
	 */
	public abstract Beverage createBeverage(Size size);
}
