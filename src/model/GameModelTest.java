package Tests;
import org.junit.jupiter.api.BeforeEach;
import java.io.ByteArrayInputStream;

import org.junit.jupiter.api.Test;

import model.Computer;
import model.GameModel;
import model.HardStrategy;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

public class GameModelTest {

    private GameModel game;

    @BeforeEach
    void setUp() {
        game = new GameModel();
        game.determineDealer();
        game.deal();
    }

    @Test
    void testConstructor() {
        assertNotNull(game);
    }

    @Test
    void testDetermineDealer() {
        String dealer = game.getDealer();
        assertTrue(dealer.equals("Player 1") || dealer.equals("Player 2"));
    }

    @Test
    void testDeal() {
        assertEquals(6, game.getHand("Player 1").split(", ").length);
        assertEquals(6, game.getHand("Player 2").split(", ").length);
    }

    @Test
    void testDiscard() {
        String card = game.getHand("Player 1").split(", ")[0];
        game.discard("Player 1", card);
        assertEquals(5, game.getHand("Player 1").split(", ").length);
    }

    @Test
    void testRegularPlay() {
        discardTwoEach();
        Scanner input = fakeInput("0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0"+
        "0\n0\n0\n0\n0\n0\n0\\n0\n0\n0\n0\n0\n0\n0\n0");
        game.peggingPlay(input);
        game.regularPlay();
        game.reset();
        // play 10 games
        for (int i=0; i < 10; i++) {
            input = fakeInput("0\nasd\n0\n10\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0"+
                    "0\n0\n0\n0\n0\n0\n0\\n0\n0\n0\n0\n0\n0\n0\n0");
        	game.deal();
        	discardTwoEach();
            game.peggingPlay(input);
            game.regularPlay();
            game.reset();
        }
        // make sure a player has scored atleast 1 point after 11 random games
        assertNotEquals(game.getScore("Player 1") + game.getScore("Player 2"), 0);
    }

    @Test
    void testPeggingPlay() {
        discardTwoEach();
        Scanner input = fakeInput("0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0"+
        "0\n0\n0\n0\n0\n0\n0\\n0\n0\n0\n0\n0\n0\n0\n0");
        game.peggingPlay(input);
        game.regularPlay();
        game.reset();
        // play 11 rounds
        for (int i=0; i < 10; i++) {
            input = fakeInput("0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0"+
                    "0\n0\n0\n0\n0\n0\n0\\n0\n0\n0\n0\n0\n0\n0\n0");
        	game.deal();
        	discardTwoEach();
            game.peggingPlay(input);
            game.regularPlay();
            game.reset();
        }
        // make sure a player has scored atleast 1 point after 11 random games
        assertNotEquals(game.getScore("Player 1") + game.getScore("Player 2"), 0);
        // play 30 games to test the private win check method
        for (int i=0; i < 30; i++) {
	        while (true) {
	            input = fakeInput("0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0"+
	                    "0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0");
	        	game.deal();
	        	discardTwoEach();
	            if (game.peggingPlay(input)) break;
	            if (game.regularPlay()) break;
	            game.reset();
	        }
        }
    }

    @Test
    void testOnePlayerPeggingPlay() {
        discardTwoEach();
        Computer computer = new Computer(game.getPlayerTwo(), new HardStrategy());
        Scanner input = fakeInput("0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0"+
                "0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0");
        // play 30 games with the computer to make sure
        for (int i=0; i < 30; i++) {
	        while (true) {
	            input = fakeInput("0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0"+
	                    "0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0\n0");
	        	game.deal();
	            game.discard("Player 1", game.getHand("Player 1").split(", ")[0]);
	            game.discard("Player 1", game.getHand("Player 1").split(", ")[0]);
	            if (game.onePlayerPeggingPlay(input, computer)) break;
	            if (game.regularPlay()) break;
	            game.reset();
	        }
        }
    }

    @Test
    void testReset() {
        game.reset();
        assertEquals(0, game.getHand("Player 1").length());
        assertEquals(0, game.getHand("Player 2").length());
    }

    @Test
    void testCheckWin() {
        assertNull(game.checkWin());
    }

    @Test
    void testGetDealer() {
        String dealer = game.getDealer();
        assertTrue(dealer.equals("Player 1") || dealer.equals("Player 2"));
    }

    @Test
    void testGetHand() {
        assertFalse(game.getHand("Player 1").isEmpty());
        assertFalse(game.getHand("Player 2").isEmpty());
    }

    @Test
    void testGetCrib() {
        discardTwoEach();
        assertFalse(game.getCrib().isEmpty());
    }

    @Test
    void testGetScore() {
        assertEquals(0, game.getScore("Player 1"));
        assertEquals(0, game.getScore("Player 2"));
    }

    @Test
    void testGetPegboard() {
        assertNotNull(game.getPegboard());
    }

    @Test
    void testGetPlayerTwo() {
        assertNotNull(game.getPlayerTwo());
    }

    private void discardTwoEach() {
        game.discard("Player 1", game.getHand("Player 1").split(", ")[0]);
        game.discard("Player 1", game.getHand("Player 1").split(", ")[0]);
        game.discard("Player 2", game.getHand("Player 2").split(", ")[0]);
        game.discard("Player 2", game.getHand("Player 2").split(", ")[0]);
    }

    private Scanner fakeInput(String inputs) {
        return new Scanner(new ByteArrayInputStream(inputs.getBytes()));
    }
    
}
