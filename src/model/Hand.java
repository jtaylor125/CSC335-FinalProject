package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import rules.HandScoringEngine;

public class Hand {
	private final List<Card> hand;
	
	private static final HandScoringEngine scoringEngine = new HandScoringEngine();
	
	//TO DO
	public Hand() {
		//Hands should start empty and be added to
		//All hand in the hand need to be accessible
		hand = new ArrayList<>();

	}
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	public void removeCard(Card card) {
		this.hand.remove(card);
	}
	
	public boolean isEmpty() {
		return hand.isEmpty();
	}
	public List<Card> getHand() {
		// return a copy of the hand, avoidance of problematic escaping references
		return new ArrayList<>(hand);
	}

	public void clear() {
		hand.clear();
	}

	public int size() {
		return hand.size();
	}

	// score the hand + starter card
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
