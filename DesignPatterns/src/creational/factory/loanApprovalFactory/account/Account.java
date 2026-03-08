package creational.factory.loanApprovalFactory.account;

/**
 * Represents a simplified bank account.
 */
public abstract class Account {

	protected double balance;
	protected double interestRate;

	/**
	 * Instantiates a new account with a balance and interest rate.
	 * 
	 * @param balance      The initial balance of the account.
	 * @param interestRate The interest rate for the account.
	 */
	protected Account(double balance, double interestRate) {
		this.balance = balance;
		this.interestRate = interestRate;
	}

	/**
	 * Calculates the balance in the account after interest is added.
	 * 
	 * @return The balance after interest is added.
	 */
	public double balanceAfterInterest() {
		return balance + (balance * interestRate);
	}

	public double getBalance() {
		return balance;
	}

	public double setBalance(double amount) {
		this.balance = amount;
		return amount;
	}

}
