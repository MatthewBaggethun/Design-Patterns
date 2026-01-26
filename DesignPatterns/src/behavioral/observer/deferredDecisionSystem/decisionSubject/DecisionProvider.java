package behavioral.observer.deferredDecisionSystem.decisionSubject;

import behavioral.observer.deferredDecisionSystem.decision.Decision;

/**
 * Interface for providing decisions in the deferred decision system. Defines
 * methods to retrieve the latest decision, a specific version of a decision,
 * and the current version number.
 */
public interface DecisionProvider {

	Decision getLatestDecision();

	String getDecision(int version);

	int getCurrentVersion();

}
