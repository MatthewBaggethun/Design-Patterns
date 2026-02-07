# The Strategy Pattern

## What is the Strategy Pattern
A typical problem in programming is defining how a class can implement multiple different ways a certain behavior can be performed.
The Strategy Pattern aims to fix this by defining a family of algorithms, encapsulating the variable behavior, and makes them interchangeable.
Strategy lets the algorithm vary independently from clients that use it. Instead of hard-coding behavior, behaviors should be delegated at run-time. The following principles outline what the Strategy Pattern aims to provide:
- Encapsulate what varies
- Favor composition over inheritance
- Behavior is determined at run-time, not compile-time

In Head First Design Patterns, they define such a scenario with a Duck class. There are many different types of ducks that can all quack. The behavior of the quack can be different depending on its type.
Instead of hard-coding many Duck classes, it is instead possible to encapsulate what varies (the quack) and inherit the rest.

## Overview & Navigation
These projects utilize Strategy Pattern in combination with other patterns:
- [HttpPipeline](/DesignPatterns/src/structural/decorator/httpPipeline/README.md)

All sub-packages of this directory contain projects utilizing only Strategy Pattern. These projects are extremely simplistic.
- [AIMovement](/DesignPatterns/src/behavioral/strategy/AIMovement/README.md)
- [PaymentSystem](/DesignPatterns/src/behavioral/strategy/paymentSystem/README.md)
- [TextFormatting](/DesignPatterns/src/behavioral/strategy/textFormatting/README.md)