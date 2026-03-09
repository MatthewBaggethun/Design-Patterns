# Logger
### Exploring the Singleton Pattern

## Problem Space

Logging is an excellent method of obtaining information about an applications operations without intrusively changing code or stopping the program to analyze specific interactions. It also happens to be a prime example of the Singleton Pattern in action. The focus for this project will be to implement a basic logger which will print information to the command line when processes occur.

The logger will need something to log, so I will implement classes that simulate service-like components. The goal of these simulators is to generate fake operations in a realistic context such that the logger can function in a "real world" scenario.

For the sake of completion I will be implementing two loggers. One logger will utilize enumerations to adhere to the Singleton Pattern whilst the other will be classically implemented utilizing null checks. The core functionality of both loggers will be identical in functionality but vary in implementation. Both loggers will be tested for thread safety to show-case how enumeration style singletons are an effective solution.

Each logger will be able to print different log levels; info, warn, error and debug.

---

## Core Abstraction


---

## Project Overview


---

## Project Architecture


---

## Core Class Diagram


---

## How To Run
1. Clone the repository.
2. Compile the project.
3. Run `loggerClinet/Main.java`.