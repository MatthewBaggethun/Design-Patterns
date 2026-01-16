package behavioral.strategy.paymentSystem.contextCheckout;

import behavioral.strategy.paymentSystem.paymentStrategy.PaymentStrategy;

/**
 * Context class containing a PaymentStrategy. Used to execute payments via
 * various means.
 */
public class Checkout {
	PaymentStrategy paymentStrategy;

	/**
	 * Create a Checkout object to perform transactions across various payment
	 * strategies.
	 * 
	 * @param paymentStrategy The payment strategy the client wishes to use.
	 */
	public Checkout(PaymentStrategy paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}

	public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
		this.paymentStrategy = paymentStrategy;
	}

	/**
	 * Execute the payment process.
	 * 
	 * @param toPay The amount the client will pay.
	 */
	public void performPayment(double toPay) {
		this.paymentStrategy.pay(toPay);
	}

}
