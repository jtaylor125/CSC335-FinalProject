package model;

public class Player {
	// instance variables
	private Hand playerHand;
	
	private int score;
	
	private boolean isDealer;

	private int winCount;
	
	// class constructor
	public Player() {
		this.playerHand = new Hand();
		
		this.score = 0;
		
		this.isDealer = false;
	}
	
	/* This method adds a card object to playerHand
	 * Arguments:
	 * 		card: Card object
	 */
	public void addToHand(Card card) {
		this.playerHand.addCard(card);
	}
	
	/* This method removes a card from playerHand
	 * Arguments:
	 * 		card: Card object
	 */
	public void discard(Card card) {
		this.playerHand.removeCard(card);
	}
	
	/* This method checks if the player is the dealer
	 * Returns:
	 * 		true if player is the dealer otherwise false
	 */
	public boolean isDealer() {
		return this.isDealer;
	}
	
	/* This method gets the score of the player
	 * Returns:
	 * 		int score
	 */
	public int getScore() {
		return this.score;
	}

	/* This method gets the number of times a player has won a game
	 * Returns:
	 * 		int winCount
	 */
	public int getWinCount() {
		return this.winCount;
	}
	
	/* This method gets the playerHand; only for packages use
	 * Returns:
	 * 		Hand playerHand
	 */ 
	Hand getHand() {
		return this.playerHand;
	}
	
	/* This method sets the player as the dealer or nondealer
	 * Arguments:
	 * 		dealer: boolean
	 */
	public void setDealer(boolean dealer) {
		this.isDealer = dealer;
	}
	
	/* This method adds to the score instance variable
	 * Arguments:
	 * 		addValue: int
	 */
	public void addScore(int addValue) {
		this.score = this.score + addValue;
	}
	
	/* This method checks if a player's playerHand is empty
	 * Returns:
	 * 		true if playerHand is empty otherwise false
	 */
	public boolean isHandEmpty() {
		return playerHand.isEmpty();
	}
	
	/* This method scores the playerHand by calling the score method
	 * Returns:
	 * 		int score
	 */
	public int scoreHand(Card starter) {
		return playerHand.score(starter);
	}
	
	/* This method updates winCount whenever player has won
	 */
	public void playerWon() {
		++winCount;
	}
}
