# The Observer Pattern

## What is the Observer Pattern
Things change, and so should our code. The Observer Pattern defines how objects can maintain awareness of change and even what changes they should be informed on. 
More formally, a one-to-many dependency between objects is maintained so that when one object changes state, all of its dependents are notified and updated automatically.
It is often the case that many classes rely on the same changing variables. Therefore decoupling the subject from the dependents (observers) is generally a good idea.
When a class or object no longer cares about changes in state, it should be able to stop receiving updates. Similarly, if an object needs those updates it should be able to receive them.
The Observer Pattern aims to:
- Notify dependents (observers) on changes in state
- Decouple the subject from the dependents
- Dependents can subscribe and un-subscripe to the subject dynamically

There are three primary variations in which Observer can be implemented. Subjects can either decide to push changes to observers, observers can pull changes from the subject, or some combination of the two.
There are pros and cons to each variation which can be very difficult to fully analyze in this brief description. Generally speaking, push will perform better with respect to run-time and pull will provide the best
access (observers only pull what they need). Combinations of the two are quite common as well, specifically when trying to design a system that is maintainable and "future-proof". This means pushing data that may not be
relevant to observers now, but might be in future development cycles.

The goal for these projects is not to re-invent the wheel when it comes to creating the most optimal Observer implementation. Instead, I aim to showcase these three variations and the reader can determine themselves the pros and cons
associated. The open-closed principle should be kept in mind when designing Observer systems, since redesigning them can be complex and time consuming.


## Overview & Navigation
All sub-packages of this directory contain projects utilizing the Observer Pattern.
- [StockTrading](/DesignPatterns/src/behavioral/observer/stockTrading/README.md)
- [WeatherMonitor](/DesignPatterns/src/behavioral/observer/weatherMonitor/README.md)
