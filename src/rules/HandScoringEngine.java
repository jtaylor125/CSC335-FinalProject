package rules;

import java.util.ArrayList;
import java.util.List;

import model.Card;

public class HandScoringEngine {
    private final List<Rule> rules = new ArrayList<>();

    public HandScoringEngine() {
        // Register hand scoring rules
        registerRule(new HandFifteenRule());
        registerRule(new HandPairRule());
        registerRule(new HandRunRule());
        registerRule(new HandFlushRule());
        registerRule(new HandNobsRule());
    }

    public void registerRule(Rule rule) {
        rules.add(rule);
    }

    public int scoreHand(List<Card> cards, Card starter) {
        int score = 0;
        for (Rule rule : rules) {
            score += rule.score(cards, starter);
        }
        return score;
    }
}
