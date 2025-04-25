/*
 * File:	PeggingScoringEngine.java
 * Author:	Tristan Emma
 * AI Help:	ChatGPT
 * Purpose: This controls the Pegging rules, This is where the rules are 
 * 			dynamically (at runtime) instantiated after the runtime type check
 */
package rules;

import model.CardStack;
import java.util.*;

public class PeggingScoringEngine  {
    private final List<Rule> peggingRules = new ArrayList<>();

    public PeggingScoringEngine () {
        loadRules();
    }

    private void loadRules() {
        registerRule(new PairRule());
        registerRule(new FifteenRule());
        registerRule(new ThirtyOneRule());
        registerRule(new RunRule());
    }

    private void registerRule(Rule rule) {
        ScoringRule annotation = rule.getClass().getAnnotation(ScoringRule.class);
        if (annotation != null && annotation.type() == RuleType.PEGGING) {
            peggingRules.add(rule);
        }
    }

    public int scorePegging(CardStack stack) {
        int total = 0;
        for (Rule rule : peggingRules) {
            total += rule.score(stack, null);
        }
        return total;
    }
}