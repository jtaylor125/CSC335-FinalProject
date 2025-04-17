package model;

import java.util.HashMap;

public class Pegboard {
	private static final int WINNING_SCORE = 121;

	private HashMap<Player, Integer> score;

	public Pegboard() {
		score = new HashMap<>();
	}

	// this method initalizes player scores if needed
	public void addPlayer(Player player) {
		if (!score.containsKey(player)) {
			score.put(player, 0);
		}
	}

	// add points to a player's score
	public void addPoints(Player player, int points) {
		addPlayer(player);  // ensure player is tracked

		int currentScore = score.get(player);
		int newScore = currentScore + points;
		score.put(player, newScore);
	}

	// get the score of a player
	public int getScore(Player player) {
		addPlayer(player);  // ensure player is tracked
		return score.get(player);
	}

	// check if a player has won
	public boolean hasWon(Player player) {
		return getScore(player) >= WINNING_SCORE;
	}
	
	// returns a copy of the hashmap that keeps track of scores
	public HashMap<Player, Integer> getAllScores() {
		return new HashMap<Player, Integer>(score);
	}
}
