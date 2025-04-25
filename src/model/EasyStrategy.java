/* Authors: Andy Zhang, Nathan Crossman, Jacob, Talyor
 * Course: CSC 335
 * Description: This class is a Strategy that a computer can use, it selects
 * cards at random. Implements the Strategy Design pattern.
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EasyStrategy implements Strategy {

    // instance variables
    private Random rand = new Random();

    // Methods required by the Strategy interface

    /* This method selects two random cards from the player's hand to discard
     * Arguments:
     *      hand: a List representing the player's current hand
     * Returns:
     *      a List containing two randomly chosen cards to discard
     */
    @Override
    public List<Card> chooseDiscards(List<Card> hand) {
        ArrayList<Card> copy = new ArrayList<>(hand);
        Collections.shuffle(copy);
        return copy.subList(0, 2);
    }

    /* This method selects a random playable card during pegging
     * Arguments:
     *      playableCards: a List of cards that can legally be played
     *      runningTotal: the current running total of pegging values
     *      playedCards: a List of cards that have already been played
     * Returns:
     *      a Card randomly selected from the list of playable cards
     */
    @Override
    public Card choosePeggingPlayCard(List<Card> playableCards, int runningTotal, List<Card> playedCards) {
        return playableCards.get(rand.nextInt(playableCards.size()));
    }

} // end class
