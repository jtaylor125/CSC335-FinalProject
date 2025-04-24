package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class HandTest {
	@Test
	void testHand() {
		Hand h = new Hand();
		assertTrue(h.isEmpty());
	}
	
	@Test
	void testAdd() {
		Hand h = new Hand();

		Card c1 = Card.get(Rank.FOUR, Suit.SPADES);
		Card c2 = Card.get(Rank.JACK, Suit.HEARTS);
		Card c3 = Card.get(Rank.TEN, Suit.CLUBS);
		Card c4 = Card.get(Rank.THREE, Suit.DIAMONDS);
		Card c5 = Card.get(Rank.QUEEN, Suit.DIAMONDS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		h.addCard(c5);
		
		assertEquals(h.size(),5);
	}
	
	@Test
	void testRemove() {
		Hand h = new Hand();

		Card c1 = Card.get(Rank.FOUR, Suit.SPADES);
		Card c2 = Card.get(Rank.JACK, Suit.HEARTS);
		Card c3 = Card.get(Rank.TEN, Suit.CLUBS);
		Card c4 = Card.get(Rank.THREE, Suit.DIAMONDS);
		Card c5 = Card.get(Rank.QUEEN, Suit.DIAMONDS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		h.addCard(c5);
		
		h.removeCard(c2);
		h.removeCard(c3);
		
		assertEquals(h.size(),3);
	}
	
	@Test
	void testGetHand() {
		Hand h = new Hand();

		Card c1 = Card.get(Rank.FOUR, Suit.SPADES);
		Card c2 = Card.get(Rank.JACK, Suit.HEARTS);
		Card c3 = Card.get(Rank.TEN, Suit.CLUBS);
		Card c4 = Card.get(Rank.THREE, Suit.DIAMONDS);
		Card c5 = Card.get(Rank.QUEEN, Suit.DIAMONDS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		h.addCard(c5);
		
		List<Card> handList = h.getHand();
		
		assertEquals(handList.get(0),c1);
		assertEquals(handList.get(1),c2);
		assertEquals(handList.get(2),c3);
		assertEquals(handList.get(3),c4);
		assertEquals(handList.get(4),c5);
	}
	
	@Test
	void testClear() {
		Hand h = new Hand();

		Card c1 = Card.get(Rank.FOUR, Suit.SPADES);
		Card c2 = Card.get(Rank.JACK, Suit.HEARTS);
		Card c3 = Card.get(Rank.TEN, Suit.CLUBS);
		Card c4 = Card.get(Rank.THREE, Suit.DIAMONDS);
		Card c5 = Card.get(Rank.QUEEN, Suit.DIAMONDS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		h.addCard(c5);
		
		h.clear();
		
		assertTrue(h.isEmpty());
	}
	
	@Test
	void testScoreZero() {
		Hand h = new Hand();

		Card c1 = Card.get(Rank.FOUR, Suit.CLUBS);
		Card c2 = Card.get(Rank.SIX, Suit.DIAMONDS);
		Card c3 = Card.get(Rank.THREE, Suit.SPADES);
		Card c4 = Card.get(Rank.TEN, Suit.HEARTS);
		Card c5 = Card.get(Rank.SEVEN, Suit.CLUBS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		
		assertEquals(0,h.score(c5));
	}
	
	@Test
	void testScoreManyFifteens() {
		Hand h = new Hand();
		
		Card c5 = Card.get(Rank.SEVEN, Suit.CLUBS);
		
		Card c1 = Card.get(Rank.EIGHT, Suit.CLUBS);
		Card c2 = Card.get(Rank.TEN, Suit.DIAMONDS);
		Card c3 = Card.get(Rank.FIVE, Suit.SPADES);
		Card c4 = Card.get(Rank.TWO, Suit.HEARTS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		
		assertEquals(6,h.score(c5));
	}
	
	@Test
	void testScorePairs() {
		Hand h = new Hand();
		
		Card c5 = Card.get(Rank.SEVEN, Suit.CLUBS);
		
		Card c1 = Card.get(Rank.EIGHT, Suit.CLUBS);
		Card c2 = Card.get(Rank.EIGHT, Suit.DIAMONDS);
		Card c3 = Card.get(Rank.SEVEN, Suit.SPADES);
		Card c4 = Card.get(Rank.TWO, Suit.HEARTS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		
		assertEquals(12,h.score(c5));
	}
	
	@Test
	void testScoreThrees() {
		Hand h = new Hand();
		
		Card c5 = Card.get(Rank.SEVEN, Suit.CLUBS);
		
		Card c1 = Card.get(Rank.EIGHT, Suit.CLUBS);
		Card c2 = Card.get(Rank.EIGHT, Suit.DIAMONDS);
		Card c3 = Card.get(Rank.EIGHT, Suit.SPADES);
		Card c4 = Card.get(Rank.TWO, Suit.HEARTS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		
		assertEquals(12,h.score(c5));
	}
	
	@Test
	void testScoreFours() {
		Hand h = new Hand();
		
		Card c5 = Card.get(Rank.SEVEN, Suit.CLUBS);
		
		Card c1 = Card.get(Rank.SEVEN, Suit.CLUBS);
		Card c2 = Card.get(Rank.SEVEN, Suit.DIAMONDS);
		Card c3 = Card.get(Rank.SEVEN, Suit.SPADES);
		Card c4 = Card.get(Rank.TWO, Suit.HEARTS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		
		assertEquals(12,h.score(c5));
	}
	
	@Test
	void testScoreRunThree() {
		Hand h = new Hand();
		
		Card c5 = Card.get(Rank.SEVEN, Suit.CLUBS);
		
		Card c1 = Card.get(Rank.EIGHT, Suit.CLUBS);
		Card c2 = Card.get(Rank.NINE, Suit.DIAMONDS);
		Card c3 = Card.get(Rank.THREE, Suit.SPADES);
		Card c4 = Card.get(Rank.TWO, Suit.HEARTS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		
		assertEquals(5,h.score(c5));
	}
	
	@Test
	void testScoreRunFour() {
		Hand h = new Hand();
		
		Card c5 = Card.get(Rank.SEVEN, Suit.CLUBS);
		
		Card c1 = Card.get(Rank.EIGHT, Suit.CLUBS);
		Card c2 = Card.get(Rank.NINE, Suit.DIAMONDS);
		Card c3 = Card.get(Rank.TEN, Suit.SPADES);
		Card c4 = Card.get(Rank.TWO, Suit.HEARTS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		
		assertEquals(6,h.score(c5));
	}
	
	@Test
	void testScoreRunFive() {
		Hand h = new Hand();
		
		Card c5 = Card.get(Rank.SEVEN, Suit.CLUBS);
		
		Card c1 = Card.get(Rank.EIGHT, Suit.CLUBS);
		Card c2 = Card.get(Rank.NINE, Suit.DIAMONDS);
		Card c3 = Card.get(Rank.TEN, Suit.SPADES);
		Card c4 = Card.get(Rank.SIX, Suit.HEARTS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		
		assertEquals(9,h.score(c5));
	}
	
	@Test
	void testScoreFlush() {
		Hand h = new Hand();
		
		Card c5 = Card.get(Rank.SIX, Suit.CLUBS);
		
		Card c1 = Card.get(Rank.EIGHT, Suit.CLUBS);
		Card c2 = Card.get(Rank.NINE, Suit.CLUBS);
		Card c3 = Card.get(Rank.TEN, Suit.CLUBS);
		Card c4 = Card.get(Rank.SIX, Suit.CLUBS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		
		assertEquals(14,h.score(c5));
	}
	
	@Test
	void testScoreFlushNoStarter() {
		Hand h = new Hand();
		
		Card c5 = Card.get(Rank.SIX, Suit.DIAMONDS);
		
		Card c1 = Card.get(Rank.EIGHT, Suit.CLUBS);
		Card c2 = Card.get(Rank.NINE, Suit.CLUBS);
		Card c3 = Card.get(Rank.TEN, Suit.CLUBS);
		Card c4 = Card.get(Rank.SIX, Suit.CLUBS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		
		assertEquals(13,h.score(c5));
	}
	
	@Test
	void testScoreNobs() {
		Hand h = new Hand();
		
		Card c5 = Card.get(Rank.SIX, Suit.CLUBS);
		
		Card c1 = Card.get(Rank.EIGHT, Suit.CLUBS);
		Card c2 = Card.get(Rank.JACK, Suit.CLUBS);
		Card c3 = Card.get(Rank.TEN, Suit.CLUBS);
		Card c4 = Card.get(Rank.SIX, Suit.CLUBS);
		
		h.addCard(c1);
		h.addCard(c2);
		h.addCard(c3);
		h.addCard(c4);
		
		assertEquals(8,h.score(c5));
	}
}
