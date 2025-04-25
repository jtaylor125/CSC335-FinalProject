/* Authors: Andy Zhang, Nathan Crossman, Jacob Taylor, Tristan Emma
 * Course: CSC 335
 * Description: This class is a Strategy that a computer can use, with a method
 * to selecting cards that performs better than random. Implements the Strategy 
 * Design pattern.
 */
package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HardStrategy implements Strategy {

    /* This method selects the two highest cards to discard to the crib
     * Arguments:
     *      hand: a List representing the player's current hand
     * Returns:
     *      a List containing two chosen cards based on their value
     */
    @Override
    public List<Card> chooseDiscards(List<Card> hand) {
        // discard the 2 highest cards
        List<Card> copy = new ArrayList<>(hand);
        copy.sort(Comparator.comparingInt(Card::getValue).reversed());
        return copy.subList(0, 2);
    }

    /* This method chooses the lowest value card to play during pegging play 
     * Arguments:
     *      playableCards: a List of cards that can legally be played
     *      runningTotal: the current running total of pegging values
     *      playedCards: a List of cards that have already been played
     * Returns:
     *      a Card with the lowest value selected from the list of playable cards
     */
    @Override
    public Card choosePeggingPlayCard(List<Card> playableCards, int runningTotal, List<Card> playedCards) {
        // prefer lowest card that doesn't exceed 31
        playableCards.sort(Comparator.comparingInt(Card::getValue));
        return playableCards.get(0);
    }
}