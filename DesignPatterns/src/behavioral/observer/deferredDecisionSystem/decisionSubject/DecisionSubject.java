package behavioral.observer.deferredDecisionSystem.decisionSubject;

import behavioral.observer.deferredDecisionSystem.decisionObserver.DecisionObserver;

/**
 * Marker interface for decision subjects in a deferred decision system.
 * Contains standard methods for subjects in an observer pattern.
 */
public interface DecisionSubject {
	
	void registerObserver(DecisionObserver observer);
	
	void removeObserver(DecisionObserver observer);
	
	void notifyObservers();
}
