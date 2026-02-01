package structural.decorator.coffeeShop.receipt;

/**
 * HeaderDecorator adds a header section to the receipt.
 */
public class HeaderDecorator extends ReceiptDecorator {

	public HeaderDecorator(Receipt receipt) {
		super(receipt);
	}

	@Override
	public String print() {
		return """
				Welcome to Brewed Awakenings
				123 Bean Street
				--------------------------------------------------------------
				""" + super.print();
	}

}
