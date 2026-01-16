package behavioral.strategy.AIMovement.contextNPC;

import behavioral.strategy.AIMovement.movementStrategy.TeleportMovement;

public class Conjurer extends NPC{
	
	public Conjurer() {
		this.movementStrategy = new TeleportMovement();
	}

	@Override
	public void display() {
		System.out.println("I am a magical conjurer.");
	}

}
