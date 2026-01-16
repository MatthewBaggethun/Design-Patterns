package behavioral.strategy.paymentSystem.paymentStrategy;

public class PaymentPayPal implements PaymentStrategy{
	double toPay;

	@Override
	public void pay(double toPay) {
		this.toPay = toPay;
		redirect();
		authorize();
		paypalTransactionOutcome();
	}
	
	/**
	 * Redirect to PayPal's processing service.
	 */
	public void redirect() {
		System.out.println("Redirecting to PayPal.");
	}
	
	/**
	 * Authorize the transaction.
	 */
	public void authorize() {
		System.out.println("Authorizing transaction.");
	}

	/**
	 * Feedback from PayPal on the outcome of the transaction.
	 */
	public void paypalTransactionOutcome() {
		System.out.println("Payment of $" + toPay + " completed.");
	}
}
