package model;

public class GameModel {
	private Player playerOne;
	private Player playerTwo;
	
	private Deck deck;
	
	private Hand crib;

	private Card discardedCard;
	
	private Card starter;
	
	private CardStack playingRun;
	
	private Pegboard pegboard;

	private int runningTotal;
	
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

		// initiate running total
		runningTotal = 0;
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

		// remove cards from players hands add to cardStack
		//playerOne.discard(card1);
		//playerTwo.discard(card2);

		// drawn cards return to the deck


		// reshuffle deck
		deck.shuffle();
	}
	
	// TO DO - complete deal functionality
	// Each player is dealt 6 cards to their hand. They discard two, which makes up the
	// crib.
	public void deal() {
		// deal the first hand to player 1 if player 2 is dealer
		if (playerTwo.isDealer()) {
			for (int i=0; i < 6; i++) {
				playerOne.addToHand(deck.drawTop());
				playerTwo.addToHand(deck.drawTop());
			}
		}
		// deal the first hand to player 2 if player 1 is dealer
		else {
			for (int i=0; i < 6; i++) {
				playerTwo.addToHand(deck.drawTop());
				playerOne.addToHand(deck.drawTop());
			}
		}
		// discards should probably be handled somewhere else as players need to see their cards
		// before choosing which one to discard
	}
	
	// Flip the starter card, the top card on the deck. Players play cards on the 
	// playingRun and can score points for various things. One important aspect of this
	// is the count.
	public void peggingPlay() {
		// get starter card
		starter = deck.drawRandom();
		
		// create center pile stack
		CardStack centerPile = new CardStack();

		// pass starter card to method in Hand class

		// set current player and second player
		Player currentPlayer;
		Player secondPlayer;

		if (playerOne.isDealer()) {
			currentPlayer = playerTwo;
			secondPlayer = playerOne;
		}
		else {
			currentPlayer = playerOne;
			secondPlayer = playerTwo;
		}
		
		// main game play loop
		while (!playerOne.isHandEmpty() && !playerTwo.isHandEmpty()) {

			// inner loop resets when both players say go
			while (!playGo(playerOne) && !playGo(playerTwo)) {
				if (playerOne.isDealer()) {
					addToPile(playerTwo, centerPile);
					addToPile(playerOne, centerPile);
				}
				else {
					addToPile(playerOne, centerPile);
					addToPile(playerTwo, centerPile);
				}
			}

			// reset running total after both players play go
			runningTotal = 0;
		}
	}

	// controller gets user chosen card to discard to center pile
	private void addToPile(Player player, CardStack pile) {
		pile.push(discardedCard);
		player.discard(discardedCard);

		// add to player score
	}

	// change card to discard based on controller
	public void updateDiscardedCard(Card card) {
		discardedCard = card;
	}

	// TO DO - complete regularPlay functionality
	// Each player scores their hand with the starter card, and the dealer scores
	// their crib as well. 
	public void regularPlay() {
		int plrOnePts = playerOne.scoreHand(starter);
		int plrTwoPts = playerTwo.scoreHand(starter);

		// add the scores to the pegboard
		pegboard.addPoints(playerOne, plrOnePts);
		pegboard.addPoints(playerTwo, plrOnePts);

		// the dealer scores the crib
		if (playerOne.isDealer()) {
			int cribPts = crib.score(starter);
			pegboard.addPoints(playerOne, cribPts);
		}
		else {
			int cribPts = crib.score(starter);
			pegboard.addPoints(playerTwo, cribPts);
		}
	}

	// check if player's hand exceeds 31 points
	public boolean playGo(Player player) {
		boolean playGo = true;

		// loop through player's hand to check if a card can be played
		for (Card card : player.getHand().getHand()) {

			if (getCardOrdinal(card)+runningTotal <= 31) {
				playGo = false;
				break;
			}
		}
		return playGo;
	}

	public int getCardOrdinal(Card card) {
		if (card.rank == Rank.JACK || card.rank == Rank.QUEEN || card.rank == Rank.KING) return 10;
		return card.rank.ordinal();
	}

	
	// TO DO - complete reset functionality
	// reset the deck, shuffle, and switch dealers
	public void reset() {
		
		deck.shuffle();
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
