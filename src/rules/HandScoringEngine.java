/*
 * File:	HandScoringEngine.java
 * Author:	Tristan Emma
 * AI Help:	ChatGPT
 * Purpose: This controls the regular hand scoring rules, This is where the rules are 
 * 			dynamically (at runtime) instantiated after the runtime type check
 */
package rules;

import java.util.ArrayList;
import java.util.List;

import model.Card;

public class HandScoringEngine {
    private final List<Rule> rules = new ArrayList<>();

    public HandScoringEngine() {
        // Register hand scoring rules
        registerRule(new HandFifteenRule());
        registerRule(new HandPairRule());
        registerRule(new HandRunRule());
        registerRule(new HandFlushRule());
        registerRule(new HandNobsRule());
    }

    public void registerRule(Rule rule) {
    	 ScoringRule annotation = rule.getClass().getAnnotation(ScoringRule.class);
         if (annotation != null && annotation.type() == RuleType.HAND) {
             rules.add(rule);
         }
    }

    public int scoreHand(List<Card> cards, Card starter) {
        int score = 0;
        for (Rule rule : rules) {
            score += rule.score(cards, starter);
        }
        return score;
    }
}
