# AI Movement (Strategy Pattern)

## Overview
The strategy pattern is used to define various means of movement that an NPC may have. Movement can be changed at runtime by switching strategies. The project is kept logically simple to showcase the
strategy pattern, so movement is defined by strings printed to the console. 

## Design Patterns Used
**Strategy Pattern**

- MovementStrategy interface defines movement
- Concrete movements inherit and build upon the interface
- Context classes contain reference to movement strategies allowing them to be altered at run-time

## UML Diagram
![Observer UML Diagram](uml/StockTradingUML.png)

## Project Structure
- `StockSubject` – Interface defining observer management and other relevant methods/fields (see overview)
- `StockObserver` – Interface for receiving updates
- `StockMarketSubject` – Concrete subject storing price updates and observer management
- `StockUpdate` – Hypothetical API used to store state of the latest stock changes

## How to Run
1. Clone the repository
2. Compile the project
3. Run `clientAIMovement/Main.java`