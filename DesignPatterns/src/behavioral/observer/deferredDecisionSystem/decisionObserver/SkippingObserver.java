package behavioral.observer.deferredDecisionSystem.decisionObserver;

import behavioral.observer.deferredDecisionSystem.decision.Decision;
import behavioral.observer.deferredDecisionSystem.decisionSubject.DecisionProvider;

/**
 * An observer that skips a specified number of updates before processing one.
 */
public class SkippingObserver extends AbstractDecisionObserver {

	private int skipInterval;

	/**
	 * Constructor to initialize the SkippingObserver with a DecisionProvider and a
	 * skip count.
	 * 
	 * @param decisionProvider The subject to observe.
	 * @param skipCount        The number of updates to skip before processing one.
	 */
	public SkippingObserver(DecisionProvider decisionProvider, int skipInterval) {
		super(decisionProvider);
		this.skipInterval = skipInterval;
	}

	@Override
	public void update() {
		int currentVersion = decisionProvider.getCurrentVersion();
		if (currentVersion % skipInterval == 0) {
			Decision d = decisionProvider.getLatestDecision();
			lastKnownVersion = d.getVersion();
		}
	}

	public void setSkipInterval(int skipInterval) {
		this.skipInterval = skipInterval;
	}

}
