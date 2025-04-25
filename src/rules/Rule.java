/*
 * File:	Rule.java
 * Author:	Tristan Emma
 * AI Help:	ChatGPT
 * Purpose: defines the interface for all the different rules for scoring
 * 			in the game of Cribbage. This interface is a part of the 
 * 			metaprogrammed Dynamic Rule Loader
 * 
 */

package rules;
import model.Card;


public interface Rule {
    int score(Iterable<Card> cards, Card starter); // starter can be null for pegging
    String name();
}
