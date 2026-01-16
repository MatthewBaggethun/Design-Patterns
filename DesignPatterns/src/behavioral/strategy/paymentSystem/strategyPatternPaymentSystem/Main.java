package behavioral.strategy.paymentSystem.strategyPatternPaymentSystem;

import behavioral.strategy.paymentSystem.checkout.Checkout;
import behavioral.strategy.paymentSystem.paymentStrategy.PaymentCreditCard;
import behavioral.strategy.paymentSystem.paymentStrategy.PaymentCrypto;
import behavioral.strategy.paymentSystem.paymentStrategy.PaymentPayPal;
import behavioral.strategy.paymentSystem.paymentStrategy.PaymentStrategy;

public class Main {

	public static void main(String[] args) {
		PaymentStrategy paymentCreditCard = new PaymentCreditCard();
		PaymentStrategy paymentPayPal = new PaymentPayPal();
		PaymentStrategy paymentCrypto = new PaymentCrypto();
		
		Checkout checkout = new Checkout(paymentCreditCard);
		checkout.performPayment(55.39);
		
		System.out.println("\n");
		
		checkout.setPaymentStrategy(paymentPayPal);
		checkout.performPayment(34.99);
		
		System.out.println("\n");
		
		checkout.setPaymentStrategy(paymentCrypto);
		checkout.performPayment(19.99);
	}

}
