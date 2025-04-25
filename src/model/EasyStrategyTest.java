package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import model.Card;
import model.EasyStrategy;
import model.Hand;

public class EasyStrategyTest {
	@Test
	void testEasyStrat() {
		EasyStrategy easy = new EasyStrategy();
		Card c1 = Card.get("ACE", "SPADES");
		Card c2 = Card.get("TWO", "HEARTS");
		Card c3 = Card.get("THREE", "DIAMONDS");
		Card c4 = Card.get("FOUR", "CLUBS");
		Card c5 = Card.get("FIVE", "SPADES");
		Card c6 = Card.get("SIX", "SPADES");
		
		Hand hand = new Hand();
		hand.addCard(c1);
		hand.addCard(c2);
		hand.addCard(c3);
		hand.addCard(c4);
		hand.addCard(c5);
		hand.addCard(c6);
		
		List<Card> discards = easy.chooseDiscards(hand.getHand());
		assertTrue(hand.getHand().contains(discards.get(0)));
		assertTrue(hand.getHand().contains(discards.get(1)));
	}
	
	@Test
	void testPeg() {
		EasyStrategy easy = new EasyStrategy();
		Card c1 = Card.get("ACE", "SPADES");
		Card c2 = Card.get("TWO", "HEARTS");
		Card c3 = Card.get("THREE", "DIAMONDS");
		Card c4 = Card.get("FOUR", "CLUBS");

		
		Hand hand = new Hand();
		hand.addCard(c1);
		hand.addCard(c2);
		hand.addCard(c3);
		hand.addCard(c4);
		
		Hand hand2 = new Hand();
		
		Card played = easy.choosePeggingPlayCard(hand.getPlayableCards(0), 0, hand2.getHand());
		assertTrue(hand.getHand().contains(played));
	}
	
	
}
