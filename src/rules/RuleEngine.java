package rules;

import model.Card;
import model.CardStack;

import java.util.*;

public class RuleEngine {
    private final List<Rule> peggingRules = new ArrayList<>();

    public RuleEngine() {
        loadRules();
    }

    private void loadRules() {
        registerRule(new PairRule());
        registerRule(new FifteenRule());
        registerRule(new RunRule());
    }

    private void registerRule(Rule rule) {
        ScoringRule annotation = rule.getClass().getAnnotation(ScoringRule.class);
        if (annotation != null && annotation.type() == RuleType.PEGGING) {
            peggingRules.add(rule);
        }
    }

    public int scorePegging(CardStack stack) {
        int total = 0;
        for (Rule rule : peggingRules) {
            total += rule.score(stack, null);
        }
        return total;
    }
}
