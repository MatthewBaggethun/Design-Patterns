package structural.decorator.coffeeShop.coffeeShopClient;

import java.util.List;

import structural.decorator.coffeeShop.beverage.Beverage;
import structural.decorator.coffeeShop.beverage.Size;
import structural.decorator.coffeeShop.beverageFactory.BeverageRequest;
import structural.decorator.coffeeShop.beverageFactory.BeverageType;
import structural.decorator.coffeeShop.beverageFactory.CondimentType;
import structural.decorator.coffeeShop.beverageFactory.DefaultBeverageFactory;
import structural.decorator.coffeeShop.order.BasicOrder;
import structural.decorator.coffeeShop.order.HappyHourDecorator;
import structural.decorator.coffeeShop.order.Order;
import structural.decorator.coffeeShop.order.TaxDecorator;
import structural.decorator.coffeeShop.orderFactory.DefaultOrderFactory;
import structural.decorator.coffeeShop.orderFactory.OrderRequest;
import structural.decorator.coffeeShop.orderFactory.OrderType;
import structural.decorator.coffeeShop.receipt.Receipt;
import structural.decorator.coffeeShop.receiptFactory.DefaultReceiptFactory;
import structural.decorator.coffeeShop.receiptFactory.ReceiptRequest;
import structural.decorator.coffeeShop.receiptFactory.ReceiptType;

public class Main {

	public static void main(String[] args) {

		// Generate some beverages
		DefaultBeverageFactory beverageFactory = new DefaultBeverageFactory();

		Beverage beverageOne = beverageFactory.createBeverage(new BeverageRequest(BeverageType.ESPRESSO, Size.SMALL,
				List.of(CondimentType.MILK, CondimentType.MOCHA)));

		Beverage beverageTwo = beverageFactory.createBeverage(new BeverageRequest(BeverageType.HOUSE_BLEND, Size.MEDIUM,
				List.of(CondimentType.MILK, CondimentType.MOCHA, CondimentType.WHIP)));

		Beverage beverageThree = beverageFactory.createBeverage(new BeverageRequest(BeverageType.DARK_ROAST, Size.LARGE,
				List.of(CondimentType.WHIP, CondimentType.WHIP)));

		// Generate the order
		DefaultOrderFactory orderFactory = new DefaultOrderFactory();

		Order order = orderFactory.createOrder(new OrderRequest(List.of(beverageOne, beverageTwo, beverageThree),
				List.of(OrderType.HAPPY_HOUR, OrderType.TAX)));

		// Generate the receipt
		DefaultReceiptFactory receiptFactory = new DefaultReceiptFactory();

		Receipt receipt = receiptFactory.createReceipt(
				new ReceiptRequest(order, List.of(ReceiptType.HEADER, ReceiptType.FOOTER, ReceiptType.DATETIME)));

		// Print the receipt to console
		System.out.println(receipt.print());
		
	}
}
