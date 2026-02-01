package structural.decorator.coffeeShop.receipt;

import java.time.LocalDateTime;

public class DateTimeDecorator extends ReceiptDecorator {

	public DateTimeDecorator(Receipt receipt) {
		super(receipt);
	}

	@Override
	public String print() {
		return "Date: " + LocalDateTime.now().toString() + "\n" + super.print();
	}

}
