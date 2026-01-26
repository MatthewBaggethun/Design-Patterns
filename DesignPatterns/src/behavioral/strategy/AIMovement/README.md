# AIMovement Project (Strategy Pattern)

## Overview
The `AIMovement` project demonstrates the Strategy design pattern by implementing different movement strategies for Non-Playable Characters (NPCs). This allows NPCs to dynamically change their movement behavior at runtime.

## Project Structure
**contextNPC**
- Contains the `NPC` abstract class and its concrete subclasses:
- `Conjurer`
- `Dragon`
- `Soldier`

**movementStrategy**
- Contains the `MovementStrategy` interface and its implementations:
  - `FlyMovement`
  - `TeleportMovement`
  - `WalkMovement`

**clientAIMovement**
	- Contains the `Main` class, which demonstrates the usage of the Strategy pattern by assigning different movement strategies to NPCs.

## UML Diagram
![Observer UML Diagram](uml/AIMovement.png)