# Loan Factory
### Exploring Factory Method Pattern

## Problem Space

This project is derived from the example which is shown in Head First Design Patterns. In that example two different pizzerias operate similarly on a high level. The process both shops use to make their pizzas is identical, however the ingredients vary greatly between them. To solve this issue, the Factory Method Pattern was used to logically separate the creation process between both stores. 

In an attempt to replicate the project from the book, I have shifted the problem from creating pizzas to a loan system. This system contains an account, loan and a bank. The idea is that many different types of loans can exist with their own interest rate and pay-by intervals. Similarly, different banks and user accounts can also exist with varying interest rates or amounts of loan disbursement available. 

This project is meant to focus solely on the Factory Method Pattern, so to keep the complexity low I have decided to only focus on the creation of different types of loans. The code will still be written in a way that different bank or account entities can be extended easily.

---

## Core Abstraction

The abstract `Loan` class defines the functionality for all the different types of loans which are available. The logic for loans resides here and concrete loans which extend this class utilize the super constructor to perform the loan logic.

Inside the abstract `Loan` class exists the following methods to model a real-world loan:
 - `applyForLoan()`
 - `approveLoan()`
 - `disburseLoan()`
 - `calculateInterest()`
 
Functionality to pay down a loan with an entire account balance is also possible by setting the `prePayAccountBalance` boolean in the constructor to true. This will remove all funds from an account to reduce the loan amount before loan payments start. If an account has more balance than the loan amount is for, then the loan will be cancelled. 

Finally, the `simulateLoanPayBack()` method will simulate how the account balance changes over the course of the loan pay-back period with interest being considered.

---

## Project Overview

This project demonstrates how the Factory Method Pattern can be utilized to instantiate objects.

Generating a request via a concrete `Bank` factory will result in the following order of concerns:

![LoanApprovalSequence](/DesignPatterns/src/creational/factory/loanApprovalFactory/resources/LoanApprovalSequence.png)

---

## Project Architecture

The core abstraction is found in the `loan` package. The abstract class `Loan` and classes which extend it can be found here. 

The `bank` package contains resources relevant for the Factory Method Pattern implementation. The abstract `Bank` class defines the method `createLoan` which is then passed into the abstract constructor. Different types of banks can implement the `createLoan` logic to support their own rules. An enum class `LoanType` also exists, which is intended to be passed into the abstract constructor as an alternative to using strings. Additional logic can be encapsulated here if specific functionality is required upon type instantiation.

The `account` package contains the abstract class `Account`. An account has a balance and an interest rate which are handled by the abstract class. Concrete implementations can determine these values with constructor inputs.

---

## Core Class Diagram

The classes interact in the following way:

![LoanApprovalClass](/DesignPatterns/src/creational/factory/loanApprovalFactory/resources/LoanApprovalClass.png)

---

## How To Run
1. Clone the repository.
2. Compile the project.
3. Run `loanApprovalClient/Main.java`.