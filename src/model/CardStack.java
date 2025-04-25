/* Authors: Andy Zhang, Nathan Crossman, Jacob Taylor, Tristan Emma
 * Course: CSC 335
 * Description: An instance of this class represents a Card Stack, a CardStack is composed
 * of any number of Card objects
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CardStack implements Iterable<Card> {

	// instance variables
	private final List<Card> cards = new ArrayList<Card>();

	/* This method adds a Card to the top of the stack
	 * Arguments:
	 * 		c: the Card object to add
	 * Preconditions:
	 * 		c must not be null and must not already exist in the stack
	 */
	public void push(Card c) {
		assert c != null && !cards.contains(c);
		cards.add(c);
	}

	/* This method removes and returns the Card at the top of the stack
	 * Returns:
	 * 		the Card object that was removed
	 * Preconditions:
	 * 		the stack must not be empty
	 */
	public Card pop() {
		assert !isEmpty();
		return cards.remove(cards.size() - 1);
	}

	/* This method returns the Card at the top of the stack without removing it
	 * Returns:
	 * 		the Card object at the top of the stack
	 * Preconditions:
	 * 		the stack must not be empty
	 */
	public Card peek() {
		assert !isEmpty();
		return cards.get(cards.size() - 1);
	}

	/* This method removes all Cards from the stack
	 */
	public void clear() {
		cards.clear();
	}

	/* This method checks if the stack is empty
	 * Returns:
	 * 		true if the stack has no cards, false otherwise
	 */
	public boolean isEmpty() {
		return cards.size() == 0;
	}

	/* This method returns the number of Cards currently in the stack
	 * Returns:
	 * 		an int representing the number of cards in the stack
	 */
	public int size() {
		return cards.size();
	}

	/* This method returns an iterator for the stack
	 * Returns:
	 * 		an Iterator<Card> over the cards in the stack
	 */
	public Iterator<Card> iterator() {
		return cards.iterator();
	}
	
} // end class
