package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {
	private final CardStack deck = new CardStack();
	

	public Deck() {
		buildNewDeck();
		shuffle();
	}
	
	//
	public void buildNewDeck() {
		while(!deck.isEmpty()) {
			deck.pop();
		}
		
		for(Suit s : Suit.values()) {
			for(Rank r : Rank.values()) {
				deck.push(Card.get(r,s));
			}
		}
	}
	
	// 
	public void shuffle() {
		ArrayList<Card> tempList = new ArrayList<>();
		
		while(!deck.isEmpty()) 
			tempList.add(deck.pop());
		
		Collections.shuffle(tempList);
		
		for(Card c : tempList) 
			deck.push(c);
	}
	
	
	// 
	public Card drawRandom() {
		assert !deck.isEmpty();
		Random rand = new Random();
		int cutIndex = rand.nextInt((deck.size() / 2) + 1) + (deck.size() / 4);
		ArrayList<Card> tempList = new ArrayList<>();
		
		for(int i = 0; i < cutIndex; i++) {
			tempList.add(deck.pop());
		}
		
		Card drawn = deck.pop();
		
		for(Card c : tempList)
			deck.push(c);
		
		return drawn;
		
	}
	
}
