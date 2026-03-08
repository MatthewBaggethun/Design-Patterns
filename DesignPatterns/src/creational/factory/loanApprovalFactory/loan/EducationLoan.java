package creational.factory.loanApprovalFactory.loan;

import creational.factory.loanApprovalFactory.account.Account;

/**
 * An EducationLoan is a type of loan that can be applied for by an account
 * holder. It has pre-set interest rate, loan term and down payment.
 */
public class EducationLoan extends Loan {

	public EducationLoan(Account account, double loanAmount, boolean prePayAccountBalance) {
		super(account, loanAmount, 0.064, 30, 0.0, prePayAccountBalance);
	}

	@Override
	protected boolean applyForLoan() {
		System.out.println("Applying for an education loan of amount: " + loanAmount
				+ " with initial account balance of: " + this.account.getBalance());

		return super.applyForLoan();
	}

	@Override
	protected void approveLoan() {
		System.out.println("Education loan approved for amount: " + loanAmount);

		super.approveLoan();
	}

}
