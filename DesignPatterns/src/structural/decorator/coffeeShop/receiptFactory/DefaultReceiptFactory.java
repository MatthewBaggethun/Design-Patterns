package structural.decorator.coffeeShop.receiptFactory;

import structural.decorator.coffeeShop.receipt.DefaultReceipt;
import structural.decorator.coffeeShop.receipt.Receipt;

/**
 * Default implementation of the ReceiptFactory interface. Creates a Receipt
 * based on the provided ReceiptRequest, applying the specified decorators.
 */
public class DefaultReceiptFactory implements ReceiptFactory {

	@Override
	public Receipt createReceipt(ReceiptRequest request) {
		
		Receipt receipt = new DefaultReceipt(request.order());
		
		for(ReceiptType type : request.receiptDecorators()) {
			receipt = type.applyReceiptDecorator(receipt);
		}
		
		return receipt;
	}

}
