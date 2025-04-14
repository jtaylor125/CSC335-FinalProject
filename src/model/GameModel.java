package model;

public class GameModel {
	private Player playerOne;
	private Player playerTwo;
	
	private Deck deck;
	
	private Hand crib;
	
	private Card starter;
	
	private CardStack playingRun;
	
	private Pegboard pegboard;
	
	public GameModel() {
		playerOne = new Player();
		playerTwo = new Player();
		
		// TO DO - complete Deck class
		deck = new Deck();
		
		// TO DO - complete Hand class
		crib = new Hand();
		
		// starter will be assigned in pegging play
		starter = null;
		
		// playingRun will be assigned in pegging play
		playingRun = null;
		
		// TO DO - complete pegboard class
		pegboard = new Pegboard();
	}
	
	// TO DO - complete determineDealer functionality
	// Draw two random cards from the deck, one for each player. The player with the 
	// lower card becomes the first dealer. Dealer gets the crib.
	public void determineDealer() {
		playerOne.addToHand(deck.drawRandom());
		playerTwo.addToHand(deck.drawRandom());
	}
	
	// TO DO - complete deal functionality
	// Each player is dealt 6 cards to their hand. They discard two, which makes up the
	// crib.
	public void deal() {
		
	}
	
	// TO DO - complete peggingPlay functionality
	// Flip the starter card, the top card on the deck. Players play cards on the 
	// playingRun and can score points for various things. One important aspect of this
	// is the count.
	public void peggingPlay() {
		
	}
	
	// TO DO - complete regularPlay functionality
	// Each player scores their hand with the starter card, and the dealer scores
	// their crib as well. 
	public void regularPlay() {
		
	}
	
	// TO DO - complete reset functionality
	// reset the deck, shuffle, and switch dealers
	public void reset() {
		
	}
	
}
