/*
 * File:	FifteenRule.java
 * Author:	Tristan Emma
 * AI Help:	ChatGPT
 * Purpose: This is the "15 for 2" rule in Cribbage when scoring during pegging play
 */
package rules;

import model.Card;


@ScoringRule(type = RuleType.PEGGING)
public class FifteenRule implements Rule {

    @Override
    public int score(Iterable<Card> cards, Card starter) {
        int total = 0;
        for (Card c : cards) {
            total += c.getValue();
        }
        return (total == 15) ? 2 : 0;
    }

    @Override
    public String name() {
        return "Fifteen Rule";
    }
}
