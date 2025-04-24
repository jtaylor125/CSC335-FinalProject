package model;

import java.util.List;

public class Computer {
    private Player player;
    private Strategy strategy;

    public Computer(Player player, Strategy difficulty) {
        this.player = player;
        this.strategy = difficulty;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Card> chooseDiscarded() {
        return strategy.chooseDiscards(player.getHand().getHand());
    }

    public Card choosePlay(int runningTotal, List<Card> played) {
        List<Card> playable = player.getHand().getPlayableCards(runningTotal);
        return strategy.choosePeggingPlayCard(playable, runningTotal, played);
    }
}
