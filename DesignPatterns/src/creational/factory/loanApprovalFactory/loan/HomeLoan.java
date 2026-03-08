package creational.factory.loanApprovalFactory.loan;

import creational.factory.loanApprovalFactory.account.Account;

/*
 * A HomeLoan is a type of loan that can be applied for by an account holder. It has pre-set interest rate, loan term and down payment.
 */
public class HomeLoan extends Loan {

	public HomeLoan(Account account, double loanAmount, boolean prePayAccountBalance) {
		super(account, loanAmount, 0.045, 15, loanAmount * 0.15, prePayAccountBalance);
	}

	@Override
	protected boolean applyForLoan() {
		System.out.println("Applying for a home loan of amount: " + loanAmount + " with initial account balance of: "
				+ this.account.getBalance());

		return super.applyForLoan();
	}

	@Override
	protected void approveLoan() {
		System.out.println("Home loan approved for amount: " + loanAmount);

		super.approveLoan();
	}

}
