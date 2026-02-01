package structural.decorator.coffeeShop.beverage;

/**
 * Enum representing the size of a beverage. Size affects the cost of the
 * beverage.
 */
public enum Size {
	SMALL {
		@Override
		public String toString() {
			return "Small";
		}
	},
	MEDIUM {
		@Override
		public String toString() {
			return "Medium";
		}
	},
	LARGE {
		@Override
		public String toString() {
			return "Large";
		}
	};

	@Override
	public abstract String toString();
}
