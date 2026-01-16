package behavioral.strategy.paymentSystem.paymentStrategy;

public interface PaymentStrategy {
	
	/**
	 * Perform a payment.
	 * @param amount The amount to pay.
	 */
	public void pay(double toPay);

}
