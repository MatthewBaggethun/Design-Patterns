package structural.decorator.coffeeShop.receipt;

/**
 * FooterDecorator adds a footer section to the receipt.
 */
public class FooterDecorator extends ReceiptDecorator {

	public FooterDecorator(Receipt receipt) {
		super(receipt);
	}

	@Override
	public String print() {
		return super.print() + """
				--------------------------------------------------------------
				Visit us online at www.brewedawakenings.com
				Follow us on social media @BrewedAwakenings
				Have a great day!
				""";
	}

}
