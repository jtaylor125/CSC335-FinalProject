package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class EasyStrategy implements Strategy {
    private Random rand = new Random();

	@Override
	public List<Card> chooseDiscards(List<Card> hand) {
        ArrayList<Card> copy = new ArrayList<>(hand);
        Collections.shuffle(copy);
        return copy.subList(0, 2);
		
	}

	@Override
	public Card choosePeggingPlayCard(List<Card> playableCards, int runningTotal, List<Card> playedCards) {
        return playableCards.get(rand.nextInt(playableCards.size()));
	}

}
