package rules;

import java.util.List;
import model.Card;
import model.CardStack;

public interface Rule {
    int score(CardStack stack, Card starter); // starter can be null for pegging
    String name();
}
