# The Decorator Pattern

## What is the Decorator Pattern
The Decorator Pattern allows responsibility to be attached to an object dynamically, without modification and without the need for sub-classing. The process of decorating an object is synonymous with "object wrapping". An object can be wrapped multiple times, as long as all the objects share an interface or abstract class. The pattern is particularly useful when an object’s responsibilities are subject to change, as new behavior can be added by wrapping the object rather than creating new subclasses. Because both decorators and the concrete component share the same interface, method calls are delegated through a chain of decorators, with each layer optionally adding behavior before or after passing control to the wrapped object.

Decorator adheres to several OO principles:
 - Open/Closed principle
 - Single Responsibility Principle
 - Favoring composition over inheritance

Every new layer acts as an extension of an object, so it is true that behavior is extended and not modified, which fulfills the Open/Closed principle. Each wrapper aims to fulfill the Single Responsibility Principle by isolating singular or grouped responsibility into a single wrapper. The behavior of the wrapped context object is determined dynamically and not statically, which fulfills the principle of favoring composition over inheritance.

An additional benefit of the Decorator Pattern is that a wrapped object can be used in place of a context object. The user of the wrapped object does not need to know how the context object is wrapped. This is the core concept that allows the context object to maintain its additional responsibilities from its wrappers and avoid sub-class explosions.

The ordering of wrappers matters. The outermost wrapper will act as the entry and exit point of the wrapped object. While method calls propagate through wrappers in the order they are stacked, some wrappers may contain deferred or conditional logic depending on the implementation, affecting when behavior is executed.

There are pros and cons to consider when implementing Decorator. Since each wrapper fulfills a specific responsibility, it is common that there will be many decorator classes. In some scenarios, it may be better to use a class-explosion approach if it would be more maintainable than having many wrappers. Depending on the complexity of each wrapper, it can be difficult to see how wrappers will interact with each other. This is made more challenging since the order of wrappers affects how logic is executed. The run-time structure can be difficult to interpret as a consequence of multiple wrappers and complex logic. The final behavior of the wrapped object results from the output of the wrapper chain rather than a single centralized implementation. The decentralized nature of the Decorator Pattern can be interpreted both as a pro and a con depending on the context.

## Overview & Navigation
All sub-packages of this directory contain projects utilizing the Decorator Pattern.
- [CoffeeShop](/DesignPatterns/src/structural/decorator/coffeeShop/README.md)
