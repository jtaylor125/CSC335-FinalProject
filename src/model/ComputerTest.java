package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Card;
import model.Computer;
import model.HardStrategy;
import model.Player;
import model.Strategy;

public class ComputerTest {


    private Player player;
    private Strategy strategy;
    private Computer computer;

    @BeforeEach
    void setUp() {
        player = new Player();
        strategy = new HardStrategy();
        computer = new Computer(player, strategy);
    }

    @Test
    void testGetPlayer() {
        assertEquals(player, computer.getPlayer(), "Computer should return the correct Player object");
    }

    @Test
    void testChooseDiscarded() {
        player.addToHand(Card.get("TWO", "HEARTS"));
        player.addToHand(Card.get("FIVE", "SPADES"));
        player.addToHand(Card.get("KING", "CLUBS"));
        player.addToHand(Card.get("JACK", "DIAMONDS"));
        player.addToHand(Card.get("NINE", "HEARTS"));
        player.addToHand(Card.get("ACE", "SPADES"));
        List<Card> discards = computer.chooseDiscarded();
        assertEquals(2, discards.size());
        assertTrue(
            (discards.contains(Card.get("KING", "CLUBS")) && discards.contains(Card.get("JACK", "DIAMONDS"))) ||
            (discards.contains(Card.get("JACK", "DIAMONDS")) && discards.contains(Card.get("KING", "CLUBS"))));
    }

    @Test
    void testChoosePlay() {
        player.addToHand(Card.get("TWO", "HEARTS"));
        player.addToHand(Card.get("FIVE", "SPADES"));
        player.addToHand(Card.get("KING", "CLUBS"));

        List<Card> playedCards = new ArrayList<>();
        int runningTotal = 20;
        Card selectedCard = computer.choosePlay(runningTotal, playedCards);
        assertNotNull(selectedCard);
        assertTrue(selectedCard.getValue() + runningTotal <= 31);
    }
}
