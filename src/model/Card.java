package model;

import java.util.HashMap;

public class Card {
	
	public final Suit suit;
	public final Rank rank;
	private static HashMap<String, Card> CARDS = new HashMap<>();
	
	
	private Card(Rank r, Suit s){
		this.rank = r;
		this.suit = s;

	}

	static {
		for(Suit s : Suit.values()) {
			for(Rank r : Rank.values()) {
				String temp = r + " OF " + s;
				CARDS.put(temp, new Card(r,s));
			}
		}
	}
	
	public static Card get(Rank r, Suit s){
		assert r != null && s != null;
		String temp = r + " OF " + s;
		return CARDS.get(temp);
	}
	
	public int getValue() {
		if (rank.ordinal() >= Rank.JACK.ordinal()) {
			return 10;
		}
		return rank.ordinal() + 1;
	}
} // end class