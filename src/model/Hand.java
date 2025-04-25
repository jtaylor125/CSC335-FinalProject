package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
	private final List<Card> hand;
	
	// contructor
	public Hand() {
		//Hands should start empty and be added to
		//All hand in the hand need to be accessible
		hand = new ArrayList<>();

	}
	
	/* This method adds a card to the hand variable
	 * Argument:
	 * 		card: a Card object
	 */
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	/* This method removes a card from the hand variable using the Card object argument
	 * Argument:
	 * 		card: a Card object
	 */
	public void removeCard(Card card) {
		this.hand.remove(card);
	}
	
	/* This method checks if the hand variable is empty
	 * Returns:
	 * 		true if hand is empty otherwise false
	 */
	public boolean isEmpty() {
		return hand.isEmpty();
	}

	/* This method gets a copy of the hand variable
	 * Returns:
	 * 		an ArrayList<>(hand)- a copy of hand
	 */
	public List<Card> getHand() {
		// return a copy of the hand, avoidance of problematic escaping references
		return new ArrayList<>(hand);
	}

	/* This method clears the hand variable by calling the class' clear method
	 */
	public void clear() {
		hand.clear();
	}

	/* This method returns the size of the hand variable
	 */
	public int size() {
		return hand.size();
	}

	/* This method scores a hand and the starter card from the argument passed by calling private
	 * methods for various ways of scoring a hand
	 * Arguments:
	 * 		starter: a Card object
	 * Returns:
	 * 		an int score
	 */
	public int score(Card starter) {
		List<Card> fullHand = new ArrayList<>(hand);
		fullHand.add(starter);

		int score = 0;

		score += scoreFifteens(fullHand);
		score += scorePairs(fullHand);
		score += scoreRuns(fullHand);
		score += scoreFlush(starter);
		score += scoreNobs(starter);

		return score;
	}
	
	/* This method scores the hand for fifteen combinations
	 * Arguments:
	 * 		hand: a List<Card> object
	 * Returns:
	 * 		an int of the hand's score
	 */
	private int scoreFifteens(List<Card> hand) {
		int count = 0;
		int n = hand.size();
		// shift left to get 2^n, (1 << n) == 2^n
		for (int i=1; i < (1 << n); i++) {
			int sum = 0;
			for (int j=0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					sum += hand.get(j).getValue();
				}
			}
			if (sum == 15) {
				count++;
			}
		}
		return count*2;
	}
	
	/* This method scores the hand for pair combinations
	 * Arguments:
	 * 		hand: List<Card>
	 * Returns:
	 * 		an int score
	 */
	private int scorePairs(List<Card> hand) {
		int score = 0;
		for (int i=0; i < hand.size(); i++) {
			for (int j=i+1; j < hand.size(); j++) {
				if (hand.get(i).rank == hand.get(j).rank) {
					score += 2;
				}
			}
		}
		return score;
	}
	
	/* This method scores the max run of a hand and disregards other subsets
	 * Arguments:
	 * 		hand: List<Card> object
	 * Returns:
	 * 		an int of the hand's score
	 */
	private int scoreRuns(List<Card> hand) {
		List<List<Card>> allSubsets = getSubsets(hand);
		int maxRunLen = 0;
		int runScore = 0;

		for (List<Card> subset : allSubsets) {
			if (subset.size() >= 3 && isRun(subset)) {
				if (subset.size() > maxRunLen) {
					maxRunLen = subset.size();
					runScore = 1;
				} else if (subset.size() == maxRunLen) {
					runScore++;
				}
			}
		}
		return runScore*maxRunLen;
	}

	/* This method is a helper method for the scoreRuns method that checks for subsets of runs
	 * Arguments:
	 * 		cards: List<Card>
	 * Returns:
	 * 		a subset of all runs based on the cards argument
	 */
	private List<List<Card>> getSubsets(List<Card> cards) {
		List<List<Card>> subsets = new ArrayList<>();
		int n = cards.size();
		//
		for (int i=1; i < (1 << n); i++) {
			List<Card> subset = new ArrayList<>();
			for (int j=0; j < n; j++) {
				if ((i & (1 << j)) != 0) {
					subset.add(cards.get(j));
				}
			}
			subsets.add(subset);
		}
		return subsets;
	}

	/* This method is a helper for the scoreRuns method to check is the hand has a run combination
	 * Arguments:
	 * 		cards: a List<Card> object
	 * Returns:
	 * 		a boolean based on if there is a run in the cards
	 */
	private boolean isRun(List<Card> cards) {
		if (cards.size() < 3) return false;

		List<Integer> values = new ArrayList<>();
		for (Card c : cards) {
			values.add(c.rank.ordinal() + 1);
		}
		Collections.sort(values);

		for (int i=1; i < values.size(); i++) {
			if (values.get(i) != values.get(i-1) + 1) {
				return false;
			}
		}
		return true;
	}

	/* This method scores for flushes in the hand and uses the starter card
	 * Arguments:
	 * 		starter: a Card object
	 * Returns:
	 * 		an int of the score of the hand
	 */
	private int scoreFlush(Card starter) {
		if (hand.size() < 4) return 0;

		Suit suit = hand.get(0).suit;
		boolean allSame = true;
		for (Card c : hand) {
			if (c.suit != suit) allSame = false;
		}
		
		if (allSame) {
			// check if starter matches suit for 5 points
			if (starter.suit == suit) {
				return 5;
			}
			return 4;
		}
		return 0;
	}
	
	/* This method checks for nobs by comparing the hand to a Jack and the starter suit
	 * Arguments:
	 * 		starter: a Card object
	 * Returns:
	 * 		an int score of the hand
	 */
	private int scoreNobs(Card starter) {
		for (Card c : hand) {
			if (c.rank == Rank.JACK && c.suit == starter.suit) {
				return 1;
			}
		}
		return 0;
	}

	/* This method gets all the cards from the hand that can be played
	 * Arguments:
	 * 		currentTotal: an int of the current total sum of discarded cards
	 * Returns:
	 * 		a List<Card> of cards that won't exceed 31 points if played
	 */
	public List<Card> getPlayableCards(int currentTotal) {
	    List<Card> playable = new ArrayList<>();
	    for (Card card : hand) {
	        if (card.getValue() + currentTotal <= 31) {
	            playable.add(card);
	        }
	    }
	    return playable;
	}
}
