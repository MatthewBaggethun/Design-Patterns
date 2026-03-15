# Remote Control
### Exploring the Command Pattern

## Problem Space

This project is influenced by that shown in *Head First Design Pattern* chapter 6 *The Command Pattern*. We are shown a scenario in which a company wants to create a home automation system utilizing a remote control to control home appliances. Such a system contains many un-related objects that are only coupled by the fact that they will be used in a common client: the remote control. Connecting these objects together via a common interface is challenging if the interface is to provide any meaningful value to the system, but it is possible via a command interface which defines the `execute` method (and others that may be relevant). All objects are capable of performing work, so sharing this interface does not retract from the functionality of the objects that have already been implemented.

From here, it is a matter of implementing the Command Pattern. The objects which the remote control is controlling will be the receivers, commands will utilize recievers to perform their `execute` concrete method and the invoker will be the remote control client.

---

## Core Abstraction

The `Command` interface defines the `execute` and `undo` methods. Concrete commands utilize the logic present in their receiver classes to fulfill the interface requirements. These concrete commands are utilized by the invoker `RemoteControl` to perform their concrete methods.

---

## Project Overview

This project shows how the Command Pattern can be utilized to couple objects which are otherwise un-related. Additionally, the Command Pattern provides convenience via type safety within the invoker class. Both of these functionalities of the Command Pattern are explored to create a remote control object.

The order of concerns will look like:

![RemoteControlSequence](/DesignPatterns/src/behavioral/command/remoteControl/resources/RemoteControlSequence.png)

The client is responsible for instantiating the relevant receiver, command and invoker objects such that the invoker can perform its logic appropriately. The invoker will tell command objects to perform their `execute` methods, which utilizes the logic within the receiver object. This process can occur many times depending on the invoker and the operations it must perform. In the `RemoteControl` invoker object, this process occurs whenever the `onButtonWasPressed` or `offButtonWasPressed` methods are called.

---

## Project Architecture

Receivers are found within the `receivers` package. These classes represent household objects that the remote controller will be able to manipulate. They contain methods to support this type of functionality.

The `invoker` package contains two separate invokers. The `SimpleRemoteControl` is a simpler version of `RemoteControl`. The only difference is the number of slots available, the simple variant has a single slot whilst the other has seven. The `SimpleRemoteControl` exists to easily test concrete commands in a test harness environment.

Commands are found within the `commands` package. From here there are many packages, each named after the reciever they are utilizing. Concrete commands implement the `Command` interface which defines the `execute` and `undo` methods. The `NoCommand` object is a null-object. `NoCommand` implements the `Command` interface but does not perform any logic when its method implementations are invoked. This object exists to avoid null checks within the invoker implementations.

---

## Core Class Diagram

The classes interact in the following way:

![RemoteControlClass](/DesignPatterns/src/behavioral/command/remoteControl/resources/RemoteControlClass.png)

---

## How To Run
1. Clone the repository.
2. Compile the project.
3. Run `remoteControlClient/Main.java`