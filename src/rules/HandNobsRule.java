package rules;

import model.Card;
import model.Rank;

public class HandNobsRule implements Rule {

	@Override
	public int score(Iterable<Card> cards, Card starter) {
		for (Card c : cards) {
            if (c.rank == Rank.JACK && c.suit == starter.suit) {
                return 1;
            }
        }
        return 0;
	}

	@Override
    public String name() {
        return "Nobs Rule";
    }

}
