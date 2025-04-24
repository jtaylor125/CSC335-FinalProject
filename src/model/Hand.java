package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand {
	private final List<Card> hand;
	
	//TO DO
	public Hand() {
		//Hands should start empty and be added to
		//All hand in the hand need to be accessible
		hand = new ArrayList<>();

	}
	
	public void addCard(Card card) {
		this.hand.add(card);
	}
	
	public void removeCard(Card card) {
		this.hand.remove(card);
	}

	public boolean isEmpty() {
		return hand.isEmpty();
	}
	public List<Card> gethand() {
		// return a copy of the hand, avoidance of problematic escaping references
		return new ArrayList<>(hand);
	}

	public void clear() {
		hand.clear();
	}

	public int size() {
		return hand.size();
	}

	// score the hand + starter card
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
	
	// check for 15s
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
	
	// checks for pairs
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
	
	// This class scores the maximum run once, doesn't score the subsets
	// so if we have 10 J K Q, it only scores a run of 4 and not 3 runs of 3
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
	// helper method for scoreRuns
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
	// helper method for scoreRuns
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
	// checks for flushes
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
	
	// checks for nobs
	private int scoreNobs(Card starter) {
		for (Card c : hand) {
			if (c.rank == Rank.JACK && c.suit == starter.suit) {
				return 1;
			}
		}
		return 0;
	}
	
	
	
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
