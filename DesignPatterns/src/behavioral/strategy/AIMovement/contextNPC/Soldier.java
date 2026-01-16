package behavioral.strategy.AIMovement.contextNPC;

import behavioral.strategy.AIMovement.movementStrategy.WalkMovement;

public class Soldier extends NPC{
	
	public Soldier() {
		this.movementStrategy = new WalkMovement();
	}

	@Override
	public void display() {
		System.out.println("I am a soldier.");
	}

}
