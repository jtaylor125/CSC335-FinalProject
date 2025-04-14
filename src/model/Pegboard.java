package model;

public class Pegboard {
    private Player playerOne;
    private Player playerTwo;
    private int peg1;
    private int peg2;
    public static final int PEG_TOTAL = 121;

    public Pegboard(Player p1, Player p2) {
        playerOne = p1;
        playerTwo = p2;
        peg1 = 0;
        peg2 = 0;
    }

    public void movePeg(Player player, int points) {
        if (playerOne.equals(player)) {
            peg1 += points;
        }
        else {
            peg2 += points;
        }
    }

    public boolean reachedPegboardEnd() {
        return peg1 == PEG_TOTAL || peg2 == PEG_TOTAL;
    }
}
