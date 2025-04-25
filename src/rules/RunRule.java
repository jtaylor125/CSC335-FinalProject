package rules;

import model.Card;

import java.util.*;

@ScoringRule(type = RuleType.PEGGING)
public class RunRule implements Rule {

    @Override
    public int score(Iterable<Card> stack, Card starter) {
        List<Card> cards = new ArrayList<>();
	    for (Card c : stack) {
	        cards.add(c);
	    }

	    int maxRun = 0;
	    for (int len = 3; len <= cards.size(); len++) {
	        List<Card> sub = cards.subList(cards.size() - len, cards.size());
	        if (isRun(sub)) {
	            maxRun = len;
	        }
	    }
	    return maxRun;
	}
	/* This helper method checks if a given list of cards forms a valid run
	 * by comparing ordinal values of card ranks in sorted order.
	 * Arguments:
	 *      cards: a List of cards to check
	 * Returns:
	 *      true if the cards form a consecutive run; false otherwise
	 */
	private boolean isRun(List<Card> cards) {
	    if (cards.size() < 3) {
	        return false;
	    }

	    List<Integer> values = new ArrayList<>();
	    for (Card card : cards) {
	        values.add(card.rank.ordinal());
	    }

	    Collections.sort(values);
	    for (int i=1; i < values.size(); i++) {
	        if (values.get(i) != values.get(i - 1) + 1) {
	            return false;
	        }
	    }

	    return true;
	}

    @Override
    public String name() {
        return "Run Rule";
    }
}
