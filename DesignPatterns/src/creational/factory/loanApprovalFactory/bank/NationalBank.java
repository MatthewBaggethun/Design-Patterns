package creational.factory.loanApprovalFactory.bank;

import creational.factory.loanApprovalFactory.account.Account;
import creational.factory.loanApprovalFactory.loan.CarLoan;
import creational.factory.loanApprovalFactory.loan.EducationLoan;
import creational.factory.loanApprovalFactory.loan.HomeLoan;
import creational.factory.loanApprovalFactory.loan.Loan;

/**
 * A NationalBank is a type of Bank that can create different types of loans
 * utilizing the Factory Method design pattern.
 */
public class NationalBank extends Bank {

	@Override
	public Loan createLoan(Account account, LoanType loanType, double amount, boolean payUpFront) {

		switch (loanType) {

		case HOME_LOAN:
			return new HomeLoan(account, amount, payUpFront);

		case CAR_LOAN:
			return new CarLoan(account, amount, payUpFront);

		case EDUCATION_LOAN:
			return new EducationLoan(account, amount, payUpFront);

		default:
			throw new IllegalArgumentException("Invalid loan type: " + loanType);

		}
	}
}
