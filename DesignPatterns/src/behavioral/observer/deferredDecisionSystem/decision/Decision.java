package behavioral.observer.deferredDecisionSystem.decision;

/**
 * Represents a decision with a specific version and decision.
 */
public class Decision {
	
	private int version;
	private String decision;
	
	public Decision(int version, String decision) {
		this.version = version;
		this.decision = decision;
	}
	
	public int getVersion() {
		return version;
	}
	
	public String getDecision() {
		return decision;
	}
}
