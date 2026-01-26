package behavioral.observer.deferredDecisionSystem.defferedDecisionSystemClient;

import behavioral.observer.deferredDecisionSystem.decision.Decision;
import behavioral.observer.deferredDecisionSystem.decisionObserver.ConditionalObserver;
import behavioral.observer.deferredDecisionSystem.decisionObserver.DeferredObserver;
import behavioral.observer.deferredDecisionSystem.decisionObserver.ImmediateObserver;
import behavioral.observer.deferredDecisionSystem.decisionObserver.SkippingObserver;
import behavioral.observer.deferredDecisionSystem.decisionSubject.DecisionLog;

public class Main {

	public static void main(String[] args) {
		DecisionLog decisionLog = new DecisionLog();
		
		ConditionalObserver conditionalObserver = new ConditionalObserver(decisionLog);
		conditionalObserver.setAcceptanceRule(decision -> decision.getVersion() % 2 == 0);
		decisionLog.registerObserver(conditionalObserver);
		
		DeferredObserver decisionObserver = new DeferredObserver(decisionLog);
		decisionLog.registerObserver(decisionObserver);
		
		ImmediateObserver immediateObserver = new ImmediateObserver(decisionLog);
		decisionLog.registerObserver(immediateObserver);
		
		SkippingObserver skippingObserver = new SkippingObserver(decisionLog, 3);
		decisionLog.registerObserver(skippingObserver);
		
		
		for (int i = 1; i <= 10; i++) {
			decisionLog.addDecision(new Decision(i, "Decision " + i));
			
			if (i % 7 == 0) {
				decisionObserver.processPendingDecision();
			}
			
			System.out.println("After adding Decision " + i + ":");
			System.out.println("ConditionalObserver last known version: " + conditionalObserver.getLastKnownVersion());
			System.out.println("DeferredObserver last known version: " + decisionObserver.getLastKnownVersion());
			System.out.println("ImmediateObserver last known version: " + immediateObserver.getLastKnownVersion());
			System.out.println("SkippingObserver last known version: " + skippingObserver.getLastKnownVersion());
			System.out.println(decisionLog.printDecisionHistory());
			System.out.println();
		}
		

	}

}
