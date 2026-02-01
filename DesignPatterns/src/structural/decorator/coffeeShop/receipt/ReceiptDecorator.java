package structural.decorator.coffeeShop.receipt;

/**
 * ReceiptDecorator serves as a base decorator class for Receipt objects. It
 * implements the Receipt interface and holds a reference to a decorated
 * Receipt. Subclasses can extend this class to add additional functionality to
 * the print method.
 */
public abstract class ReceiptDecorator implements Receipt {

	protected Receipt decoratedReceipt;

	protected ReceiptDecorator(Receipt decoratedReceipt) {
		this.decoratedReceipt = decoratedReceipt;
	}

	@Override
	public String print() {
		return decoratedReceipt.print();
	}

}
