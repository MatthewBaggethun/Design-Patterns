package behavioral.observer.deferredDecisionSystem.decisionObserver;

import behavioral.observer.deferredDecisionSystem.decisionSubject.DecisionProvider;

/**
 * Abstract base class for DecisionObservers. Implements common functionality
 * for observers in the deferred decision system.
 */
public abstract class AbstractDecisionObserver implements DecisionObserver {

	protected DecisionProvider decisionProvider;
	protected int lastKnownVersion = 0;

	/**
	 * Constructor to initialize the AbstractDecisionObserver with a
	 * DecisionProvider.
	 * 
	 * @param decisionProvider The provider to observe.
	 */
	protected AbstractDecisionObserver(DecisionProvider decisionProvider) {
		this.decisionProvider = decisionProvider;
	}

}
