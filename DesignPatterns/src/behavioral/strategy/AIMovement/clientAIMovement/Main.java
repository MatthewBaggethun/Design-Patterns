package behavioral.strategy.AIMovement.clientAIMovement;

import behavioral.strategy.AIMovement.contextNPC.Conjurer;
import behavioral.strategy.AIMovement.contextNPC.Dragon;
import behavioral.strategy.AIMovement.contextNPC.NPC;
import behavioral.strategy.AIMovement.contextNPC.Soldier;
import behavioral.strategy.AIMovement.movementStrategy.MovementStrategy;
import behavioral.strategy.AIMovement.movementStrategy.TeleportMovement;
import behavioral.strategy.AIMovement.movementStrategy.WalkMovement;

public class Main {

	public static void main(String[] args) {
		MovementStrategy walk = new WalkMovement();
		MovementStrategy teleport = new TeleportMovement();
		
		NPC soldier = new Soldier();
		soldier.display();
		soldier.performMovement();
		
		System.out.println();
		
		NPC dragon = new Dragon();
		dragon.display();
		dragon.performMovement();
		
		System.out.println("\n" + "The soldier clipped the dragon's wings. The dragon can only walk now..." + "\n");
		
		dragon.setMovementStrategy(walk);
		dragon.performMovement();
		
		System.out.println();
		
		NPC conjurer = new Conjurer();
		conjurer.display();
		conjurer.performMovement();
		
		System.out.println("\n" + "The conjurer has smitten the battlefield with a radial blast. All entities are teleported away." + "\n");
		
		soldier.setMovementStrategy(teleport);
		soldier.performMovement();
		dragon.setMovementStrategy(teleport);
		dragon.performMovement();
		
	}

}
