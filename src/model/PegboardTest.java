package model;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
public class PegboardTest {
    private static Pegboard board;
    private static Player player;
    
    @BeforeEach
    public void setUp() {
        board = new Pegboard();
        player = new Player();
    }

    @Test
    public void testAddPoints() {
        board.addPoints(player, 20);
        assertEquals(20, board.getScore(player));
    }

    @Test
    public void testGetFrontPeg() {
        board.addPoints(player, 10);
        int front = board.getFrontPeg(player);
        assertEquals(10, front);
    }

    @Test
    public void testGetBackPeg() {
        board.addPoints(player, 5);
        board.addPoints(player, 7);
        int back = board.getBackPeg(player);
        assertEquals(5, back);
    }

    @Test
    public void testHasWon() {
        board.addPoints(player, 121);
        assertTrue(board.hasWon(player));
    }

    @Test
    public void testResetScores() {
        board.addPoints(player, 80);
        board.resetScores();
        assertEquals(0, board.getScore(player));
    }

    @Test
    public void testGetScore() {
        board.addPoints(player, 33);
        int score = board.getScore(player);
        assertEquals(33, score);
    }
    
    @Test
    public void testScoring() {
        board.addPoints(player, 2);
        int firstScore = board.getScore(player);
        assertEquals(2, firstScore);

        board.addPoints(player, 3);
        int secondScore = board.getScore(player);
        assertEquals(5, secondScore);

        // check front and back peg positions
        int frontPeg = board.getFrontPeg(player);
        int backPeg = board.getBackPeg(player);
        assertEquals(5, frontPeg);
        assertEquals(2, backPeg);
    }
    @Test
    public void testToString() {
        board.addPoints(player, 2);
        board.addPoints(player, 3);
        assertEquals(board.toString(), "Current Pegboard:\n"
        		+ "Player Pegs: Peg 1: 2, Peg 2: 5\n");
    }
}
