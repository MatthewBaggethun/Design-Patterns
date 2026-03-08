package creational.factory.loanApprovalFactory.loanApprovalClient;

import creational.factory.loanApprovalFactory.account.Account;
import creational.factory.loanApprovalFactory.account.SavingsAccount;
import creational.factory.loanApprovalFactory.bank.Bank;
import creational.factory.loanApprovalFactory.bank.LoanType;
import creational.factory.loanApprovalFactory.bank.NationalBank;

public class Main {

	public static void main(String[] args) {
		
		Account account = new SavingsAccount(5000000);
		
		Bank bank = new NationalBank();
		bank.createLoan(account, LoanType.HOME_LOAN, 7000000, true);
		// bank.createLoan(account, LoanType.CAR_LOAN, 300000, false);
		// bank.createLoan(account, LoanType.EDUCATION_LOAN, 1500000, true);

	}

}
