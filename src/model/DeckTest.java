package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Card;
import model.Deck;

public class DeckTest {

    private Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @Test
    void testDeckConstructor() {
        assertEquals(52, deck.size());
    }

    @Test
    void testBuildNewDeck() {
        for (int i = 0; i < 10; i++) {
            deck.drawTop();
        }
        assertEquals(42, deck.size());

        deck.buildNewDeck();

        assertEquals(52, deck.size());
    }

    @Test
    void testShuffle() {
        Deck newDeck = new Deck();
        newDeck.drawTop();
        newDeck.buildNewDeck();
        newDeck.shuffle();
        Card topCard = newDeck.drawTop();
        assertNotNull(topCard);
    }

    @Test
    void testDrawRandom() {
        int originalSize = deck.size();
        Card randomCard = deck.drawRandom();
        assertNotNull(randomCard);
        assertEquals(originalSize-1, deck.size());
    }

    @Test
    void testDrawTop() {
        int originalSize = deck.size();
        Card topCard = deck.drawTop();
        assertNotNull(topCard);
        assertEquals(originalSize-1, deck.size());
    }

    @Test
    void testSize() {
        assertEquals(52, deck.size());
        deck.drawTop();
        deck.drawTop();
        deck.drawTop();
        assertEquals(49, deck.size());
    }
}
