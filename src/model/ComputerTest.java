package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class ComputerTest {
	@Test
	void testComputer() {
		Player p = new Player();
		EasyStrategy easy = new EasyStrategy();
		Computer c = new Computer(p,easy);
		
		assertEquals(c.getPlayer(),p);
	}
	
	@Test
	void testDiscarded() {
		Player p = new Player();
		EasyStrategy easy = new EasyStrategy();
		Computer c = new Computer(p,easy);
		
		Card c1 = Card.get("ACE", "SPADES");
		Card c2 = Card.get("TWO", "HEARTS");
		Card c3 = Card.get("THREE", "DIAMONDS");
		Card c4 = Card.get("FOUR", "CLUBS");
		Card c5 = Card.get("FIVE", "SPADES");
		Card c6 = Card.get("SIX", "SPADES");
		
		p.addToHand(c1);
		p.addToHand(c2);
		p.addToHand(c3);
		p.addToHand(c4);
		p.addToHand(c5);
		p.addToHand(c6);

		
		List<Card> discards = c.chooseDiscarded();
		assertTrue(p.getHand().getHand().contains(discards.get(0)));
		assertTrue(p.getHand().getHand().contains(discards.get(1)));
	}
	
	@Test
	void testPeg() {
		Player p = new Player();
		EasyStrategy easy = new EasyStrategy();
		Computer c = new Computer(p,easy);
		
		
		Card c1 = Card.get("ACE", "SPADES");
		Card c2 = Card.get("TWO", "HEARTS");
		Card c3 = Card.get("THREE", "DIAMONDS");
		Card c4 = Card.get("FOUR", "CLUBS");

		
		p.addToHand(c1);
		p.addToHand(c2);
		p.addToHand(c3);
		p.addToHand(c4);
		
		Hand hand2 = new Hand();
		
		Card played = c.choosePlay(0,hand2.getHand());
		assertTrue(p.getHand().getHand().contains(played));
	}
}
