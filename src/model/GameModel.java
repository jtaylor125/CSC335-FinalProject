/* Authors: Andy Zhang
 * Course: CSC 335
 * Description: This class is used to simulate a game of Cribbage. It is composed of many Classes
 * such as Player, Deck, Hand, CardStack, and Pegboard.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import rules.PeggingScoringEngine;

public class GameModel {
	private Player playerOne;
	private Player playerTwo;
	
	private Deck deck;
	private Hand crib;
	private Card starter;
	private CardStack playingRun;
	private Pegboard pegboard;
	private int runningTotal;
	
	private PeggingScoringEngine ruleEngine;
	
	
	// Constructor
	public GameModel() {
		playerOne = new Player();
		playerTwo = new Player();
		
		deck = new Deck();
		
		crib = new Hand();
		
		starter = null;
		
		playingRun = null;
		
		pegboard = new Pegboard();
		
		ruleEngine = new PeggingScoringEngine();
	}
	
    /* This method determines the dealer by drawing a random card for each player.
     * The player with the lower card becomes the dealer. The drawn cards are discarded,
     * and the deck is reset and shuffled for play.
     */
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
	
    /* This method deals six cards to each player based on who is the dealer.
     * The non-dealer receives cards first, followed by the dealer.
     */
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
	}
    /* This method removes a specified card from a player's hand and adds it to the crib.
     * Arguments:
     *      player: a String indicating the player ("Player 1" or "Player 2")
     *      discard: a String representing the card to discard (e.g., "QUEEN HEARTS")
     */
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
	
    /* This method executes pegging play between two human players.
     * It handles alternating turns, card selection, point scoring, and turn logic.
     * Returns true or false to determine if someone won
     * Arguments:
     *      input: a Scanner used to receive player input
     * Returns:
     * 		true or false
     */
	public boolean peggingPlay(Scanner input) {
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
	    // game loop
	    while (!playerOne.isHandEmpty() || !playerTwo.isHandEmpty()) {
	        Hand currentHand = currentPlayer.getHand();
	        List<Card> playableCards = currentHand.getPlayableCards(runningTotal);
	        // play until both players run out of cards to play
	        if (!playableCards.isEmpty()) {
	            System.out.println(getPlayerName(currentPlayer) + ", select a card to play:");
	            for (int i=0; i < playableCards.size(); i++) {
	                System.out.println(i + ": " + playableCards.get(i));
	            }

	            int selectedIndex = -1;
                // input validation
	            while (true) {
	                System.out.print("Enter the number of the card to play: ");
	                String selection = input.nextLine().strip();
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

	            int points = ruleEngine.scorePegging(playingRun);
	            // add points
	            pegboard.addPoints(currentPlayer, points);
				if (checkIfPlayerWon(currentPlayer, otherPlayer)) return true;

	            System.out.println(getPlayerName(currentPlayer) + " plays " + play + " for " + points + 
	            		" point(s). Running total: " + runningTotal);
	            // last card scoring
	            if (runningTotal == 31 || bothPlayersCantPlay(currentPlayer, otherPlayer)) {
	                if (runningTotal != 31) {
	                    pegboard.addPoints(currentPlayer, 1);
						if (checkIfPlayerWon(currentPlayer, otherPlayer)) return true;
	                    System.out.println(getPlayerName(currentPlayer) + " scores 1 point for last card.");
	                }

	                runningTotal = 0;
	                playingRun.clear();
	                goCalled = false;
	            }
	        }
	        // "Go" scoring
	        else {
	            if (goCalled) {
	                pegboard.addPoints(otherPlayer, 1);
					if (checkIfPlayerWon(currentPlayer, otherPlayer)) return true;
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
			if (checkIfPlayerWon(currentPlayer, otherPlayer)) return true;
	        else System.out.println(getPlayerName(currentPlayer) + " scores 1 point for final card.");
	    }
	    return false;
	}

	// check if a player has won
	private boolean checkIfPlayerWon(Player player1, Player player2) {
		if (pegboard.hasWon(player1)) {
			if (getPlayerName(player1).equals("Player 1")) {
				playerOne.playerWon();
				System.out.println(getPlayerName(player1) + " has won!");
				pegboard.resetScores();
				reset();
			}
			else {
				playerTwo.playerWon();
				System.out.println(getPlayerName(player2) + " has won!");
				pegboard.resetScores();
				reset();
			}
			return true;
		}
		else if (pegboard.hasWon(player2)) {
			if (getPlayerName(player2).equals("Player 1")) {
				playerOne.playerWon();
				System.out.println(getPlayerName(player1) + " has won!");
				reset();
			}
			else {
				playerTwo.playerWon();
				System.out.println(getPlayerName(player2) + " has won!");
				reset();
			}
			return true;
		}
		return false;
	}
	
	/* This method executes pegging play for a one-player game human vs computer.
	 * It alternates turns between the human and computer player, evaluates playable cards,
	 * calculates scores for runs, pairs, and special totals like 15 and 31, and manages "go" calls.
	 * Returns true or false to determine if someone won during the middle of the round
	 * Arguments:
	 *      input: a Scanner object for user input
	 *      computer: a Computer object representing the AI opponent
	 * Returns:
	 * 		true or false
	 */
	public boolean onePlayerPeggingPlay(Scanner input, Computer computer) {
	    starter = deck.drawTop();
	    playingRun = new CardStack();
	    runningTotal = 0;

	    Player player1 = playerOne;
	    Player computerPlayer = computer.getPlayer();

	    Player currentPlayer;
	    Player otherPlayer;
	    
	    if (player1.isDealer()) {
	        currentPlayer = computerPlayer;
	        otherPlayer = player1;
	    }
	    else {
	        currentPlayer = player1;
	        otherPlayer = computerPlayer;
	    }

	    boolean goCalled = false;
	    // game loop
	    while (!player1.isHandEmpty() || !computerPlayer.isHandEmpty()) {
	        Hand currentHand = currentPlayer.getHand();
	        List<Card> playableCards = currentHand.getPlayableCards(runningTotal);
	        // play until the player runs out of cards
	        if (!playableCards.isEmpty()) {
	            Card play;

	            if (currentPlayer == player1) {
	                // player chooses card
	                System.out.println("Your hand: " + getHand("Player 1"));
	                System.out.println("Playable cards:");
	                for (int i = 0; i < playableCards.size(); i++) {
	                    System.out.println(i + ": " + playableCards.get(i));
	                }

	                int selectedIndex = -1;
	                // input validation
	                while (true) {
	                    System.out.print("Enter the number of the card to play: ");
	                    String selection = input.nextLine().strip();
	                    try {
	                        selectedIndex = Integer.parseInt(selection);
	                        if (selectedIndex >= 0 && selectedIndex < playableCards.size()) {
	                            break;
	                        }
	                        else {
	                            System.out.println("Number out of range. Try again.");
	                        }
	                    }
	                    catch (NumberFormatException e) {
	                        System.out.println("Invalid input. Please enter a number.");
	                    }
	                }

	                play = playableCards.get(selectedIndex);
	            }
	            else {
	                // computer chooses the card depending on the Strategy
	                List<Card> playedCards = new ArrayList<Card>();
	                for (Card c : playingRun) {
	                    playedCards.add(c);
	                }
	                play = computer.choosePlay(runningTotal, playedCards);
	                System.out.println("Computer plays " + play);
	            }

	            currentHand.removeCard(play);
	            playingRun.push(play);
	            runningTotal += play.getValue();

	            int points = ruleEngine.scorePegging(playingRun);

	            pegboard.addPoints(currentPlayer, points);
				if (checkIfPlayerWon(currentPlayer, otherPlayer)) return true;
	            System.out.println(getPlayerName(currentPlayer) + " scores " + points + " point(s). Running total: " + runningTotal);
	            // last card point scoring
	            if (runningTotal == 31 || bothPlayersCantPlay(player1, computerPlayer)) {
	                if (runningTotal != 31) {
	                    pegboard.addPoints(currentPlayer, 1);
						if (checkIfPlayerWon(currentPlayer, otherPlayer)) return true;
	                    System.out.println(getPlayerName(currentPlayer) + " scores 1 point for last card.");
	                }

	                runningTotal = 0;
	                playingRun.clear();
	                goCalled = false;
	            }
	        }
	        // "Go" scoring
	        else {
	            if (goCalled) {
	                pegboard.addPoints(otherPlayer, 1);
					if (checkIfPlayerWon(currentPlayer, otherPlayer)) return true;
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
			if (checkIfPlayerWon(currentPlayer, otherPlayer)) return true;
	        else System.out.println(getPlayerName(currentPlayer) + " scores 1 point for final card.");
	    }
	    return false;
	}
	
    /* This method returns a player name string based on which player object is passed in
     * Arguments:
     *      player: a Player object
     * Returns:
     *      a String representing the player ("Player 1" or "Player 2")
     */
	private String getPlayerName(Player player) {
	    if (player == playerOne) {
	        return "Player 1";
	    }
	    else {
	        return "Player 2";
	    }
	}


	/* This method scores each player's hand using the starter card and also scores the crib
	 * for the dealer. Scores are added to the pegboard and displayed to the user.
	 * Returns true or false if a player wins through regularPlay
	 * Returns:
	 * 		true or false
	 */
	public boolean regularPlay() {
		int plrOnePts = playerOne.scoreHand(starter);
		System.out.println("Player 1 scores " + plrOnePts);
		int plrTwoPts = playerTwo.scoreHand(starter);
		System.out.println("Player 2 scores " + plrTwoPts);
		
		// add the scores to the pegboard
		pegboard.addPoints(playerOne, plrOnePts);
		pegboard.addPoints(playerTwo, plrTwoPts);
		if (checkIfPlayerWon(playerOne, playerTwo)) return true;
		
		System.out.println("Crib: " + getCrib());

		// the dealer scores the crib
		if (playerOne.isDealer()) {
			int cribPts = crib.score(starter);
			pegboard.addPoints(playerOne, cribPts);
			System.out.println("Player 1 scores " + cribPts + " from the crib");
			if (checkIfPlayerWon(playerOne, playerTwo)) return true;
		}
		else {
			int cribPts = crib.score(starter);
			pegboard.addPoints(playerTwo, cribPts);
			System.out.println("Player 2 scores " + cribPts + " from the crib");
			if (checkIfPlayerWon(playerOne, playerTwo)) return true;
		}
		return false;
	}

	
	/* This method checks whether both players have no playable cards remaining
	 * given the current running total.
	 * Arguments:
	 *      p1: Player 1
	 *      p2: Player 2
	 * Returns:
	 *      true if both players cannot play; false otherwise
	 */
	private boolean bothPlayersCantPlay(Player p1, Player p2) {
	    List<Card> playerOnePlayable = p1.getHand().getPlayableCards(runningTotal);
	    List<Card> playerTwoPlayable = p2.getHand().getPlayableCards(runningTotal);

	    return playerOnePlayable.isEmpty() && playerTwoPlayable.isEmpty();
	}
	
	/* This method resets the state of the game for a new round. It clears both players' hands,
	 * the crib, the starter, the running total, and the playing stack. It rebuilds and shuffles
	 * the deck and switches the dealer.
	 */
	public void reset() {
		playerOne.getHand().clear();
		playerTwo.getHand().clear();
		starter = null;
		runningTotal = 0;
		crib.clear();
		if (playingRun != null) playingRun.clear();
		deck.buildNewDeck();
		deck.shuffle();
		switchDealers();
	}


	/* This method switches the dealer role between player one and player two.
	 * It sets the previous dealer to false and the other player to true.
	 */
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
	/* This method checks whether either player has reached the winning score threshold.
	 * It uses the Pegboard's hasWon method to determine if a player has won the game.
	 * Returns:
	 *      a String declaring the winner ("Player 1 wins!" or "Player 2 wins!")
	 *      or null if no player has won yet
	 */
	public String checkWin() {
		if (pegboard.hasWon(playerOne)) return "Player 1 wins!";
		else if (pegboard.hasWon(playerTwo)) return "Player 2 wins!";
		return null;
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
			for (Card c : playerOne.getHand().getHand()) {
				hand += c.toString() +", ";
			}
		}
		else {
			for (Card c : playerTwo.getHand().getHand()) {
				hand += c.toString() +", ";
			}
		}
		if (hand.length() == 0) return "";
		return hand.substring(0,hand.length()-2);
	}
	// gets the current crib
	public String getCrib() {
		String cribString = "";
		for (Card c : crib.getHand()) {
			cribString += c.toString() +", ";
		}
		return cribString.substring(0, cribString.length()-2);
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
	
	public Player getPlayerTwo() {
		return playerTwo;
		
	}
}

