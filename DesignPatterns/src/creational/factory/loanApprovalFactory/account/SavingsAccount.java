package creational.factory.loanApprovalFactory.account;

/**
 * A SavingsAccount is a type of Account that has a pre-set interest rate. Can
 * be used to apply for loans.
 */
public class SavingsAccount extends Account {

	public SavingsAccount(double balance) {
		super(balance, 0.02);
	}
}
