package behavioral.strategy.AIMovement.movementStrategy;

public class WalkMovement implements MovementStrategy{

	@Override
	public void move() {
		walk();
	}
	
	/**
	 * Defines the walking movement.
	 */
	private void walk() {
		System.out.println("Walking on the ground.");
	}

}
