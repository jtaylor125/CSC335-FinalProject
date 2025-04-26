/* Authors: Andy Zhang, Nathan Crossman, Jacob Taylor, Tristan Emma
 * Course: CSC 335
 * Description: An instance of this class represents a Player. A Player
 * has a Hand of Cards, a count of their wins, a boolean that determines
 * whether they are currently the dealer or not, and a score.
 */
package model;

public class Player {
	private Hand playerHand;
	
	private int score;
	
	private boolean isDealer;

	private int winCount;
	
	public Player() {
		this.playerHand = new Hand();
		
		this.score = 0;
		
		this.isDealer = false;

		this.winCount = 0;
	}
	
	
	public void addToHand(Card card) {
		this.playerHand.addCard(card);
	}
	
	public void discard(Card card) {
		this.playerHand.removeCard(card);
	}
	
	// getters
	
	public boolean isDealer() {
		return this.isDealer;
	}
	
	public int getScore() {
		return this.score;
	}

	public int getWinCount() {
		return this.winCount;
	}
	
	// not well encapsulated, for package use only
	Hand getHand() {
		return this.playerHand;
	}
	
	// setters
	
	public void setDealer(boolean dealer) {
		this.isDealer = dealer;
	}
	
	public void addScore(int addValue) {
		this.score = this.score + addValue;
	}
	
	public boolean isHandEmpty() {
		return playerHand.isEmpty();
	}
	
	public int scoreHand(Card starter) {
		return playerHand.score(starter);
	}

	public void playerWon() {
		++winCount;
	}
}
