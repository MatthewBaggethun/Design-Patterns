package behavioral.strategy.AIMovement.nonPlayerControlledCharacter;

import behavioral.strategy.AIMovement.movementStrategy.MovementStrategy;

public abstract class NPC {
	MovementStrategy movementStrategy;
	
	public void setMovementStrategy(MovementStrategy movementStrategy) {
		this.movementStrategy = movementStrategy;
	}
	
	/**
	 * Performs movement as defined by the MovementStrategy implementation.
	 */
	public void performMovement() {
		this.movementStrategy.move();
	}
	
	/**
	 * Print to the console NPC characteristics.
	 */
	public abstract void display();

}
