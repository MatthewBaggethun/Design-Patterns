# HTTP Request Pipeline in Java
### Exploring Decorator, Strategy, Factory and Builder Design Patterns

## Problem Space

HTTP clients are complex systems that are responsible for more than just sending and receiving data. Much of this complexity is abstracted away from the developer, enabling rapid development, but often reducing the understanding required to perform a request. Gaining insight into how a request is executed can provide a deeper understanding of larger frameworks, such as Spring. This project aims to explore how an HTTP request is performed and what types of concerns are involved.

Implementing a system capable of handling requests can quickly devolve into tightly coupled, difficult-to-extend code if design principles are ignored. Design patterns are used throughout this project to make the system easier to reason about and to encourage extensibility. Ultimately, the project is designed to support additional functionality without requiring significant refactoring of existing code.

The following principles will be considered carefully throughout the development of the project:
 - The open/closed principle
 - Favor composition over inheritance
 - Program to an interface, not an implementation
 - Strive for loosely coupled designs

The project contains the following learning points:
 - System design
 - Design patterns
 - Client/Server web interactions
 - Sync/Async implementations

---

## Core Abstraction

At the core of the system is the concrete class `JavaHttpHandler`. This class implements the `HttpHandler` interface, which defines methods for both synchronous and asynchronous execution of requests. Defining both execution models at the interface level ensures that decorators encapsulate their responsibilities consistently, regardless of how a request is executed.

`JavaHttpHandler` interacts with the classes in the `Model` package, which define the structure and behavior of HTTP requests and responses. Internally, `java.net` is used to perform actual network operations, while requests and responses are transformed into the project’s own `Request` and `Response` abstractions.

Side note:
The `Model` package could be avoided entirely in favor of using `java.net` types directly. These abstractions exist primarily as a learning exercise, allowing the request pipeline to be expressed explicitly and independently of the underlying HTTP library.

---

## Project Overview

This project demonstrates how design patterns can be utilized to create a system capable of generating a configurable request-processing pipeline. The client supports both synchronous and asynchronous execution while allowing concerns to be composed dynamically at runtime.

Generating a request with the builder and factory will result in the following order of concerns:

Request --(contains)--> AuthenticationDecorator -> LoggingDecorator -> RetryDecorator -> TimeoutDecorator -> JavaHttpHandler --(obtains)--> Response

Generating custom requests with an altered order of concerns is also possible, but not supported by the builder and factory implementation. This can be done by wrapping a `JavaHttpHandler` in any order. Note that the order of concerns does matter in the request pipeline. For example: if logging is placed outside of a retry decorator it cannot be aware of the repeated requests.

The builder and factory implementations intentionally fix the order of concerns to make the pipeline configuration standardized. This practice is common in larger frameworks, like Spring. Specific implementations should be "restricted" to developers who understand the implementation of the pipeline and the ramifications of concern ordering.

If a custom implementation will be performed repeatedly, then a new builder and factory implementation can be created, or if slight modifications are needed then the current implementations can be extended further. Creating a new pipeline is made easier due to the dynamic nature of the decorators.

---

## Project Architectural Overview

The `Model` package contains classes responsible for representing requests and responses. Every request and response contains headers and optionally a body; the request type also defines idempotency. These features are all accounted for in the `Response`, `Request`, `Headers` and `HttpMethod` classes respectively.

The core abstraction `JavaHttpHandler` utilizes `Java.Net` to transform requests and responses into `Request` and `Response` objects. Additionally, `JavaHttpHandler` extends the `HttpHandler` interface, which defines synchronous and asynchronous executions. This extension is also a requirement to adhere to the Decorator Pattern. Both the `Model` and `HttpHandler` packages should be understood before proceeding further.

Request concerns are implemented via decorators around `JavaHttpHandler`, which are found in the `Decorators` package. Each decorator holds a reference to an `HttpHandler`. This allows the context class to be wrapped an infinite number of times. The order of wrapping will affect the order in which concerns are executed. The outermost wrapper will act as the entry and exit for the pipeline.

Concerns such as authentication and connectivity can be implemented in many ways. The `Strategy` package contains sub packages for such concerns. Strategies can be swapped dynamically to alter performance. The `Auth` sub package contains authentication strategy implementations, and the `Retry` sub package contains connectivity strategy implementations.

Executing a request is done by configuring the builder in `PipelineConfig`. The config is passed to `PipelineFactory`, which ensures the order of concerns is respected.

Custom requests can be created dynamically by wrapping an `JavaHttpHandler` with decorators. There are no restrictions to custom implementations, but order will affect the process pipeline.

---

## UML of Relevant Interactions
![HttpPipeline](/DesignPatterns/src/structural/decorator/httpPipeline/Resources/HttpPipeline.png)

## How To Run
1. Clone the repository
2. Compile the project
3. Run `httpPipelineClient/Main.java`