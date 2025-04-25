package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

import model.Card;
import model.CardStack;
import model.Rank;
import model.Suit;

public class CardStackTest {
	
	@Test
	void testCardStack() {
		CardStack stack = new CardStack(); 
		assertEquals(stack.size(),0);
		assertTrue(stack.isEmpty());
	}
	
	@Test
	void testPushPeek() {
		CardStack stack = new CardStack();
		Card c1 = Card.get(Rank.FOUR, Suit.SPADES);
		stack.push(c1);
		assertEquals(stack.peek(),c1);
		assertFalse(stack.isEmpty());
		assertEquals(stack.size(),1);
	}
	
	@Test
	void testPop() {
		CardStack stack = new CardStack();
		Card c1 = Card.get(Rank.FOUR, Suit.SPADES);
		stack.push(c1);
		assertEquals(stack.peek(),c1);
		assertFalse(stack.isEmpty());
		assertEquals(stack.size(),1);
		
		Card c2 = stack.pop();
		assertEquals(c2,c1);
		assertTrue(stack.isEmpty());
		assertEquals(stack.size(),0);
	}
	
	@Test
	void testMultipleCards() {
		CardStack stack = new CardStack();
		Card c1 = Card.get(Rank.FOUR, Suit.SPADES);
		Card c2 = Card.get(Rank.JACK, Suit.HEARTS);
		Card c3 = Card.get(Rank.TEN, Suit.CLUBS);
		Card c4 = Card.get(Rank.THREE, Suit.DIAMONDS);
		stack.push(c1);
		stack.push(c2);
		stack.push(c3);
		stack.push(c4);
		
		assertEquals(stack.peek(),c4);
		assertFalse(stack.isEmpty());
		assertEquals(stack.size(),4);
		
		assertEquals(c4,stack.pop());
		
		assertEquals(stack.peek(),c3);
		assertFalse(stack.isEmpty());
		assertEquals(stack.size(),3);
		
		Card c5 = Card.get(Rank.QUEEN, Suit.SPADES);
		stack.push(c5);
		assertEquals(stack.peek(),c5);
		assertFalse(stack.isEmpty());
		assertEquals(stack.size(),4);
	}
	
	@Test
	void testClear() {
		CardStack stack = new CardStack();
		Card c1 = Card.get(Rank.FOUR, Suit.SPADES);
		Card c2 = Card.get(Rank.JACK, Suit.HEARTS);
		Card c3 = Card.get(Rank.TEN, Suit.CLUBS);
		Card c4 = Card.get(Rank.THREE, Suit.DIAMONDS);
		stack.push(c1);
		stack.push(c2);
		stack.push(c3);
		stack.push(c4);
		
		Card c5 = Card.get(Rank.QUEEN, Suit.SPADES);
		stack.push(c5);
		
		assertFalse(stack.isEmpty());
		assertEquals(stack.size(),5);
		
		stack.clear();
		
		assertTrue(stack.isEmpty());
		assertEquals(stack.size(),0);
	}
	
	@Test
	void testIterator() {
		CardStack stack = new CardStack();
		Card c1 = Card.get(Rank.FOUR, Suit.SPADES);
		Card c2 = Card.get(Rank.JACK, Suit.HEARTS);
		Card c3 = Card.get(Rank.TEN, Suit.CLUBS);
		Card c4 = Card.get(Rank.THREE, Suit.DIAMONDS);
		stack.push(c1);
		stack.push(c2);
		stack.push(c3);
		stack.push(c4);
		
		Iterator<Card> iter = stack.iterator();
		
		CardStack stackTwo = new CardStack();
		
		while (iter.hasNext()) {
			Card c = iter.next();
			stackTwo.push(c);
		}
		
		assertEquals(c4,stackTwo.pop());
		assertEquals(c3,stackTwo.pop());
		assertEquals(c2,stackTwo.pop());
		assertEquals(c1,stackTwo.pop());
	}
}
