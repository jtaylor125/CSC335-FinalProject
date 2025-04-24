package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
		playerOne.discard(card1);
		playerTwo.discard(card2);

		// reshuffle deck
		deck.buildNewDeck();
		deck.shuffle();
	}
	
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
	
	public void discard(String player, String discard) {
		String rank = discard.substring(0, discard.indexOf(" "));
		String suit = discard.substring(discard.indexOf(" ")+1);
		Card discardCard = Card.get(rank,suit);
		
		if (player.toLowerCase().equals("player 1")) {
			crib.addCard(discardCard);
			playerOne.getHand().removeCard(discardCard);
		}
		else {
			crib.addCard(discardCard);
			playerTwo.getHand().removeCard(discardCard);;
		}
	}
	
	// Flip the starter card, the top card on the deck. Players play cards on the 
	// playingRun and can score points for various things. One important aspect of this
	// is the count.
	public void peggingPlay(Scanner input) {
	    starter = deck.drawTop();
	    playingRun = new CardStack();
	    runningTotal = 0;
	    
	    Player currentPlayer;
	    Player otherPlayer;
	    
	    if (playerOne.isDealer()) {
	        currentPlayer = playerTwo;
	        otherPlayer = playerOne;
	    }
	    else {
	        currentPlayer = playerOne;
	        otherPlayer = playerTwo;
	    }

	    boolean goCalled = false;
	    System.out.println("Starter card: " + starter);
	    while (!playerOne.isHandEmpty() || !playerTwo.isHandEmpty()) {
	        Hand currentHand = currentPlayer.getHand();
	        List<Card> playableCards = currentHand.getPlayableCards(runningTotal);

	        if (!playableCards.isEmpty()) {
	            System.out.println(getPlayerName(currentPlayer) + ", select a card to play:");
	            for (int i=0; i < playableCards.size(); i++) {
	                System.out.println(i + ": " + playableCards.get(i));
	            }

	            int selectedIndex = -1;
	            while (true) {
	                System.out.print("Enter the number of the card to play: ");
	                String selection = input.nextLine().strip();
	                // input validation
	                try {
	                    selectedIndex = Integer.parseInt(selection);
	                    if (selectedIndex >= 0 && selectedIndex < playableCards.size()) {
	                        break;
	                    }
	                    else {
	                        System.out.println("Not a valid option, Try again.");
	                    }
	                }
	                catch (NumberFormatException e) {
	                    System.out.println("Invalid input, Please enter a number.");
	                }
	            }

	            Card play = playableCards.get(selectedIndex);
	            currentHand.removeCard(play);
	            playingRun.push(play);
	            runningTotal += play.getValue();

	            int points = 0;
	            if (runningTotal == 15) {
	                points += 2;
	            }

	            if (runningTotal == 31) {
	                points += 2;
	            }

	            points += scorePair(playingRun);
	            points += scoreRun(playingRun);

	            pegboard.addPoints(currentPlayer, points);
	            System.out.println(getPlayerName(currentPlayer) + " plays " + play + " for " + points + 
	            		" point(s). Running total: " + runningTotal);
	            
	            if (runningTotal == 31 || bothPlayersCantPlay(currentPlayer, otherPlayer)) {
	                if (runningTotal != 31) {
	                    pegboard.addPoints(currentPlayer, 1);
	                    System.out.println(getPlayerName(currentPlayer) + " scores 1 point for last card.");
	                }

	                runningTotal = 0;
	                playingRun.clear();
	                goCalled = false;
	            }
	        }
	        else {
	            if (goCalled) {
	                pegboard.addPoints(otherPlayer, 1);
	                System.out.println(getPlayerName(currentPlayer) + " says 'Go'. " + getPlayerName(otherPlayer) + " scores 1 point.");
	                runningTotal = 0;
	                playingRun.clear();
	                goCalled = false;
	            }
	            else {
	                System.out.println(getPlayerName(currentPlayer) + " says 'Go'");
	                goCalled = true;
	            }
	        }
	        Player temp = currentPlayer;
	        currentPlayer = otherPlayer;
	        otherPlayer = temp;
	    }
	    if (runningTotal > 0 && runningTotal < 31) {
	        pegboard.addPoints(currentPlayer, 1);
	        System.out.println(getPlayerName(currentPlayer) + " scores 1 point for final card.");
	    }
	}
	private String getPlayerName(Player player) {
	    if (player == playerOne) {
	        return "Player 1";
	    }
	    else {
	        return "Player 2";
	    }
	}
	// scores pairs of the current CardStack
	private int scorePair(CardStack stack) {
	    List<Card> cards = new ArrayList<>();
	    for (Card c : stack) {
	        cards.add(c);
	    }

	    int count = 1;
	    for (int i=cards.size()-2; i >= 0; i--) {
	        if (cards.get(i).rank == cards.get(cards.size()-1).rank) {
	            count++;
	        }
	        else break;
	    }
	    if (count == 2) return 2;
	    else if (count == 3) return 6;
	    else if (count == 4) return 12;
	    return 0;
	}

	// TO DO - complete regularPlay functionality
	// Each player scores their hand with the starter card, and the dealer scores
	// their crib as well. 
	public void regularPlay() {
		int plrOnePts = playerOne.scoreHand(starter);
		System.out.println("Player 1 scores " + plrOnePts);
		int plrTwoPts = playerTwo.scoreHand(starter);
		System.out.println("Player 2 scores " + plrTwoPts);
		
		// add the scores to the pegboard
		pegboard.addPoints(playerOne, plrOnePts);
		pegboard.addPoints(playerTwo, plrTwoPts);
		
		System.out.println("Crib: " + getCrib());

		// the dealer scores the crib
		if (playerOne.isDealer()) {
			int cribPts = crib.score(starter);
			pegboard.addPoints(playerOne, cribPts);
			System.out.println("Player 1 scores " + cribPts + " from the crib");
		}
		else {
			int cribPts = crib.score(starter);
			pegboard.addPoints(playerTwo, cribPts);
			System.out.println("Player 2 scores " + cribPts + " from the crib");
		}
		
	}
	// Checks for a runs in the current pegging play, and only scores the longest one
	private int scoreRun(CardStack stack) {
	    List<Card> cards = new ArrayList<>();
	    for (Card c : stack) {
	        cards.add(c);
	    }

	    int maxRun = 0;
	    for (int len = 3; len <= cards.size(); len++) {
	        List<Card> sub = cards.subList(cards.size() - len, cards.size());
	        if (isRun(sub)) {
	            maxRun = len;
	        }
	    }
	    return maxRun;
	}
	// helper method for scoreRun
	private boolean isRun(List<Card> cards) {
	    if (cards.size() < 3) {
	        return false;
	    }

	    List<Integer> values = new ArrayList<>();
	    for (Card card : cards) {
	        values.add(card.rank.ordinal());
	    }

	    Collections.sort(values);
	    for (int i=1; i < values.size(); i++) {
	        if (values.get(i) != values.get(i - 1) + 1) {
	            return false;
	        }
	    }

	    return true;
	}
	
	// checks if both players can no longer play
	private boolean bothPlayersCantPlay(Player p1, Player p2) {
	    List<Card> playerOnePlayable = p1.getHand().getPlayableCards(runningTotal);
	    List<Card> playerTwoPlayable = p2.getHand().getPlayableCards(runningTotal);

	    return playerOnePlayable.isEmpty() && playerTwoPlayable.isEmpty();
	}
	
	
	
	// TO DO - complete reset functionality
	// reset the deck, shuffle, and switch dealers
	// completed
	public void reset() {
		playerOne.getHand().clear();
		playerTwo.getHand().clear();
		starter = null;
		runningTotal = 0;
		crib.clear();
		playingRun.clear();
		deck.buildNewDeck();
		deck.shuffle();
		switchDealers();
	}
	// completed
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
		}
		else {
			return "Player 2";
		}
	}
	// gets the hand of a player
	public String getHand(String player) {
		String hand = "";
		if (player.toLowerCase().equals("player 1")) {
			for (Card c : playerOne.getHand().gethand()) {
				hand += c.toString() +", ";
			}
		}
		else {
			for (Card c : playerTwo.getHand().gethand()) {
				hand += c.toString() +", ";
			}
		}
		return hand.substring(0,hand.length()-2);
	}
	// gets the current crib
	public String getCrib() {
		String cribString = "";
		for (Card c : crib.gethand()) {
			cribString += c.toString() +", ";
		}
		return cribString.substring(0,cribString.length()-2);
	}
	// gets the score of a player using the front peg
	public int getScore(String playerName) {
	    if (playerName.toLowerCase().equals("player 1")) {
	        return pegboard.getScore(playerOne);
	    }
	    else {
	        return pegboard.getScore(playerTwo);
	    }
	}
	public String getPegboard() {
		return pegboard.toString();
	}
	
	public String checkWin() {
		if (pegboard.hasWon(playerOne)) return "Player 1 wins!";
		else if (pegboard.hasWon(playerTwo)) return "Player 2 wins!";
		return null;
		
	}
}

