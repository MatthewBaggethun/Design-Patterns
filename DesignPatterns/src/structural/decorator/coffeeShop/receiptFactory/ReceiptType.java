package structural.decorator.coffeeShop.receiptFactory;

import structural.decorator.coffeeShop.receipt.DateTimeDecorator;
import structural.decorator.coffeeShop.receipt.FooterDecorator;
import structural.decorator.coffeeShop.receipt.HeaderDecorator;
import structural.decorator.coffeeShop.receipt.Receipt;

/**
 * Enum representing different types of receipt decorators. Each type can create
 * its corresponding Receipt decorator instance.
 */
public enum ReceiptType {
	
	HEADER {
		@Override
		public Receipt applyReceiptDecorator(Receipt receipt) {
			return new HeaderDecorator(receipt);
		}
	},
	FOOTER {
		@Override
		public Receipt applyReceiptDecorator(Receipt receipt) {
			return new FooterDecorator(receipt);
		}
	},
	DATETIME {
		@Override
		public Receipt applyReceiptDecorator(Receipt receipt) {
			return new DateTimeDecorator(receipt);
		}
	};
	
	/**
	 * Creates a Receipt decorator wrapping the given Receipt.
	 *
	 * @param receipt The Receipt to be decorated.
	 * @return The created Receipt decorator instance.
	 */
	public abstract Receipt applyReceiptDecorator(Receipt receipt);
}
