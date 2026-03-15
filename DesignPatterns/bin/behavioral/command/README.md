# The Command Pattern

## What is the Command Pattern
The Command Pattern encapsulates method invocation as objects. This is accomplished by implementing interface methods to perform specific tasks. The most common methods defined by the command interface are `execute` and `undo`. These methods generally perform an action via `execute` and are capable of reversing the action via `undo`. Creating concrete commands which implement a command interface allow for specific implementations of these methods. In this way, `execute` and `undo` have been encapsulated and can be called through their concrete classes, effectively transforming methods into first class objects.

Although concrete commands share an interface and are now type-safe, the system is still not ideal for client use. At this stage the system contains many concrete commands which encapsulate specific functionalities. It is common to have many concrete commands "per object". For example, imagine a class with five methods, and each method must be transformed into a concrete command. The initial class containing the five methods is known as the Receiver class. Receivers contain methods needed to carry out requests but do not have the ability to call the `execute` functions of the concrete commands. Concrete commands can utilize logic from within Receiver classes to perform their `execute` and `undo` methods.

Finally, an Invoker class is expected. This class is responsible for executing the methods defined by the command interface, like `execute` and `undo`. The Invoker does not know or care how the concrete commands perform their work. In this way the Invoker is decoupled from the classes performing the core logic. The Invoker class can either be the client itself, or some other high-level class which performs coupling logic. The system is designed such that the Invoker can perform these coupling operations whilst remaining decoupled from the classes/types it utilizes.


## Key benefits and use-cases
A key benefit of the Command Pattern is the ability for clients to parameterize the command interface type, such that concrete implementations can be applied without compile time errors. Such scenarios occur frequently in systems where many un-related objects have a common functionality. Another common scenario where the Command Pattern is beneficial is when many un-related objects are expected to be contained within a client.

Systems which can make use of the Command Pattern make themselves apparent quickly. Identifying that the system contains many un-related objects and a common client are good initial indicators. The final major indicator is when neither the Decorator or Strategy patterns are good fits.

The Strategy Pattern is utilized when a common functionality needs to be implemented, but the functionality will vary depending on the concrete implementation. An underlying requirement for implementing the Strategy Pattern is that the context object/client utilizes a common interface parameter and the concrete implementations are at least loosely coupled. This is not possible in cases where objects are totally un-related, since these objects cannot share an interface in any meaningful way. The only common functionality which all objects share is that they are capable of executing work. This is represented via the `execute` method in the command interface, and provides a way for un-related objects to share a common interface.

The Decorator Pattern is utilized when a context object is required to play many roles, such that an object can have a modifiable behavior. This is accomplished by wrapping the context object dynamically via a shared interface. Again, the problem of un-related objects comes into play since it is not possible to wrap one object with another that does not share any interface with it (there are some technical exceptions via use of the Adapter Pattern). The Command Pattern is required to couple these un-related objects together. Admittedly this connection between Decorator and Command may seem far-fetched, but the essence of modifying behavior can be applied to the Command Pattern as well. In this case, the modifying behavior is determined by the type of command, just like a decorated object has modifying behavior determined by its wrappers.

I view the Command Pattern as an flexible alternative between the Strategy and Decorator patterns. Although these patterns have many use-cases, there is typically one which stands out as a clear solution to structure a system around. When no clear solution exists, the Command Pattern offers a solution where one may otherwise not exist.

---

## Overview & Navigation
These projects utilize the Command Pattern in combination with other patterns:

All sub-packages of this directory contain projects utilizing the Command Pattern:
- [Remote Control](/DesignPatterns/src/behavioral/command/remoteControl/README.md)