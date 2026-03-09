# Logger
### Exploring the Singleton Pattern

## Problem Space
Logging is an excellent method of obtaining information about an applications operations without intrusively changing code or stopping the program to analyze specific interactions. It also happens to be a prime example of the Singleton Pattern in action. The focus for this project will be to implement a basic logger which will print information to the command line when processes occur.

The logger will need something to log, so I will implement classes that simulate service-like components. The goal of these simulators is to generate fake operations in a realistic context such that the logger can function in a "real world" context.

For the sake of completion I will be implementing two loggers. One logger will utilize enumerations to adhere to the Singleton Pattern whilst the other will be classically implemented utilizing null checks. The core functionality of both loggers will be identical in functionality but vary in implementation. Both loggers will be tested for thread safety to show-case how enumeration style singletons are an effective solution.

Each logger will be able to print different log levels; info, warn, error and debug.

---

## Core Abstraction
Although not true abstractions, the `loggers` package contains two separate logger implementations. `LoggerClassic` is implemented in a pre-javaSE5 style and makes use of a typical single thread singleton implementation. On the other hand, `LoggerModern` utilizes an enumeration to maintain the Singleton Pattern defining requirement. These implementations are tested for thread safety in `Main` along with tests utilizing the simulators. Finally the enumeration `LoggingLevels` defines different logging levels just like the java standard library logger.

---

## Project Overview
This project demonstrates how a single thread singleton and thread-safe singleton can be implemented utilizing different techniques.

Calling `getInstance()` or `.INSTANCE` on either singleton will result in the following order of concerns:
![Singleton Sequence](/DesignPatterns/src/creational/singleton/logger/resources/SingletonSequence.png)

---

## Project Architecture
Singleton implementations are found within the `loggers` package.

Service components exist for each logger individually. These are found in `serviceSimulatorClassic` for the single threaded classical singleton implementation and `serviceSimulatorModern` for the modern enumeration based implementation. The implementations of the classes in these packages are nearly identical with the only difference being the type of logger used to perform the logging.

The `Main` class in `loggerClient` contains methods which show how both loggers behave in a single threaded environment utilizing their service component classes. Additionally, thread safety is also explored here.

---

## How To Run
1. Clone the repository.
2. Compile the project.
3. Run `loggerClinet/Main.java`.