package structural.decorator.coffeeShop.beverage;

/**
 * Concrete Beverage class representing Dark Roast Coffee.
 */
public class DarkRoast extends Beverage {

	/**
	 * Constructs a DarkRoast beverage with the specified size.
	 * 
	 * @param size The size of the beverage.
	 */
	public DarkRoast(Size size) {
		super(size);
		this.description = "Dark Roast Coffee";
	}

	@Override
	public double cost() {

		double baseCost = 0.99;

		switch (size) {
		case SMALL:
			return baseCost;
		case MEDIUM:
			return baseCost + 0.40;
		case LARGE:
			return baseCost + 0.80;
		default:
			return baseCost;
		}
	}
}
