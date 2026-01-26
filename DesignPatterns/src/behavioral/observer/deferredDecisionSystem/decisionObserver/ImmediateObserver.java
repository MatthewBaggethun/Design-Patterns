package behavioral.observer.deferredDecisionSystem.decisionObserver;

import behavioral.observer.deferredDecisionSystem.decision.Decision;
import behavioral.observer.deferredDecisionSystem.decisionSubject.DecisionProvider;

public class ImmediateObserver extends AbstractDecisionObserver {

	/**
	 * Constructor to initialize the ImmediateObserver with a DecisionSubject.
	 * @param decisionSubject The subject to observe.
	 */
	public ImmediateObserver(DecisionProvider decisionProvider) {
		super(decisionProvider);
	}

	@Override
	public void update() {
		Decision decision = decisionProvider.getLatestDecision();
		lastKnownVersion = decision.getVersion();
	}

}
