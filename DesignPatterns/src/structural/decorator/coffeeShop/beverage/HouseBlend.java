package structural.decorator.coffeeShop.beverage;

/**
 * Concrete Beverage class representing House Blend Coffee.
 */
public class HouseBlend extends Beverage {

	/**
	 * Constructs a HouseBlend beverage with the specified size.
	 * 
	 * @param size The size of the beverage.
	 */
	public HouseBlend(Size size) {
		super(size);
		this.description = "House Blend Coffee";
	}

	@Override
	public double cost() {

		double baseCost = 0.89;

		switch (size) {
		case SMALL:
			return baseCost;
		case MEDIUM:
			return baseCost + 0.30;
		case LARGE:
			return baseCost + 0.60;
		default:
			return baseCost;
		}
	}

}
