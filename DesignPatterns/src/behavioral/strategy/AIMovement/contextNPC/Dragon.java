package behavioral.strategy.AIMovement.contextNPC;

import behavioral.strategy.AIMovement.movementStrategy.FlyMovement;

public class Dragon extends NPC{
	
	public Dragon() {
		this.movementStrategy = new FlyMovement();
	}

	@Override
	public void display() {
		System.out.println("I am a dragon.");
	}

}
