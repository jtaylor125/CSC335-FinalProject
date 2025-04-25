/* Authors: Andy Zhang, Nathan Crossman, Jacob, Talyor
 * Course: CSC 335
 * Description: An instance of this class represents a standard Deck of
 * 52 Cards. It is composed of 52 or less Card objects at any given moment
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck {

    // instance variables
    private final CardStack deck = new CardStack();

    // Constructor
    /* This constructor creates a new Deck object and initializes it
     * by building and shuffling a standard 52-card deck
     */
    public Deck() {
        buildNewDeck();
        shuffle();
    }

    /* This method rebuilds the deck to a full set of 52 cards
     * by clearing the stack and pushing one card for each combination of Rank and Suit
     */
    public void buildNewDeck() {
        while (!deck.isEmpty()) {
            deck.pop();
        }

        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                deck.push(Card.get(r, s));
            }
        }
    }

    /* This method shuffles the deck by removing all cards into a list,
     * randomizing the order, and then re-pushing them back into the stack
     */
    public void shuffle() {
        ArrayList<Card> tempList = new ArrayList<>();

        while (!deck.isEmpty()) {
            tempList.add(deck.pop());
        }

        Collections.shuffle(tempList);

        for (Card c : tempList) {
            deck.push(c);
        }
    }

    /* This method draws a random card from somewhere in the middle of the deck
     * Returns:
     *      a Card object selected pseudo-randomly from the deck
     * Preconditions:
     *      the deck must not be empty
     */
    public Card drawRandom() {
        assert !deck.isEmpty();
        Random rand = new Random();
        int cutIndex = rand.nextInt((deck.size() / 2) + 1) + (deck.size() / 4);
        ArrayList<Card> tempList = new ArrayList<>();

        for (int i = 0; i < cutIndex; i++) {
            tempList.add(deck.pop());
        }

        Card drawn = deck.pop();

        for (Card c : tempList) {
            deck.push(c);
        }

        return drawn;
    }

    /* This method draws the top card from the deck
     * Returns:
     *      a Card object from the top of the stack
     */
    public Card drawTop() {
        return deck.pop();
    }

    /* This method returns the number of cards currently in the deck
     * Returns:
     *      an int representing the current size of the deck
     */
    public int size() {
        return deck.size();
    }

} // end class
