package rules;


import model.Card;


public interface Rule {
    int score(Iterable<Card> cards, Card starter); // starter can be null for pegging
    String name();
}
