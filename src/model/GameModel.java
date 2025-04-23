package model;

public class GameModel {
	private Player playerOne;
	private Player playerTwo;
	
	private Deck deck;
	
	private Hand crib;
	
	private Card starter;
	
	private CardStack playingRun;
	
	private Pegboard pegboard;

	private int runningTotal;
	
	public GameModel() {
		playerOne = new Player();
		playerTwo = new Player();
		
		deck = new Deck();
		
		crib = new Hand();
		
		starter = null;
		
		playingRun = null;
		
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

		// remove cards from players hands add to cardStack
		//playerOne.discard(card1);
		//playerTwo.discard(card2);

		// drawn cards return to the deck


		// reshuffle deck
		deck.shuffle();
	}
	
	// Each player is dealt 6 cards to their hand. They discard two, which makes up the
	// crib.
	public void deal() {
		// deal the first hand to player 1 if player 2 is dealer
		if (playerTwo.isDealer()) {
			for (int i=0; i < 5; i++) {
				playerOne.addToHand(deck.drawTop());
				playerTwo.addToHand(deck.drawTop());
			}
		}
		// deal the first hand to player 2 if player 1 is dealer
		else {
			for (int i=0; i < 5; i++) {
				playerTwo.addToHand(deck.drawTop());
				playerOne.addToHand(deck.drawTop());
			}
		}
		// discards should probably be handled somewhere else as players need to see their cards
		// before choosing which one to discard
	}
	
	public void discard(String player, String discard) {
		String rank = discard.substring(0, discard.indexOf(" "));
		String suit = discard.substring(discard.indexOf(" ")+1);
		Card discardCard = Card.get(rank,suit);
		
		if (player.equals("Player 1")) {
			crib.addCard(discardCard);
			playerOne.getHand().removeCard(discardCard);
		} else {
			crib.addCard(discardCard);
			playerTwo.getHand().removeCard(discardCard);;
		}
	}
	
	// Flip the starter card, the top card on the deck. Players play cards on the 
	// playingRun and can score points for various things. One important aspect of this
	// is the count.
	public void peggingPlay() {
		starter = deck.drawTop();
		playingRun = new CardStack();
		runningTotal = 0;
		
		Player currentPlayer = playerOne.isDealer() ? playerTwo : playerOne;
	    Player otherPlayer = playerOne.isDealer() ? playerOne : playerTwo;
		
		
		// store hand and crib scores before cards are used
		int playerOneHandScore = playerOne.getHand().score(starter);
		int playerTwoHandScore = playerTwo.getHand().score(starter);
		int cribScore = crib.score(starter);
		
		boolean roundOver = false;
	    boolean goCalled = false;

	    while (!playerOne.isHandEmpty() || !playerTwo.isHandEmpty()) {
	        boolean turnTaken = false;

	        Hand currentHand = currentPlayer.getHand();
	        List<Card> playableCards = currentHand.getPlayableCards(runningTotal); // assumes method exists

	        if (!playableCards.isEmpty()) {
	            Card play = playableCards.get(0); // basic AI: play first valid
	            currentHand.removeCard(play);
	            playingRun.addCard(play);
	            runningTotal += play.rank.getValue(); // assumes rank has numeric value

	            // Scoring logic (pseudo code):
	            int points = 0;
	            if (runningTotal == 15) points += 2;
	            if (runningTotal == 31) points += 2;
	            points += playingRun.scoreRun(); // your method to check runs/pairs/etc.

	            pegboard.addPoints(currentPlayer, points);
	            System.out.println(currentPlayer.getName() + " plays " + play + " for " + points + " points");

	            turnTaken = true;

	            // Check for 31 reset
	            if (runningTotal == 31 || currentPlayer.getHand().getPlayableCards(runningTotal).isEmpty() &&
	                otherPlayer.getHand().getPlayableCards(runningTotal).isEmpty()) {
	                runningTotal = 0;
	                playingRun.clear(); // reset stack
	                goCalled = false;
	            }
	        } else {
	            if (goCalled) {
	                // "Go" was already called last turn, award 1 point to opponent
	                pegboard.addPoints(otherPlayer, 1);
	                runningTotal = 0;
	                playingRun.clear();
	                goCalled = false;
	                System.out.println(currentPlayer.getName() + " cannot play. " + otherPlayer.getName() + " scores 1 point.");
	            } else {
	                goCalled = true;
	                System.out.println(currentPlayer.getName() + " says 'Go'");
	            }
	        }

	        // Swap players
	        Player temp = currentPlayer;
	        currentPlayer = otherPlayer;
	        otherPlayer = temp;
	    }
	}

	// TO DO - complete regularPlay functionality
	// Each player scores their hand with the starter card, and the dealer scores
	// their crib as well. 
	public void regularPlay() {
		int plrOnePts = playerOne.scoreHand(starter);
		int plrTwoPts = playerTwo.scoreHand(starter);

		// add the scores to the pegboard
		pegboard.addPoints(playerOne, plrOnePts);
		pegboard.addPoints(playerTwo, plrTwoPts);

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
	
	// getters
	public String getDealer() {
		if (playerOne.isDealer()) {
			return "Player 1";
		} else {
			return "Player 2";
		}
	}
	
	public String getHand(String player) {
		String hand = "";
		if (player.equals("Player 1")) {
			for (Card c : playerOne.getHand().gethand()) {
				hand += c.toString() +", ";
			}
		} else {
			for (Card c : playerTwo.getHand().gethand()) {
				hand += c.toString() +", ";
			}
		}
		return hand.substring(0,hand.length()-2);
	}
	
	public String getCrib() {
		String cribString = "";
		for (Card c : crib.gethand()) {
			cribString += c.toString() +", ";
		}
		return cribString.substring(0,cribString.length()-2);
	}
}
