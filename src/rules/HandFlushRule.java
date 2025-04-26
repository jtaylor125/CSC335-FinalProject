/*
 * File:	HandFlushRule.java
 * Author:	Tristan Emma
 * AI Help:	ChatGPT
 * Purpose: This is the "Flush" rule in Cribbage when scoring a hand
 */
package rules;

import java.util.HashMap;

import model.Card;
import model.Suit;

@ScoringRule(type = RuleType.HAND)
public class HandFlushRule implements Rule {

	@Override
	public int score(Iterable<Card> cards, Card starter) {	
		HashMap<Suit, Integer> map = new HashMap<>();
		
		for(Suit suit : Suit.values()) 
			map.put(suit, 0);
	
        for(Card c : cards) 
        	map.put(c.suit, (map.get(c.suit) + 1));
        
        map.put(starter.suit, (map.get(starter.suit) + 1));
        
        for(Suit suit :Suit.values()) {
        	if(map.get(suit) == 4)
        		return 4;
        	if(map.get(suit) == 5)
        		return 5;
        }
        
        return 0;
	}

	@Override
	public String name() {
	    return "Flush Rule";
	}

}
