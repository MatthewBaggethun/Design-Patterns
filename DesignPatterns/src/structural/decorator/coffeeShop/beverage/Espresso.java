package structural.decorator.coffeeShop.beverage;

/**
 * Concrete Beverage class representing Espresso.
 */
public class Espresso extends Beverage {

	/**
	 * Constructs an Espresso with the specified size.
	 * 
	 * @param size The size of the espresso.
	 */
	public Espresso(Size size) {
		super(size);
		this.description = "Espresso";
	}

	@Override
	public double cost() {

		double baseCost = 1.99;

		switch (size) {
		case SMALL:
			return baseCost;
		case MEDIUM:
			return baseCost + 0.50;
		case LARGE:
			return baseCost + 1.00;
		default:
			return baseCost;
		}
	}

}
