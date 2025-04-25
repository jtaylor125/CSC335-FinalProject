/*
 * File:	HandNobsRule.java
 * Author:	Tristan Emma
 * AI Help:	ChatGPT
 * Purpose: This is the "Nobs" rule in Cribbage when scoring a hand
 */
package rules;

import model.Card;
import model.Rank;

@ScoringRule(type = RuleType.HAND)
public class HandNobsRule implements Rule {

	@Override
	public int score(Iterable<Card> cards, Card starter) {
		for (Card c : cards) {
            if (c.rank == Rank.JACK && c.suit == starter.suit) {
                return 1;
            }
        }
        return 0;
	}

	@Override
    public String name() {
        return "Nobs Rule";
    }

}
