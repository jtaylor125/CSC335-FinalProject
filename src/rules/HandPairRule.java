/*
 * File:	HandPairRule.java
 * Author:	Tristan Emma
 * AI Help:	ChatGPT
 * Purpose: This is the "Pair" (multiples) rule in Cribbage when scoring a hand
 */
package rules;

import model.Card;

import java.util.ArrayList;
import java.util.List;

@ScoringRule(type = RuleType.HAND)
public class HandPairRule implements Rule {

    @Override
    public int score(Iterable<Card> cards, Card starter) {
        List<Card> all = new ArrayList<>();
        for(Card c : cards)
        	all.add(c);
        all.add(starter);
        int score = 0;

        for (int i = 0; i < all.size(); i++) {
            for (int j = i + 1; j < all.size(); j++) {
                if (all.get(i).rank == all.get(j).rank) {
                    score += 2;
                }
            }
        }

        return score;
    }

    @Override
    public String name() {
        return "Pair Rule";
    }
}
