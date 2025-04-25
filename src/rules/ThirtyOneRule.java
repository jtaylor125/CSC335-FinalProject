package rules;

import model.Card;
import model.CardStack;

@ScoringRule(type = RuleType.PEGGING)
public class ThirtyOneRule implements Rule {

    @Override
    public int score(Iterable<Card> stack, Card starter) {
        int total = 0;
        for (Card c : stack) {
            total += c.getValue();
        }
        return (total == 31) ? 2 : 0;
    }

    @Override
    public String name() {
        return "Thirty-One Rule";
    }
}