# The Singleton Pattern

## What is the Singleton Pattern
The Singleton Pattern enforces that only one instance of a class exists throughout the application. This is done by declaring a private constructor and utilizing a public static method within the class to call the constructor. The static method is responsible for containing logic which enforces the single instance criteria. The singleton class can be obtained via an access point like a getter method.

Implementations of the Singleton Pattern can vary depending on design requirements, like single-threaded vs multi-threaded applications. In single threaded applications a null check is guaranteed to validate the existence of a singleton instance. In a multi-threaded scenario, two threads could see the instance as null and each proceed to create a new object, creating a race condition and violating the Singleton Pattern property. More specifically, two separate threads can check for null simultaneously, get deferred or blocked. In any of these scenarios the threads will continue on to instantiate the singleton such that more than one instance exists. 

There are many methods to overcome the race condition, the most common of which is to utilize double checked locking. After the null check fails, the class should define a synchronized block around the creation logic of the singleton. Additionally, the local variable should be declared volatile to prevent instruction reordering surrounding the synchronized block. This approach does block the program, however since only one instance of the class will exist throughout the application lifetime the performance loss is considered negligible.

After the release of javaSE 5 came enumerations which provided a natural solution to singleton instantiation issues. This solved the issues surrounding multi-threaded singleton instantiation since enumerations are thread safe. This is because enumerations are themselves singletons and are guaranteed by java's class loader to be instantiated only once. More specifically the instantiation, access and underlying functionality of enumeration constants are all intended (and guaranteed) to be thread safe operations. Making singletons with enumeration constants is the preferred method to create singleton objects in java for both single-threaded and multi-threaded use cases. The less verbose nature of enumeration constants is also appreciated compared to complex singleton classes. Enumeration based singletons also provide protection against serialization and reflection, since enum instances cannot be created via deserialization and their constructors cannot be called reflectively. These advantages make enum singletons the preferred approach in modern Java applications.

---

## Overview & Navigation
These projects utilize Singleton Pattern in combination with other patterns:

All sub-packages of this directory contain projects utilizing the Singleton Pattern:
- [Logger](/DesignPatterns/src/creational/singleton/logger/README.md)