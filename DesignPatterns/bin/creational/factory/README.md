# The Factory Pattern

## What is the Factory Pattern
The Factory Method pattern defines an interface for creating objects, allowing subclasses to decide which concrete class to instantiate. Instead of directly instantiating objects using constructors, client code depends on an abstraction (the factory interface). Concrete factories implement the creation logic for specific product types. This approach decouples object creation from object usage. Clients depend only on abstractions rather than concrete classes, which supports the Dependency Inversion Principle. It also adheres to the Open/Closed Principle, since new product types can be introduced without modifying existing client code. The Factory Pattern is particularly useful when object creation is complex, when a class cannot anticipate which concrete class it must instantiate, or when new types are expected to be added over time. In this regard, the factory pattern is nothing more than an extension of the Dependency Inversion and Open/Closed principles combined to form a solution regarding object instantiation.

The Abstract Factory pattern provides an interface for creating families of related or dependent objects without specifying their concrete classes. While Factory Method focuses on creating a single product, Abstract Factory creates multiple related products that are designed to work together. Abstract Factory similarly builds upon the Dependency Inversion and Open/Closed principles, with the core difference being that groups of objects are instantiated instead of singular objects. Designing objects with intent to be used together in a group-like manner or singular objects capable of handling various tasks depends on the scenario. Typically, in systems that are subject to change, it is ideal to create classes utilizing the Single Responsibility Principle. If each object fulfills one logical requirement, it can often lead to groups of objects working together to solve the entire problem. In such scenarios, Abstract Factory is often a more appropriate choice than Factory Method.

Although Factory Method and Abstract Factory are related, they address different levels of object creation. Factory Method focuses on deferring the instantiation of a single product, while Abstract Factory coordinates the creation of multiple related products. In many implementations, Abstract Factory relies on Factory Methods internally to create each product in the family. Deciding which pattern to utilize is a decision that should be guided by the structure of the system. Following the DI, O/C and SR principles will lead to the utilization of the correct pattern in most cases.

## Overview & Navigation
These projects utilize Factory Pattern in combination with other patterns:
- [HttpPipeline](/DesignPatterns/src/structural/decorator/httpPipeline/README.md)
- [CoffeeShop](/DesignPatterns/src/structural/decorator/coffeeShop/README.md)
- [TextWebFormatting](/DesignPatterns/src/structural/decorator/textWebFormatting/README.md)

All sub-packages of this directory contain projects utilizing the Factory Pattern.
- [LoanApproval](/DesignPatterns/src/creational/factory/loanApprovalFactory/README.md)



