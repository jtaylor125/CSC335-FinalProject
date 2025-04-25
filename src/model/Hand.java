package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rules.HandScoringEngine;

public class Hand {
	private final List<Card> hand;
	
	private static final HandScoringEngine scoringEngine = new HandScoringEngine();
	

	public Hand() {
		//Hands should start empty and be added to
		//All hand in the hand need to be accessible
		hand = new ArrayList<>();

	}
	
	/* This method adds a card to the hand variable
	 * Argument:
	 * 		card: a Card object
	 */
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	/* This method removes a card from the hand variable using the Card object argument
	 * Argument:
	 * 		card: a Card object
	 */
	public void removeCard(Card card) {
		this.hand.remove(card);
	}
	
	/* This method checks if the hand variable is empty
	 * Returns:
	 * 		true if hand is empty otherwise false
	 */
	public boolean isEmpty() {
		return hand.isEmpty();
	}

	/* This method gets a copy of the hand variable
	 * Returns:
	 * 		an ArrayList<>(hand)- a copy of hand
	 */
	public List<Card> getHand() {
		// return a copy of the hand, avoidance of problematic escaping references
		return new ArrayList<>(hand);
	}

	/* This method clears the hand variable by calling the class' clear method
	 */
	public void clear() {
		hand.clear();
	}

	/* This method returns the size of the hand variable
	 */
	public int size() {
		return hand.size();
	}

	/* This method scores a hand and the starter card from the argument passed by 
	 * calling the scoringEngine which independently define the rules for scoring
	 * Arguments:
	 * 		starter: a Card object
	 * Returns:
	 * 		an int score
	 */
	public int score(Card starter) {
		return scoringEngine.scoreHand(this.getHand(), starter);
	}
	

	public List<Card> getPlayableCards(int currentTotal) {
	    List<Card> playable = new ArrayList<>();
	    for (Card card : hand) {
	        if (card.getValue() + currentTotal <= 31) {
	            playable.add(card);
	        }
	    }
	    return playable;
	}
}
