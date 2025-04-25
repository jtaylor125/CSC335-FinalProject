package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Card;
import model.Player;
import model.Rank;
import model.Suit;

public class PlayerTest {
	@Test
	void testPlayer() {
		Player p = new Player();
		
		assertFalse(p.isDealer());
		assertEquals(0,p.getScore());
		assertTrue(p.isHandEmpty());
	}
	
	@Test
	void testAdd() {
		Player p = new Player();
		
		Card c = Card.get(Rank.FOUR, Suit.SPADES);
		
		p.addToHand(c);
		
		assertFalse(p.isHandEmpty());
	}
	
	@Test
	void testHand() {
		Player p = new Player();
		
		Card c1 = Card.get(Rank.FOUR, Suit.SPADES);
		Card c2 = Card.get(Rank.JACK, Suit.HEARTS);
		Card c3 = Card.get(Rank.TEN, Suit.CLUBS);
		Card c4 = Card.get(Rank.THREE, Suit.DIAMONDS);
		Card c5 = Card.get(Rank.QUEEN, Suit.DIAMONDS);
		Card c6 = Card.get(Rank.KING, Suit.DIAMONDS);
		Card c7 = Card.get(Rank.SIX, Suit.DIAMONDS);
		
		p.addToHand(c1);
		p.addToHand(c2);
		p.addToHand(c3);
		p.addToHand(c4);
		p.addToHand(c5);
		p.addToHand(c6);
		
		p.discard(c6);
		p.discard(c1);
		
		int score = p.scoreHand(c7);
		
		assertEquals(3,score);
	}
	
	@Test
	void testDealer() {
		Player p = new Player();
		
		p.setDealer(true);
		assertTrue(p.isDealer());
	}
	
	@Test 
	void testAddScore(){
		Player p = new Player();
		
		Card c1 = Card.get(Rank.FOUR, Suit.SPADES);
		Card c2 = Card.get(Rank.JACK, Suit.HEARTS);
		Card c3 = Card.get(Rank.TEN, Suit.CLUBS);
		Card c4 = Card.get(Rank.THREE, Suit.DIAMONDS);
		Card c5 = Card.get(Rank.QUEEN, Suit.DIAMONDS);
		Card c6 = Card.get(Rank.KING, Suit.DIAMONDS);
		Card c7 = Card.get(Rank.SIX, Suit.DIAMONDS);
		
		p.addToHand(c1);
		p.addToHand(c2);
		p.addToHand(c3);
		p.addToHand(c4);
		p.addToHand(c5);
		p.addToHand(c6);
		
		p.discard(c6);
		p.discard(c1);
		
		int score = p.scoreHand(c7);
		
		p.addScore(score);
		
		assertEquals(3,p.getScore());
	}
}
