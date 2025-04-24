package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameModelTest {

    private GameModel game;

    @BeforeEach
    public void setUp() {
        game = new GameModel();
    }

    @Test
    public void testDetermineDealer() {
        game.determineDealer();
        String dealer = game.getDealer();
        assertTrue(dealer.equals("Player 1") || dealer.equals("Player 2"));
    }

    @Test
    public void testDeal() {
        game.determineDealer();
        game.deal();

        int p1Size = game.getHand("Player 1").split(", ").length;
        int p2Size = game.getHand("Player 2").split(", ").length;

        assertEquals(6, p1Size);
        assertEquals(6, p2Size);
    }

    @Test
    public void testDiscard() {
        game.determineDealer();
        game.deal();

        String[] p1Cards = game.getHand("Player 1").split(", ");
        String card = p1Cards[0];

        game.discard("Player 1", card);

        assertTrue(game.getCrib().contains(card));
        assertFalse(game.getHand("Player 1").contains(card));
    }

    @Test
    public void testPeggingPlay() {
        game.determineDealer();
        game.deal();

        // Discard 2 cards each
        String[] p1Cards = game.getHand("Player 1").split(", ");
        String[] p2Cards = game.getHand("Player 2").split(", ");
        game.discard("Player 1", p1Cards[0]);
        game.discard("Player 1", p1Cards[1]);
        game.discard("Player 2", p2Cards[0]);
        game.discard("Player 2", p2Cards[1]);

        int score1 = game.getScore("Player 1");
        int score2 = game.getScore("Player 2");

        assertTrue(score1 > 0 || score2 > 0);
    }

    @Test
    public void testRegularPlay() {
        game.determineDealer();
        game.deal();

        String[] p1Cards = game.getHand("Player 1").split(", ");
        String[] p2Cards = game.getHand("Player 2").split(", ");

        game.discard("Player 1", p1Cards[0]);
        game.discard("Player 1", p1Cards[1]);
        game.discard("Player 2", p2Cards[0]);
        game.discard("Player 2", p2Cards[1]);

        game.regularPlay();

        int score1 = game.getScore("Player 1");
        int score2 = game.getScore("Player 2");
        
        game.reset();
        
        game.deal();
        p1Cards = game.getHand("Player 1").split(", ");
        p2Cards = game.getHand("Player 2").split(", ");

        game.discard("Player 1", p1Cards[0]);
        game.discard("Player 1", p1Cards[1]);
        game.discard("Player 2", p2Cards[0]);
        game.discard("Player 2", p2Cards[1]);

        game.regularPlay();

        score1 = game.getScore("Player 1");
        score2 = game.getScore("Player 2");

        assertTrue(score1 > 0 || score2 > 0);
    }

    @Test
    public void testReset() {
        game.determineDealer();
        String initialDealer = game.getDealer();
        game.deal();
        game.reset();
        String newDealer = game.getDealer();

        assertNotEquals(initialDealer, newDealer);
    }
}