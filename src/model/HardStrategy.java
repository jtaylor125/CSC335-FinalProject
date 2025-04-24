package model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class HardStrategy implements Strategy {

    @Override
    public List<Card> chooseDiscards(List<Card> hand) {
        // discard the 2 highest cards
        List<Card> copy = new ArrayList<>(hand);
        copy.sort(Comparator.comparingInt(Card::getValue).reversed());
        return copy.subList(0, 2);
    }

    @Override
    public Card choosePeggingPlayCard(List<Card> playableCards, int runningTotal, List<Card> playedCards) {
        // prefer lowest card that doesn't exceed 31
        playableCards.sort(Comparator.comparingInt(Card::getValue));
        return playableCards.get(0);
    }
}