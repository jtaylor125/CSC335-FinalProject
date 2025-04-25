package model;

import java.util.HashMap;

public class Card {
	// instance variables
	public final Suit suit;
	public final Rank rank;
	// FLYWEIGHT store
	private static HashMap<String, Card> CARDS = new HashMap<>();
	
	// Constructor
	private Card(Rank r, Suit s){
		this.rank = r;
		this.suit = s;
		
	}
	// static block
	static {
		for(Suit s : Suit.values()) {
			for(Rank r : Rank.values()) {
				String temp = r + " OF " + s;
				CARDS.put(temp, new Card(r,s));
			}
		}
	}
	
	/* This method gets a Card object given the Rank and the Suit Enums of a Card
	 * and returns a Card
	 * Arguments:
	 * 		r: A Rank Enum
	 * 		s: A Suit Enum
	 * Returns:
	 * 		a Card object
	 */
	
	public static Card get(Rank r, Suit s) {
		assert r != null && s != null;
		String temp = r + " OF " + s;
		return CARDS.get(temp);
	}
	
	/* This method gets a card given the String representations of a Rank
	 * and Suit of a Card and returns a Card object
	 * Arguments:
	 * 		rank: a String of a Rank
	 * 		suit: a String of a Suit
	 */
	public static Card get(String rank, String suit) {
		String temp = rank.toUpperCase() + " OF " + suit.toUpperCase();
		return CARDS.get(temp);
	}
	
	/* This method returns a value of a Card Object
	 * Returns:
	 * 		an int representing the value of a Rank in Cribbage
	 */
	public int getValue() {
		if (rank.ordinal() >= Rank.TEN.ordinal()) {
			return 10;
		}
		return rank.ordinal() + 1;
	}
	
	@Override
	public String toString() {
		return rank.toString() + " " + suit.toString();
	}
} // end class