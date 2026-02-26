# Web Text Formatting
### Exploring Decorator, Strategy, Factory and Builder Design Patterns

## Problem Space

The document object model is a core aspect of web development. Understanding how the DOM interacts with different web elements would require a study on its own, and is outside the range of just design patterns. Therefore the focus will be narrowed down. Generating HTML elements via code is an interesting concept that also provides an excellent opportunity for design patterns to be applied to. This is not intended to be a mega-project, so the focus is not on creating a fully functional DOM from scratch, or even creating basic web designs utilizing HTML, the focus will be put more on the potential design such a system could have.

The DOM is responsible for more than just managing the structure of a webpage, it is also responsible for other functionalities like behavior and styling. These functionalities interact within the DOM to dynamically alter the structure of the webpage. To account for this I have decided to include markdown to the problem-space, since markdown and HTML share some common features. This provides an opportunity to apply design patterns to shared features, and language specific features alike. 

I will aim to imitate how GUI based web builders function, where users utilize a third party tool to create web elements and the programming is abstracted away from the user. For simplicity I will assume that the user intends to add some text to their webpage, and depending on how the user wants it formatted will determine what happens behind the scenes to generate the HTML/markdown response.

Even this simplified and highly focused problem-space has many potential bugs that can present themselves. Chiefly amongst these is generating illegal HTML/markdown responses. Preventing all of these scenarios would require a hefty test suite to ensure that HTML and/or markdown wrappers are applied in a legal manor. This is outside the scope of the project and therefore I will be omitting any sort of testing from this problem-space. The primary objective is to learn and apply design patterns.

---

## Core Abstraction

Once text has been provided by the user, they will be able to format it with either HTML or markdown. The package `preformattedText` contains the necessary utilities such that the decorator pattern can be applied to the user's text. The functional interface `InputToFormat` defines the `format` method, which is responsible for applying the types of formatting the user requests. The abstract class `AbstractFormatter` both takes and is a `InputToFormat` object. The `format` method can be called from here by child classes to call the `format` methods down the decorator chain. The context object `PlainText` is meant to be the wrapped object and holds only a StringBuilder object, representing the user's text to be formatted.

---

## Project Overview
This project demonstrates how the decorator, strategy, factory and builder design patterns can be utilized to create custom HTML and markdown responses from a text input.

Generating a response via the builders will contain the following order of concerns:
![TextWebFormattingSequence](/DesignPatterns/src/structural/decorator/textWebFormatting/resources/textWebFormattingSequenceDiagram.png)

Generating custom responses is also possible via the direct use of decorators, but this provides no benefit as it only increases the verbosity required to achieve what the builders can do.

---

## Project Architecture
The `preformattedText` package contains the core abstraction and enables the usage of the decorator pattern, as discussed in the Core Abstraction section.

Decorators can be found under three packages. Shared HTML and markdown features are found under the `sharedDecorators` package, HTML decorators under `htmlDecorators` and markdown under `markdownDecorators`. Every decorator extends the `AbstractFormatter` abstract class following the decorator pattern. The `format` method of each decorator utilizes the super method to call down the decorator chain once the context object has been wrapped accordingly.

Every decorator requires a `FormattingStrategy` as an input, which defines the implementation that will be utilized when applying a wrapper. The `formatStrategy` package contains this interface and the HTML and markdown specific strategies. Additionally, the `FormattingFactory` enum class can be used to quickly instantiate a `FormattingStrategy`, which is intended to be used by the builders.

Side note: The `FormattingFactory` enum class is entirely overkill for this project, but I have included it in the case I revisit the project later to expand and add other types of formatting.

Finally the `builders` package contains the builder classes for HTML and markdown respectively.

---

## Core Class Diagram
The interaction between the classes in `preformattedText` and `formatStrategy` are core to the project:
![CoreClassDiagram](/DesignPatterns/src/structural/decorator/textWebFormatting/resources/textWebFormattingCoreClassDiagram.png)

## How To Run
1. Clone the repository.
2. Compile the project.
3. Run `textToMarkdownClient/Main.java`.
4. Optionally wrap or configure your own markdown or HTML texts utilizing the builder or decorators.