package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import model.Card;
import model.Rank;
import model.Suit;

public class CardTest {
	@Test
	void testCard() {
		Card c1 = Card.get(Rank.ACE, Suit.SPADES);
		Card c2 = Card.get(Rank.TWO, Suit.DIAMONDS);
		Card c3 = Card.get(Rank.THREE, Suit.CLUBS);
		Card c4 = Card.get(Rank.FOUR, Suit.HEARTS);
		Card c5 = Card.get(Rank.FIVE, Suit.SPADES);
		Card c6 = Card.get(Rank.SIX, Suit.DIAMONDS);
		Card c7 = Card.get(Rank.SEVEN, Suit.CLUBS);
		Card c8 = Card.get(Rank.EIGHT, Suit.HEARTS);
		Card c9 = Card.get(Rank.NINE, Suit.SPADES);
		Card c10 = Card.get(Rank.TEN, Suit.DIAMONDS);
		Card c11 = Card.get(Rank.JACK, Suit.CLUBS);
		Card c12 = Card.get(Rank.QUEEN, Suit.HEARTS);
		Card c13 = Card.get(Rank.KING, Suit.SPADES);
		
		assertEquals(c1.getValue(), 1);
		assertEquals(c2.getValue(), 2);
		assertEquals(c3.getValue(), 3);
		assertEquals(c4.getValue(), 4);
		assertEquals(c5.getValue(), 5);
		assertEquals(c6.getValue(), 6);
		assertEquals(c7.getValue(), 7);
		assertEquals(c8.getValue(), 8);
		assertEquals(c9.getValue(), 9);
		assertEquals(c10.getValue(), 10);
		assertEquals(c11.getValue(), 10);
		assertEquals(c12.getValue(), 10);
		assertEquals(c13.getValue(), 10);
	}
	
	@Test
	void testCardTwo() {
		Card c1 = Card.get("ACE", "SPADES");
		Card c2 = Card.get("TWO", "DIAMONDS");
		Card c3 = Card.get("THREE", "CLUBS");
		Card c4 = Card.get("FOUR", "HEARTS");
		
		assertEquals(c1.getValue(), 1);
		assertEquals(c2.getValue(), 2);
		assertEquals(c3.getValue(), 3);
		assertEquals(c4.getValue(), 4);
	}
	
	@Test
	void testToString() {
		Card c1 = Card.get("ACE", "SPADES");
		assertEquals(c1.toString(),"ACE SPADES");
	}
	
}
