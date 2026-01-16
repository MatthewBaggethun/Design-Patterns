package behavioral.strategy.AIMovement.movementStrategy;

public class TeleportMovement implements MovementStrategy{

	@Override
	public void move() {
		teleport();
	}
	
	/**
	 * Defines the teleportation movement.
	 */
	private void teleport() {
		System.out.println("Appearing at will.");
	}

}
