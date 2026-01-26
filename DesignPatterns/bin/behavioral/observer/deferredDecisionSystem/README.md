# Deferred Decision System (Temporal Observer Pattern)

## Problem Space
Most Observer implementations assume an immediate reaction to a state change. This project will explore what happens when observers are intended to not react instantaneously but rather react later on a past state change or 
based on a rule.
In this project we will have a system that produces decisions over time, where observers will be notified about these decisions but wait to act on them until a later time. The subject wont know when observers react or which
updates they care about. 

More specifically there will be observers which will act as follows:
- Ignore update
- Process update now
- Process update later

Observers will be responsible for their own rules and the subject is not aware about what observers do.

To keep complexity low, the subject will only maintain a single value, think of it as a configuration setting.

## Overview


## Design Patterns Used
**Observer Pattern (Hybrid Push-Pull Model)**

- Observers register with a subject
- Subject notifies observers when its state changes
- Observers get relevant and irrelevant information (hence hybrid push-pull)
- Loose coupling between subject and observers
- Observer logic can be deferred or instantaneous

## UML Diagram
![Observer UML Diagram]()

## Project Structure
- `DescisionLog` - Concrete subject maintaining an "evolving" value.
- `StrictObserver` - Concrete observer that processes a change instantly.
- `LazyObserver` - Concrete observer that processes a change when triggered.
- `ConditionalObserver` - Concrete observer that pulls and tests the value against a rule. Accepts only if the rule is passed, discards permanently otherwise.
- `AuditObserver` - Concrete observer that records decision making.

## How to Run
1. Clone the repository
2. Compile the project
3. Run ``