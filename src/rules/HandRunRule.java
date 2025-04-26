/*
 * File:	HandRunRule.java
 * Author:	Tristan Emma
 * AI Help:	ChatGPT
 * Purpose: This is the "Run" rule in Cribbage when scoring a hand
 */
package rules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Card;

@ScoringRule(type = RuleType.HAND)
public class HandRunRule implements Rule {

	@Override
	public int score(Iterable<Card> cards, Card starter) {
		List<Card> all = new ArrayList<>();
        for(Card c : cards)
        	all.add(c);
        all.add(starter);
        
        List<List<Card>> subsets = getSubsets(all);
        int maxRunLength = 0;
        int runCount = 0;

        for (List<Card> subset : subsets) {
            if (subset.size() >= 3 && isRun(subset)) {
                if (subset.size() > maxRunLength) {
                    maxRunLength = subset.size();
                    runCount = 1;
                } else if (subset.size() == maxRunLength) {
                    runCount++;
                }
            }
        }

        return maxRunLength * runCount;
    }

    private List<List<Card>> getSubsets(List<Card> cards) {
        List<List<Card>> subsets = new ArrayList<>();
        int n = cards.size();
        for (int i = 1; i < (1 << n); i++) {
            List<Card> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(cards.get(j));
                }
            }
            subsets.add(subset);
        }
        return subsets;
    }

    private boolean isRun(List<Card> cards) {
        List<Integer> values = new ArrayList<>();
        for (Card c : cards) {
            values.add(c.rank.ordinal() + 1); // make Ace = 1, Jack = 11, etc.
        }
        Collections.sort(values);
        for (int i = 1; i < values.size(); i++) {
            if (values.get(i) != values.get(i - 1) + 1) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String name() {
        return "Run Rule";
    }

}
