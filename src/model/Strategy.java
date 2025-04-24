package model;

import java.util.List;

public interface Strategy {
	public List<Card> chooseDiscards(List<Card> hand);
	
	public Card choosePeggingPlayCard(List<Card> playableCards, int runningTotal, List<Card> playedCards);

}
