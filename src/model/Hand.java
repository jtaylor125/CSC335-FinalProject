package model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
	private final List<Card> hand = new ArrayList<Card>();
	
	//TO DO
	public Hand() {
		//Hands should start empty and be added to
		//All cards in the hand need to be accessible
	}
	
	public int calculateHandScore() {
		return 0;
	}
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	public void removeCard(Card card) {
		this.hand.remove(card);
	}
}
