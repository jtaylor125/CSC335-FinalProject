package model;

import java.util.HashMap;

public class Pegboard {
    // class variable 
    private static final int WINNING_SCORE = 121;
    
    // defined inside of pegboard for encapsulation, as it is only used in pegboard
    private static class PegPair {
        int[] pegs = new int[2];
        int activePeg = 0;

        /* This method adds points to pegs
         * Arguments:
         *      points: int
         */
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

        /* This method resets the two pegs points including the activePeg
         */
        void reset() {
            pegs[0] = 0;
            pegs[1] = 0;
            activePeg = 0;
        }

        /* This method returns the current peg's points
         * returns:
         *      an int of the current peg
         */
        int current() {
            if (pegs[0] > pegs[1]) {
                return pegs[0];
            }
            else {
                return pegs[1];
            }
        }

        /* This method returns the previous peg's points
         * Returns:
         *      an int of the previous peg
         */
        int previous() {
            if (pegs[0] < pegs[1]) {
                return pegs[0];
            }
            else {
                return pegs[1];
            }
        }

        /* This method checks if a peg has reached the winning score
         * Returns:
         *      true if a peg has reached the winning score otherwise false
         */
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

    // contructor
    public Pegboard() {
        playerPegs = new HashMap<>();
    }

    /* This method checks and stores the passed argument player object in the playerPegs hashmap
     * Arguments:
     *      player: a Player object 
     */
    private void ensurePlayer(Player player) {
        if (!playerPegs.containsKey(player)) {
            playerPegs.put(player, new PegPair());
        }
    }

    /* This method adds points to the PegPair object in playerPegs based on the player
     * Arguments:
     *      player: Player object
     *      points: int of score
     */
    public void addPoints(Player player, int points) {
        ensurePlayer(player);
        PegPair pegs = playerPegs.get(player);
        pegs.addPoints(points);
        player.addScore(points);
    }

    /* This method gets the current peg from playerPegs
     * Arguments:
     *      player: Player object
     * Returns:
     *      the current peg score
     */
    public int getFrontPeg(Player player) {
        ensurePlayer(player);
        PegPair pegs = playerPegs.get(player);
        return pegs.current();
    }

    /* This method returns the previous peg from playerPegs
     * Arguments:
     *      player: Player object
     * Returns:
     *      the previous peg score
     */
    public int getBackPeg(Player player) {
        ensurePlayer(player);
        PegPair pegs = playerPegs.get(player);
        return pegs.previous();
    }

    /* This method checks if a player has won from playerPegs
     * Arguments:
     *      player: Player object
     * Returns:
     *      true if a player has won otherwise false
     */
    public boolean hasWon(Player player) {
        ensurePlayer(player);
        PegPair pegs = playerPegs.get(player);
        return pegs.hasWon();
    }

    /* This method gets the front peg score
     * Arguments:
     *      player: Player object
     * Returns:
     *      int score of the front peg
     */
    public int getScore(Player player) {
        return getFrontPeg(player);
    }

    /* This method resets the scores for each PegPair pegs
     */
    public void resetScores() {
        for (PegPair pegs : playerPegs.values()) {
            pegs.reset();
        }
    }

    /* This method creates a string representation of the Pegboard object and loops
     * through playerPegs to get each PegPair for each player
     * Returns:
     *      String object of the Pegboard object
     */
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