package structural.decorator.coffeeShop.receiptFactory;

import structural.decorator.coffeeShop.receipt.Receipt;

/**
 * Factory interface for creating Receipt objects.
 */
public interface ReceiptFactory {

	/**
	 * Creates a Receipt based on the provided ReceiptRequest.
	 *
	 * @param request The ReceiptRequest containing details for the receipt.
	 * @return The created Receipt object.
	 */
	Receipt createReceipt(ReceiptRequest request);

}
