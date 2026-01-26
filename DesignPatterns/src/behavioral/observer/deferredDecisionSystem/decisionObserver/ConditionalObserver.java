package behavioral.observer.deferredDecisionSystem.decisionObserver;

import java.util.function.Predicate;

import behavioral.observer.deferredDecisionSystem.decision.Decision;
import behavioral.observer.deferredDecisionSystem.decisionSubject.DecisionProvider;

public class ConditionalObserver extends AbstractDecisionObserver {

	private Predicate<Decision> acceptanceRule;

	/**
	 * Constructor to initialize the ConditionalObserver with a DecisionSubject and
	 * a version threshold.
	 * 
	 * @param decisionProvider The subject to observe.
	 * @param thresholdVersion The version threshold for updates.
	 */
	public ConditionalObserver(DecisionProvider decisionProvider) {
		super(decisionProvider);
	}

	/**
	 * Sets the acceptance rule for updates.
	 * 
	 * @param acceptanceRule A predicate that determines whether to accept an update
	 *                       based on the Decision.
	 */
	public void setAcceptanceRule(Predicate<Decision> acceptanceRule) {
		this.acceptanceRule = acceptanceRule;
	}

	@Override
	public void update() {
		Decision decision = decisionProvider.getLatestDecision();
		if (acceptanceRule.test(decision)) {
			lastKnownVersion = decision.getVersion();
		}
	}

}
