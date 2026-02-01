
# The Observer Pattern

## What is the Observer Pattern
The Observer Pattern defines a one-to-many dependency between objects, such that when the one (the subject) changes its state, the many (the observers) are notified and updated automatically. The one-to-many relationship in Observer is also synonymous with Subject-Observer or Independent–Dependent. Observer aims to provide dependents with updated state information without tightly coupling the subject to the observers. This is accomplished via registration to a subject. Once registered, the subject will be able to communicate changes in state. The process of registering or unregistering, often synonymous with subscribing and unsubscribing (not to be confused with the Subscriber Pattern), is dynamic.

The core mechanism of the Observer Pattern is based on delegation rather than direct dependency. The subject maintains a collection of observers and notifies them when its state changes, typically by invoking a well-defined update method. Because observers interact with the subject only through a shared interface or abstract contract, the subject does not need to know any concrete details about the observers that depend on it. This allows new observers to be introduced without modifying the subject itself.

Observer adheres to several OO principles:
 - Open/Closed Principle
 - Single Responsibility Principle
 - Loose Coupling / Dependency Inversion
 
By allowing new observers to be added without changing the subject, the Observer Pattern satisfies the Open/Closed Principle. The subject focuses solely on managing and updating its own state, while observers are responsible for reacting to changes, which supports the Single Responsibility Principle. Since the subject depends only on an abstraction rather than concrete observers, the pattern promotes loose coupling and reduces direct dependencies between components.

The key benefit of the Observer Pattern is that observers are treated equally, regardless of the specific implementation each observer has. The subject does not need to know how the observers update their information, nor does it need to coordinate their behavior. This separation of concerns allows many unrelated behaviors to react to the same changes in state without introducing conditional logic or code duplication. Systems which rely on frequently changing state information opt to use the Observer Pattern to maintain flexibility and adhere to OO principles. 

Three primary variations of the Observer Pattern exist (and many sub-variations). Subjects can either decide to push changes to observers, observers can pull changes from the subject, or some combination of the two. The variation used will affect how the subject and observers are coupled. Push based approaches make the subject send all of its state changes. This means that observers will have access to information that is not relevant to their operation. In pull based approaches, the observers are capable of deciding which data is retrieved. This restricts access to only that which is required, but has run-time implications on larger scales when compared to pushing data. Real world implementations tend to use a hybrid approach, where information that may have future importance is pushed to all observers and specific updates are performed via pull. This approach is common since real systems are subject to change, and knowing what information will be useful in the future is impossible to know in the present.

There are trade-offs and inherent systemic risk associated with Observer Pattern implementations. Because updates are broadcast to all registered observers, it can be difficult to predict or reason about the full impact of a state change, especially as the number of observers grows. Debugging can become challenging when behavior emerges from multiple observers reacting independently. Additionally, careless use and/or modification of state data can lead to major bugs. This is particularly found in push based systems, where an observer gains access to state that it should not have to begin with, and that state is modified without proper precautions in place. Access issues are a common problem in many design patterns, in which the Observer Pattern is one of them. Designing observers which transmute state information into temporary or local objects to preserve the subjects state is ideal but not always possible. 


## Overview & Navigation
All sub-packages of this directory contain projects utilizing the Observer Pattern.
- [DeferredDecisionSystem](/DesignPatterns/src/behavioral/observer/deferredDecisionSystem/README.md)
- [StockTrading](/DesignPatterns/src/behavioral/observer/stockTrading/README.md)
- [WeatherMonitor](/DesignPatterns/src/behavioral/observer/weatherMonitor/README.md)
