package rules;

import model.Card;
import model.CardStack;

@ScoringRule(type = RuleType.PEGGING)
public class FifteenRule implements Rule {

    @Override
    public int score(CardStack stack, Card starter) {
        int total = 0;
        for (Card c : stack) {
            total += c.getValue();
        }
        return (total == 15) ? 2 : 0;
    }

    @Override
    public String name() {
        return "Fifteen Rule";
    }
}
