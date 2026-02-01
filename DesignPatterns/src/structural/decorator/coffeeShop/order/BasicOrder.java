package structural.decorator.coffeeShop.order;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import structural.decorator.coffeeShop.beverage.Beverage;

/**
 * Represents a simplified order that can contain multiple beverages.
 */
public class BasicOrder implements Order {

	private final List<Beverage> beverages = new ArrayList<>();

	@Override
	public void addBeverage(Beverage beverage) {
		beverages.add(beverage);
	}

	@Override
	public List<Beverage> getBeverages() {
		return beverages;
	}

	@Override
	public double getTotalCost() {
		double total = 0.0;

		for (Beverage beverage : beverages) {
			total += beverage.cost();
		}
		return total;
	}

	@Override
	public String getOrderDescription() {
		NumberFormat formatter = new DecimalFormat("#0.00");
		StringBuilder description = new StringBuilder();

		for (int i = 0; i < beverages.size(); i++) {
			description.append("Item " + (i + 1) + ": ")
					.append(beverages.get(i).getSize().toString())
					.append(" " + beverages.get(i).getDescription())
					.append(" $" + formatter.format(beverages.get(i).cost()))
					.append("\n");
		}
		return description.toString().trim();
	}

}
