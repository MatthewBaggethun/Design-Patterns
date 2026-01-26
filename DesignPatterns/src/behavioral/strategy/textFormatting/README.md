# Text Formatting Project (Strategy Pattern)

## Overview
The `textFormatting` project demonstrates the Strategy design pattern by implementing different formatting strategies to be used on strings.
The formatting strategy can be dynamically changed at run-time depending on client needs.

## Project Structure
**contextTextEditor**

Contains a single class `ContextTextEditor` which stores a `Format` field parameter. The formatting strategy can be changed dynamically via the setter. Formatting is executed by this class.

**formatStrategy**
- Contains the `FormatStrategy` interface and its implementations:
  - `FormatLowerCase`
  - `FormatUpperCase`

**clientTextFormatting**

Contains the `Main` class, which demonstrates the usage of the Strategy pattern by assigning different format strategies and performing those formats.

## UML Diagram
![Strategy UML Diagram](uml/textFormatting.png)