/*
 * File:	HandFifteenRule.java
 * Author:	Tristan Emma
 * AI Help:	ChatGPT
 * Purpose: This is the "15 for 2" rule in Cribbage when scoring a hand
 */
package rules;

import java.util.ArrayList;
import java.util.List;

import model.Card;

@ScoringRule(type = RuleType.HAND)
public class HandFifteenRule implements Rule {
	
	@Override
	public int score(Iterable<Card> cards, Card starter) {
		List<Card> all = new ArrayList<>();
        for(Card c : cards)
        	all.add(c);
        all.add(starter);

        int count = 0;
        int n = all.size();
        for (int i = 1; i < (1 << n); i++) {
	    	int sum = 0;
	    	for (int j = 0; j < n; j++) {
	    		if ((i & (1 << j)) != 0) {
	    			sum += all.get(j).getValue();
	            }
	        }
	    	if (sum == 15) 
	    		count++;
	    	}
	    return count * 2;
	}

	@Override
    public String name() {
        return "Fifteen Rule";
    }

}
