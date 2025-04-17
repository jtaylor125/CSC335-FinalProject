package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class DeckTest {

	@Test
	void testDeck() {
		Deck d = new Deck();
		assertEquals(d.size(), 52);
	}
	
	@Test
	void testDrawTop() {
		Deck d = new Deck();
		Card c = d.drawTop();
		assertNotEquals(c,null);
		assertEquals(d.size(), 51);
	}
	
	@Test
	void testDrawRand() {
		Deck d = new Deck();
		Card c = d.drawRandom();
		assertNotEquals(c,null);
		assertEquals(d.size(), 51);
	}
	
	@Test
	void testNewDeck() {
		Deck d = new Deck();
		Card c = d.drawTop();
		assertEquals(d.size(), 51);
		d.buildNewDeck();
		assertEquals(d.size(), 52);
	}
}
