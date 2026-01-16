package behavioral.strategy.paymentSystem.paymentStrategy;

public class PaymentCrypto implements PaymentStrategy{
	double toPay;

	@Override
	public void pay(double toPay) {
		this.toPay = toPay;
		initializeTransfer();
		broadcast();
		confirmation();
	}

	/**
	 * Initialize the crpyto transfer.
	 */
	public void initializeTransfer() {
		System.out.println("Initializing crypto transfer.");
	}
	
	/**
	 * Broadcast the transaction to the network.
	 */
	public void broadcast() {
		System.out.println("Broadcasting transaction to network.");
	}
	
	/**
	 * Obtain the outcome of the transaction from the network.
	 */
	public void confirmation() {
		System.out.println("Payment of $" + toPay + " confirmed.");
	}
}
