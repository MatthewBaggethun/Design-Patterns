package behavioral.strategy.paymentSystem.paymentStrategy;

public class PaymentCreditCard implements PaymentStrategy{
	double toPay;

	@Override
	public void pay(double toPay) {
		this.toPay = toPay;
		processPayment();
		chargePayment();
		paymentOutcome();
	}
	
	/**
	 * Process the payment.
	 */
	public void processPayment() {
		System.out.println("Processing credit card payment.");
	}
	
	/**
	 * Perform the transaction.
	 */
	public void chargePayment() {
		System.out.println("Charging $" + toPay + " to credit card.");
	}
	
	/**
	 * Provide feedback on the outcome of the transaction.
	 */
	public void paymentOutcome() {
		System.out.println("Payment was successful.");
	}

}
