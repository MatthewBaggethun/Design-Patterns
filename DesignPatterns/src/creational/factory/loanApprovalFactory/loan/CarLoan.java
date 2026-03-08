package creational.factory.loanApprovalFactory.loan;

import creational.factory.loanApprovalFactory.account.Account;

/**
 * A CarLoan is a type of loan that can be applied for by an account holder. It
 * has a pre-set interest rate, loan term and down payment.
 */
public class CarLoan extends Loan {

	public CarLoan(Account account, double loanAmount, boolean prePayAccountBalance) {
		super(account, loanAmount, 0.1, 6, 0.3 * loanAmount, prePayAccountBalance);
	}

	@Override
	protected boolean applyForLoan() {
		System.out.println("Applying for a car loan of amount: " + loanAmount + " with initial account balance of: "
				+ this.account.getBalance());

		return super.applyForLoan();
	}

	@Override
	protected void approveLoan() {
		System.out.println("Car loan approved for amount: " + loanAmount);

		super.approveLoan();
	}

}
