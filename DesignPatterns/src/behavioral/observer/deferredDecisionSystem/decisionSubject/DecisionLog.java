package behavioral.observer.deferredDecisionSystem.decisionSubject;

import java.util.ArrayList;
import java.util.List;

import behavioral.observer.deferredDecisionSystem.decision.Decision;
import behavioral.observer.deferredDecisionSystem.decisionObserver.DecisionObserver;

/**
 * Concrete implementation of DecisionSubject for logging decisions. Maintains a
 * history of decisions and notifies observers on updates.
 */
public class DecisionLog implements DecisionSubject, DecisionProvider {

	private List<DecisionObserver> observers = new ArrayList<>();
	private List<Decision> decisionHistory = new ArrayList<>();
	private int currentVersion;

	@Override
	public void registerObserver(DecisionObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(DecisionObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers() {
		for (DecisionObserver observer : observers) {
			observer.update();
		}
	}

	/**
	 * Adds a new decision to the log and notifies observers.
	 * 
	 * @param decision the decision to add.
	 */
	public void addDecision(Decision decision) {
		decisionHistory.add(decision);
		currentVersion++;
		notifyObservers();
	}

	/**
	 * Retrieves a decision by its version number.
	 * 
	 * @param version the version number of the decision.
	 * @return
	 */
	public String getDecision(int version) {
		if (version < 0 || version >= decisionHistory.size()) {
			throw new IllegalArgumentException("Invalid version number");
		}
		return decisionHistory.get(version).getDecision();
	}

	/**
	 * Retrieves the latest decision.
	 * 
	 * @return the latest decision.
	 */
	public Decision getLatestDecision() {
		return decisionHistory.get(currentVersion - 1);
	}

	/**
	 * Gets the current version number.
	 * 
	 * @return the current version.
	 */
	public int getCurrentVersion() {
		return currentVersion;
	}

	/**
	 * Prints the entire decision history.
	 * 
	 * @return The decision history as a formatted string.
	 */
	public String printDecisionHistory() {
		StringBuilder sb = new StringBuilder();
		for (Decision decision : decisionHistory) {
			sb.append("Version ").append(decision.getVersion()).append(": ").append(decision.getDecision())
					.append("\n");
		}
		return sb.toString();
	}

}
