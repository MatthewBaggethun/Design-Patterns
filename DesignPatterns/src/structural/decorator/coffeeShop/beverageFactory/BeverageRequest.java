package structural.decorator.coffeeShop.beverageFactory;

import java.util.List;

import structural.decorator.coffeeShop.beverage.Size;

/**
 * Immutable record representing a beverage request with type, size, and condiments.
 */
public record BeverageRequest(BeverageType beverageType, Size size, List<CondimentType> condiments) {

}
