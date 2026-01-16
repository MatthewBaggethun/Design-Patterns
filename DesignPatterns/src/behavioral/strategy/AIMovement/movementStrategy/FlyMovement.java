package behavioral.strategy.AIMovement.movementStrategy;

public class FlyMovement implements MovementStrategy{

	@Override
	public void move() {
		fly();
	}

	/**
	 * Defines the flying movement.
	 */
	private void fly() {
		System.out.println("Flying through the air.");
	}
}
