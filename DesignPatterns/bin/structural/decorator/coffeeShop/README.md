# Coffee Shop (Decorator Pattern + Factory Pattern)

## Problem Space
The coffee shop problem extends from that which is found in chapter three of Head First Design Patterns. A coffee shop is struggling to design a system capable of handling new combinations of coffee and condiments in a sustainable and maintainable way. The team working on the project has designed their system to utilize subclassing to accommodate for every possible combination. They are struggling to keep up with additional menu items, since each item results in its own class explosion. Creating custom orders is also difficult since their design does not easily allow for customer specific inputs without creating a subclass for it. Something as simple as 2x whip for every combination is verging on unmaintainable.

This implementation aims to solve these issues and add more functionality to the system. Utilizing the Decorator and Factory patterns I will redesign the system to make adding new menu items and/or combinations of items much simpler. Additionally, an order tracking and receipt functionality has been added so that sales can be tracked externally (in a DB) at a later time. These systems will follow rules and apply logic accordingly (Ex. tax calculation, discounts...).

Making this system maintainable and functional is the goal. Every system will be separated according to concerns whilst maintaining the open/closed principle. Adding new functionality will not require old code to be rewritten. The system will operate dynamically. This is accomplished by favoring composition over inheritance. The creation of objects will be performed via factories that will also operate dynamically with type safety. This will minimize code duplication and reduce the room for error.

The project will have many themes:
 - Utilize Decorator to dynamically modify object responsibility.
 - Utilize Factory to dynamically create wrapped objects.
 - Design the system to allow for easy future additions.
 - Create a maintainable system with low code duplication.
 - Make package coupling obvious and seamless.

## Overview
Implementation of the Decorator Pattern, combined with Factory Pattern. These patterns function well together, allowing OO principles to be followed in a somewhat formulaic manor. Both patterns interact dynamically. Factories are responsible for creating wrapped objects whilst Decorator is responsible for behavior specific logic. The wrapping of objects should be performed solely through factory methods. The order of wrapping is defined by the order they are provided into factory constructors. 

## UML Diagram
![Decorator UML Diagram](resources/CoffeeShop.png)

## Project Structure
Decorator implementations are found in `beverage`, `condiment`, `order` and `receipt`.
Factory implementation are found in `beverageFactory`, `orderFactory` and `receiptFactory`.

Decorator packages are structured similarly. There are interfaces defining essential functionality and abstract classes extending on this functionality further. `condiment` only contains an abstract class since it is only defining specific functionality to place around `Beverage` classes.

Factory packages each contain an interface, abstract class, record and at least one enum. These classes are used together to seamlessly allow for dynamic object creation. The concrete factories each contain "default" in their file name. 

## How to Run
1. Clone the repository
2. Compile the project
3. Run `coffeeShopClient/Main.java`