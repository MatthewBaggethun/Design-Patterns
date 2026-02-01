package structural.decorator.coffeeShop.condiment;

import structural.decorator.coffeeShop.beverage.Beverage;
import structural.decorator.coffeeShop.beverage.Size;

/**
 * Abstract decorator class for condiments that can be added to beverages.
 * This class extends the Beverage class and holds a reference to a Beverage
 * object, allowing for dynamic addition of responsibilities.
 */
public abstract class CondimentDecorator extends Beverage {
	
	protected final Beverage beverage;

	protected CondimentDecorator(Beverage beverage) {
		this.beverage = beverage;
	}
	
	@Override
	public Size getSize() {
		return beverage.getSize();
	}
	
	@Override
	public abstract String getDescription();
	
	/**
	 * Returns the wrapped beverage.
	 * @return The beverage being decorated.
	 */
	protected Beverage getWrappedBeverage() {
		return beverage;
	}

}
