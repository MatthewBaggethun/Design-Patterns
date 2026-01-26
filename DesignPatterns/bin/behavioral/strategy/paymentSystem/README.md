# Payment System Project (Strategy Pattern)

## Overview
The `paymentSystem` project demonstrates the Strategy design pattern by implementing different payment strategies to be used at a checkout. The payment strategy can be dynamically changed at run-time depending on user preference.

## Project Structure
**contextCheckout**

Contains a single class `Checkout` which stores a `PaymentStrategy` field parameter. The strategy can be changed dynamically, in this case a new object is created for a new checkout procedure.
Payments can be set and performed here.

**paymentStrategy**
- Contains the `PaymentStrategy` interface and its implementations:
  - `PaymentCreditCard`
  - `PaymentCrypto`
  - `PaymentPayPal`

**clientPaymentSystem**

Contains the `Main` class, which demonstrates the usage of the Strategy pattern by assigning different payment strategies and performing those payments.

## UML Diagram
![Strategy UML Diagram](uml/paymentSystem.png)