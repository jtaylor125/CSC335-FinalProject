package model;

import java.util.ArrayList;
import java.util.List;

public class Deck extends CardStack{
	private final List<Card> deck = new ArrayList<Card>();
	
	// TO DO
	public Deck() {
		// for card in cards, add to deck
		// shuffle?
	}
	
	// TO DO
	public void shuffle() {
		
	}
	
	// TO DO
	public void drawRandom() {
		
	}

	// cut deck in half and deal top of bottom half
	public Card cutAndDeal() {
		int middleIndex = deck.size() / 2;
		return deck.remove(middleIndex);
	}
	
}
