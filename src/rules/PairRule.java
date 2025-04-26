/*
 * File:	PairRule.java
 * Author:	Tristan Emma
 * AI Help:	ChatGPT
 * Purpose: This is the "Pair" (multiples) rule in Cribbage when scoring during pegging play
 */
package rules;

import model.Card;

import java.util.ArrayList;
import java.util.List;

@ScoringRule(type = RuleType.PEGGING)
public class PairRule implements Rule {

    @Override
    public int score(Iterable<Card> stack, Card starter) {
	    List<Card> cards = new ArrayList<>();
	    for (Card c : stack) {
	        cards.add(c);
	    }

	    int count = 1;
	    for (int i=cards.size()-2; i >= 0; i--) {
	        if (cards.get(i).rank == cards.get(cards.size()-1).rank) {
	            count++;
	        }
	        else break;
	    }
	    if (count == 2) return 2;
	    else if (count == 3) return 6;
	    else if (count == 4) return 12;
	    return 0;
	}

    @Override
    public String name() {
        return "Pair Rule";
    }
}
