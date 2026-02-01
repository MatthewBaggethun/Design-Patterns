/**
 * 
 */
package structural.decorator.coffeeShop.beverage;

/**
 * Contract class for beverages. Extensions of this class represent different
 * types of beverages, which can be decorated with various add-ons.
 */
public abstract class Beverage {

	protected Size size;
	protected String description = "Unknown Beverage";

	/**
	 * Default constructor for the beverage class, intended for use by decorators to
	 * improve readability.
	 */
	protected Beverage() {
	}

	/**
	 * Defines the size of the beverage. Additional logic is handled via decorators.
	 * 
	 * @param size The size of the beverage.
	 */
	protected Beverage(Size size) {
		this.size = size;
	}

	/**
	 * Calculates the cost of the beverage, including any decorations.
	 * 
	 * @return The total cost of the beverage.
	 */
	public abstract double cost();

	public Size getSize() {
		return size;
	}

	public String getDescription() {
		return description;
	}

	public void setSize(Size size) {
		this.size = size;
	}

}
