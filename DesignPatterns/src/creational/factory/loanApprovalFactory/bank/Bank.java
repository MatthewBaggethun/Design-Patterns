package creational.factory.loanApprovalFactory.bank;

import creational.factory.loanApprovalFactory.account.Account;
import creational.factory.loanApprovalFactory.loan.Loan;

/**
 * Represents a simplified bank that can process loan applications.
 */
public abstract class Bank {

	public Loan processLoan(Account account, LoanType loanType, double amount, boolean payUpFront) {
		return createLoan(account, loanType, amount, payUpFront);
	}

	/**
	 * Instantiate a new loan based on the provided inputs. The type of loan created
	 * will depend on the loanType parameter.
	 * 
	 * @param account    The account applying for the loan.
	 * @param loanType   The type of loan being applied for.
	 * @param amount     The amount of the loan being applied for.
	 * @param payUpFront Whether the account holder is paying up front or not.
	 * @return A new Loan instance based on the provided inputs.
	 */
	public abstract Loan createLoan(Account account, LoanType loanType, double amount, boolean payUpFront);
}
