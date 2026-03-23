# Text Editor
### Exploring the Command and Singleton Pattern

## Problem Space

A frequent use-case for the Command Pattern derives from its easily implemented undo/redo functionality. For example, Git utilizes the Command Pattern, and in essence shows the Command Pattern expanded to its fullest potential. IDEs and word processors are also common technologies which utilize the Command Pattern for undo/redo functionality. The concrete implementations may vary, yet the core functionality remains the same. Another similarity in these implementations is the use of a tree structure to maintain a history of commands, such that undoing and redoing can be retraced to any point in the tree. This is a case in which DSA (data structures and algorithms) and design patterns become closely linked together. 

Exploring how DSA and the Command Pattern are combined to form an effective undo/redo functionality is a topic worth exploring. The goal is not to recreate Git or an IDE, instead I am narrowing down the focus for this project to its simplest form. I will create a simple receiver which contains the ability to edit text, similar to any other IDE. This will narrow the concrete Command classes to a minimum to keep the complexity low. There will be no GUI, everything will be performed statically via a Main method. The Invoker will be responsible for the execute/undo/redo functionality, and also for maintaining a tree structure such that retracing to any point is possible.

---

## Core Abstraction

The `Command` interface defines the `execute` and `undo` methods. Concrete commands utilize the logic present in the receiver `Editor` to fulfill the interface requirements. These concrete commands are utilized by the invoker `CommandManager` to perform their concrete methods.

---

## Project Overview

This project shows how the Command Pattern can be utilized to implement undo and redo functionality utilizing a tree structure.

The order of concerns will look like:

![TextEditorSequence](/DesignPatterns/src/behavioral/command/textEditor/resources/TextEditorSequence.png)

The client is responsible for instantiating the relevant receiver, command and invoker objects such that the invoker can perform its logic appropriately. The invoker will tell command objects to perform their `execute` or `undo` methods, which utilizes the logic within the receiver object. In the `CommandManager` invoker object, the `redo` method is also defined, which does not exist in the `Command` interface. This functionality is performed via help of a tree structure maintained by the `CommandManager`.

---

## Project Architecture

Receivers are found within the `receivers` package. Here the Singleton `Editor` exists, which defines methods for text editing and for `Page` management. A `Page` is a POJO which represents a document. `Editor` is an enum based Singleton, which is done for many reasons. Primarily, the methods found within `Editor` indicate that a class implementation could cause problems for maintaining state if the project was larger. Having many `Editor` objects is also redundant since there would be no use for this outside of a multi-threaded environment. In that case, the complexity would increase to an unreasonable level which would make managing state difficult. Enums are thread safe, albeit with a performance cost. In any case, there is no multi-threading present in this project, but the simpler state management provided by the enum is ideal. The enum implementation also makes an expansion of the project easier for these reasons.

The `invoker` package contains the Invoker `CommandManager` which is responsible for performing the `execute` and `undo` methods defined by the `Command` interface. The `redo` method is an expansion upon the `Command` interface which is made possible via the tree structure implementation, which is also maintained by the `CommandManager` object. The tree consists of `CommandNode` objects which contain pointers to their parent concrete command and their children concrete commands. The `CommandManager` contains the `CommandNode` objects within a HashMap to traverse the tree. The functionality provided by the tree implementation can be expanded further to provide a replay and snapshot capability to the project, allowing commands to be replayed to a certain point in the tree to restore state or to restore the state to a previous version.

Commands are found within the `commands` package. Here the `Command` interface defining the `undo` and `redo` methods is found, along with the `NoCommand` concrete implementation. The sub-package `textCommands` contains concrete implementations of the `Command` interface utilizing logic from the receiver `Editor`.

---

## Core Class Diagram

The classes interact in the following way:

![TextEditorClass](/DesignPatterns/src/behavioral/command/textEditor/resources/TextEditorClass.png)

Outgoing connections from `Editor` have not been made in the diagram since the state is shared and only the singular instance exists.

---

## How To Run
1. Clone the repository.
2. Compile the project.
3. Run `textEditor/Client/TestHarness.java`
4. Create your own commands and test the execute/undo/redo functionality.