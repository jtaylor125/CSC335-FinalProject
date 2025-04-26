/* Authors: Andy Zhang, Nathan Crossman, Jacob Taylor, Tristan Emma
 * Course: CSC 335
 * Description: This class represents a computer, it plays against a human opponent
 * and determines what cards it plays based on the Strategy it uses
 */
package model;

import java.util.List;

public class Computer {

    // instance variables
    private Player player;
    private Strategy strategy;

    // Constructor
    /* This constructor initializes a Computer player with a specific Strategy difficulty
     * Arguments:
     *      player: the Player object this computer controls
     *      difficulty: the Strategy implementation representing the AI behavior
     */
    public Computer(Player player, Strategy difficulty) {
        this.player = player;
        this.strategy = difficulty;
    }

    /* This method returns the Player object controlled by this Computer
     * Returns:
     *      the Player object
     */
    public Player getPlayer() {
        return player;
    }

    /* This method uses the Strategy to choose two cards to discard to the crib
     * Returns:
     *      a List containing two Card objects selected for discarding
     */
    public List<Card> chooseDiscarded() {
        return strategy.chooseDiscards(player.getHand().getHand());
    }

    /* This method uses the Strategy to choose a card to play during pegging play
     * Arguments:
     *      runningTotal: the current total of points in the pegging phase
     *      played: a list of Cards that have already been played
     * Returns:
     *      a Card object chosen to be played next
     */
    public Card choosePlay(int runningTotal, List<Card> played) {
        List<Card> playable = player.getHand().getPlayableCards(runningTotal);
        return strategy.choosePeggingPlayCard(playable, runningTotal, played);
    }

} // end class
