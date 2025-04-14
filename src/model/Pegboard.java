package model;

public class Pegboard {
    private Player playerOne;
    private Player playerTwo;
    private int peg1;
    private int peg2;
    public static final int PEG_TOTAL = 121;

    // class constructor
    public Pegboard(Player p1, Player p2) {
        playerOne = p1;
        playerTwo = p2;
        peg1 = 0;
        peg2 = 0;
    }

    // increments a peg variable (add exception thrown if player doesn't equal the instance variables)
    public void movePeg(Player player, int points) {
        if (playerOne.equals(player)) {
            peg1 += points;
        }
        else {
            peg2 += points;
        }
    }

    // checks if a peg has reached the 121th peg hole
    public boolean reachedPegboardEnd() {
        return peg1 == PEG_TOTAL || peg2 == PEG_TOTAL;
    }
}
