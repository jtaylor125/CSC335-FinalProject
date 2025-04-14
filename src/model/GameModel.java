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
		
		// TO DO - complete pegboard cloass
		pegboard = new Pegboard();
	}
	
	// Draw two random cards from the deck, one for each player. The player with the 
	// lower card becomes the first dealer. Dealer gets the crib.
	public void determineDealer() {
		// draw random cards from deck and add to players hands
		Card card1 = deck.drawRandom();
		Card card2 = deck.drawRandom();

		while (card1.rank.ordinal() == card2.rank.ordinal()) {
			deck.shuffle();
			card1 = deck.drawRandom();
			card2 = deck.drawRandom();
		}

		playerOne.addToHand(card1);
		playerTwo.addToHand(card2);

		// compare rank of each players hands
		if (card1.rank.ordinal() < card2.rank.ordinal()) {
			playerTwo.setDealer(true);
		}
		else {
			playerOne.setDealer(true);
		}

		// remove cards from players hands
		playerOne.discard(card1);
		playerTwo.discard(card2);

		// drawn cards return to the deck

	}
	
	// TO DO - complete deal functionality
	// Each player is dealt 6 cards to their hand. They discard two, which makes up the
	// crib.
	public void deal() {
		// deal the first hand to player 1 if player 2 is dealer
		if (playerTwo.isDealer()) {
			for (int i=0; i < 6; i++) {
				playerOne.addToHand(deck.pop());
				playerTwo.addToHand(deck.pop());
			}
		}
		// deal the first hand to player 2 if player 1 is dealer
		else {
			for (int i=0; i < 6; i++) {
				playerTwo.addToHand(deck.pop());
				playerOne.addToHand(deck.pop());
			}
		}
		// discards should probably be handled somewhere else as players need to see their cards
		// before choosing which one to discard
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

		switchDealers();
	}
	
	private void switchDealers() {
		// switch dealers
		if (playerOne.isDealer()) {
			playerOne.setDealer(false);
			playerTwo.setDealer(true);
		}
		else {
			playerOne.setDealer(true);
			playerTwo.setDealer(false);
		}
	}
}
