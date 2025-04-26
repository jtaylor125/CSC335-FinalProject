/* Authors: Andy Zhang, Nathan Crossman, Jacob Taylor, Tristan Emma
 * Course: CSC 335
 * Description: An instance of this class represents a Pegboard inside
 * of a Cribbage game, it tracks the scores of two players using a smaller
 * class, PegPair. The PegPair class simulates two pegs inside of a Pegboard.
 * The difference between two Pegs is meant to track the amount previously 
 * scored.
 */
package model;

import java.util.HashMap;

public class Pegboard {
    private static final int WINNING_SCORE = 121;
    
    // defined inside of pegboard for encapsulation, as it is only used in pegboard
    private static class PegPair {
        int[] pegs = new int[2];
        int activePeg = 0;

        void addPoints(int points) {
        	if (points == 0) return;
            int moving = activePeg;
            int stationary;

            if (activePeg == 0) {
                stationary = 1;
            }
            else {
                stationary = 0;
            }

            int base = pegs[stationary];
            int newPosition = base + points;

            if (pegs[moving] == pegs[stationary] && points == 0) {
                newPosition += 1;
            }

            pegs[moving] = newPosition;

            if (pegs[0] > pegs[1]) {
                activePeg = 1;
            }
            else {
                activePeg = 0;
            }
        }

        void reset() {
            pegs[0] = 0;
            pegs[1] = 0;
            activePeg = 0;
        }

        int current() {
            if (pegs[0] > pegs[1]) {
                return pegs[0];
            }
            else {
                return pegs[1];
            }
        }

        int previous() {
            if (pegs[0] < pegs[1]) {
                return pegs[0];
            }
            else {
                return pegs[1];
            }
        }

        boolean hasWon() {
            if (current() >= WINNING_SCORE) {
                return true;
            }
            else {
                return false;
            }
        }
    }

    private final HashMap<Player, PegPair> playerPegs;

    public Pegboard() {
        playerPegs = new HashMap<>();
    }

    private void ensurePlayer(Player player) {
        if (!playerPegs.containsKey(player)) {
            playerPegs.put(player, new PegPair());
        }
    }

    public void addPoints(Player player, int points) {
        ensurePlayer(player);
        PegPair pegs = playerPegs.get(player);
        pegs.addPoints(points);
        player.addScore(points);
    }

    public int getFrontPeg(Player player) {
        ensurePlayer(player);
        PegPair pegs = playerPegs.get(player);
        return pegs.current();
    }

    public int getBackPeg(Player player) {
        ensurePlayer(player);
        PegPair pegs = playerPegs.get(player);
        return pegs.previous();
    }

    public boolean hasWon(Player player) {
        ensurePlayer(player);
        PegPair pegs = playerPegs.get(player);
        return pegs.hasWon();
    }

    public int getScore(Player player) {
        return getFrontPeg(player);
    }

    public void resetScores() {
        for (PegPair pegs : playerPegs.values()) {
            pegs.reset();
        }
    }

    public String toString() {
        String result = "Current Pegboard:\n";

        for (Player player : playerPegs.keySet()) {
            String role = "Player";

            if (player.isDealer()) {
                role = "Dealer";
            }
            PegPair pegs = playerPegs.get(player);
            result += role + " Pegs: Peg 1: " + pegs.pegs[0] + ", Peg 2: " + pegs.pegs[1] + "\n";
        }

        return result;
    }
}
