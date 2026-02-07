# HTTP Pipeline (Decorator, Strategy, Factory, Observer)

## Problem Space

Understanding how HTTP clients function and interact is baseline knowledge every developer should have. Therefore, I will be recreating a typical HTTP pipeline which handles requests and responses. The pipeline will feature some commonplace features such as logging, authentication, retries, rate limiting and timeouts. The client will be capable of both synchronous and asynchronous execution. Testing will also be included in this project.

Developing a production grade pipeline is not the goal of this project. More specifically, the intent will be on client side architecture and design. Learning, understanding and implementing the relevant features utilizing design patterns is the learning outcome I wish to achieve. Servers and GUIs will be avoided entirely or I will just make an assumption of their existence.

The following features will be implemented in this project:

- Logging and diagnostics
- Authentication
- Retries and backoff
- Timeouts and fault tolerance
- Caching and rate limiting

There will be a large focus on the architecture of the project. Deep inheritance hierarchies will be avoided by utilizing the Decorator Pattern. Specific implementations will be handled by the Strategy Pattern, object creation by the Factory Pattern and Observer Pattern can be used for request listening. Duplicate logic between snyc and async methods will be considered as well during implementation.

---

## Overview

This project is a production-adjacent HTTP client library that demonstrates how design patterns can be used to create a simplisitc yet powerful system to handle a configurable request-processing pipeline. The client supports both synchronous and asynchronous execution while allowing cross-cutting concerns such as logging, retries, authentication, and timeouts to be composed dynamically at runtime.


---

## Project Architecture
**MAKE THIS A PNG**
[Architecture](/DesignPatterns/src/structural/decorator/httpPipeline/Resources/Architecture.md)

