package creational.factory.loanApprovalFactory.loan;

import creational.factory.loanApprovalFactory.account.Account;

/**
 * Represents a loan that can applied for by an account holder.
 */
public abstract class Loan {

	protected Account account;
	protected double loanAmount;
	protected double interestRate;
	protected int loanTerm; // Number of times interest is applied.
	protected double downPayment;
	protected boolean prePayAccountBalance;

	private double totalAmountToPayBack;
	private boolean paidBackInFull = false;

	/**
	 * Instantiates a new Loan with the specified loan amount, interest rate, and
	 * loan term.
	 * 
	 * @param loanAmount   The amount of the loan.
	 * @param interestRate The interest rate of the loan.
	 * @param loanTerm     The number of times interest is applied to the loan.
	 */
	protected Loan(Account account, double loanAmount, double interestRate, int loanTerm, double downPayment,
			boolean prePayAccountBalance) {

		this.account = account;
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.loanTerm = loanTerm;
		this.downPayment = downPayment;
		this.prePayAccountBalance = prePayAccountBalance;

		if (applyForLoan())
			simulateLoanPayBack();
	}

	/**
	 * Will check if preconditions for the loan are met. If they are, the loan will
	 * be approved and disbursed and the return value will be true. If not, the loan
	 * will be denied and the return value will be false.
	 * 
	 * @return True if the loan application is successful and false otherwise.
	 */
	protected boolean applyForLoan() {
		if (Double.compare(account.getBalance(), downPayment) >= 0) {

			approveLoan();

			if (paidBackInFull) {
				System.out.println("Loan has already been paid back in full. No need to apply for a new loan.");
				return false;
			}

			disburseLoan();
			calculateInterest();
			return true;

		} else {
			System.out.println("Loan application denied due to insufficient down payment.");
			return false;
		}
	}

	/**
	 * Will approve the loan if the account holder meets the loan requirements.
	 */
	protected void approveLoan() {
		if (canPayBackLoanAtOnce()) {
			System.out.println("\n"
					+ "The account balance is sufficient to pay back the entire loan at once. Paying back the loan in full."
					+ "\n");
			System.out.println("The account balance is now: " + account.setBalance(account.getBalance() - loanAmount));
			paidBackInFull = true;
			return;
		}

		System.out.println("\n" + "Down payment required: " + downPayment);

		if (this.prePayAccountBalance && !paidBackInFull) {
			System.out.println("Paying the initial down payment of " + downPayment + " from account balance");
			loanAmount -= downPayment;
			System.out.println("The loan amount after down payment is: " + loanAmount);
			System.out.println("Balance after loan approval: "
					+ Double.toString(account.setBalance(account.getBalance() - downPayment)) + "\n");
			System.out.println("Now paying the remaining account balance into the loan before disbursement:");
			double remainingBalanceToPrePay = account.getBalance();
			System.out.println(
					"Removing funds to pre-pay. Account balance is now " + Double.toString(account.setBalance(0)));
			System.out.println("Reducing the loan amount by the pre-paid balance. The loan amount is now : "
					+ Double.toString(loanAmount - remainingBalanceToPrePay) + "\n");
			loanAmount -= remainingBalanceToPrePay;
		}

		else {
			System.out.println("Balance after loan approval: "
					+ Double.toString(account.setBalance(account.getBalance() - downPayment)) + "\n");
		}
	}

	/**
	 * Will calculate the total amount that must be paid back after the loan term,
	 * including interest.
	 */
	protected double calculateInterest() {
		this.totalAmountToPayBack = loanAmount + (loanAmount * interestRate * loanTerm);

		System.out.println(
				"The total to be paid back after " + loanTerm + " intervals is: " + totalAmountToPayBack + "\n");

		return this.totalAmountToPayBack;
	}

	/**
	 * Will transfer funds to the account holder's account if the loan is approved.
	 */
	protected void disburseLoan() {
		this.account.setBalance(account.getBalance() + loanAmount);
		System.out.println("Loan disbursed. Current account balance: " + account.getBalance() + "\n");
	}

	/**
	 * Simulates the process of paying back the loan over the loan term, applying
	 * interest and payments to the account balance.
	 */
	private void simulateLoanPayBack() {
		double totalAmountPaidBack = 0;

		for (int i = 0; i < loanTerm; i++) {

			System.out.println("\n --- Payment Interval " + (i + 1) + " ---");

			double accountBalanceAfterInterest = account.balanceAfterInterest();
			System.out.println("Account balance after interest and before payment: " + accountBalanceAfterInterest);
			this.account.setBalance(accountBalanceAfterInterest);

			double accountBalanceAfterPayment = account.getBalance() - (totalAmountToPayBack / loanTerm);
			System.out.println("Account balance after payment: " + accountBalanceAfterPayment);
			totalAmountPaidBack += (totalAmountToPayBack / loanTerm);
			account.setBalance(accountBalanceAfterPayment);

			System.out.println(
					"Total amount of loan paid back so far: " + percentageOfLoanPaidBack(totalAmountPaidBack) + "%");

		}
	}

	/**
	 * Helper method to calculate the percentage of the loan that has been paid
	 * back. Avoids issues with floating point precision when the full loan is paid.
	 * 
	 * @param totalAmountPaidBack Amount paid back so far.
	 * @return An integer representing the percentage of the loan that has been paid
	 *         back.
	 */
	private int percentageOfLoanPaidBack(double totalAmountPaidBack) {
		return Math.abs(totalAmountPaidBack - totalAmountToPayBack) < 0.01 ? 100
				: (int) ((totalAmountPaidBack / totalAmountToPayBack) * 100);
	}

	/**
	 * Helper method to determine if the account holder can pay back the entire loan
	 * at once, which is the same as purchasing the item outright without a loan.
	 * 
	 * @return True if the account balance is greater than or equal to the total
	 *         amount of the loan. False otherwise.
	 */
	private boolean canPayBackLoanAtOnce() {
		return account.getBalance() >= loanAmount;
	}
}
