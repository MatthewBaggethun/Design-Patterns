package behavioral.observer.deferredDecisionSystem.decisionObserver;

import behavioral.observer.deferredDecisionSystem.decision.Decision;
import behavioral.observer.deferredDecisionSystem.decisionSubject.DecisionProvider;

/**
 * An observer that updates its state immediately upon notification.
 */
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
