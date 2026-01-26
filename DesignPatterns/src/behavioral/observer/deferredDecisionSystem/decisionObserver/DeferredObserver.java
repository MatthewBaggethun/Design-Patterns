package behavioral.observer.deferredDecisionSystem.decisionObserver;

import behavioral.observer.deferredDecisionSystem.decisionSubject.DecisionProvider;

public class DeferredObserver extends AbstractDecisionObserver {

	/**
	 * Constructor to initialize the DeferredObserver with a DecisionSubject.
	 * 
	 * @param decisionProvider The subject to observe.
	 */
	public DeferredObserver(DecisionProvider decisionProvider) {
		super(decisionProvider);
	}

	@Override
	public void update() {
		// DeferredObserver does not update immediately upon notification.
		// It will check for updates only when explicitly requested.
	}

	/**
	 * Method to process pending decisions when explicitly requested.
	 */
	public void processPendingDecision() {
		var decision = decisionProvider.getLatestDecision();
		if (decision.getVersion() > lastKnownVersion) {
			lastKnownVersion = decision.getVersion();
		}
	}

}
