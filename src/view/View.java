package view;

import java.util.List;
import java.util.Scanner;

import model.Card;
import model.Computer;
import model.EasyStrategy;
import model.GameModel;
import model.Strategy;
import model.HardStrategy;

public class View {
	public static void main(String args[]) {
		Scanner systemIn = new Scanner(System.in);
		
		System.out.println("Welcome to Cribbage!");
		
		System.out.print("Starting game...  ");
		
		GameModel game = new GameModel();
		
		System.out.print("Success!\n");
		
		System.out.println("Two players or one? (enter 'two' or 'one')");
		
		boolean onePlayer;
		
		String input = systemIn.nextLine().strip();
		while (true) {
			if (input.equals("two")) {
				onePlayer = false;
				System.out.println("Two player mode chosen");
				break;
			}
			else if (input.equals("one")){
				onePlayer = true;
				System.out.println("One player mode chosen");
				break;
			}
			else {
				System.out.println("Invalid entry, please enter 'one' or 'two'");
				input = systemIn.nextLine().strip();
			}
		}
		
		if (onePlayer) {
			handleOnePlayer(systemIn, game);
		}
		else {
			handleTwoPlayers(systemIn, game);
		}
	}
	
	public static void handleOnePlayer(Scanner systemIn, GameModel game) {
	    System.out.println("Select difficulty (enter 'easy' or 'hard'):");
	    String difficulty = systemIn.nextLine().strip().toLowerCase();

	    Strategy strategy;
	    while (true) {
	        if (difficulty.equals("easy")) {
	            strategy = new EasyStrategy();
	            System.out.println("Easy mode selected.");
	            break;
	        }
	        else if (difficulty.equals("hard")) {
	            strategy = new HardStrategy();
	            System.out.println("hard mode selected.");
	            break;
	        }
	        else {
	            System.out.println("Invalid input. Please enter 'easy' or 'hard':");
	            difficulty = systemIn.nextLine().strip().toLowerCase();
	        }
	    }

	    Computer computer = new Computer(game.getPlayerTwo(), strategy);

	    System.out.println("Starting one player game. You are Player 1. Computer is Player 2.");
	    game.determineDealer();
	    
	    while (game.checkWin() == null) {
		    String dealer = game.getDealer();
		    System.out.println(dealer + " is dealer.");
		    
		    game.deal();
		    
		    // player discards
		    System.out.println("Player 1 hand: " + game.getHand("Player 1"));
		    System.out.println("Choose first card to discard (enter as seen, e.g. 'QUEEN DIAMONDS'):");
		    String discardOne = systemIn.nextLine().strip().toUpperCase();
		   
		    while (!game.getHand("Player 1").toString().toUpperCase().contains(discardOne)) {
		        System.out.println("Invalid card. Try again:");
		        discardOne = systemIn.nextLine().strip().toUpperCase();
		    }
		    game.discard("Player 1", discardOne);
	
		    System.out.println("Player 1 hand: " + game.getHand("Player 1"));
		    System.out.println("Choose second card to discard:");
		    String discardTwo = systemIn.nextLine().strip().toUpperCase();
		    
		    while (!game.getHand("Player 1").contains(discardTwo)) {
		        System.out.println("Invalid card. Try again:");
		        discardTwo = systemIn.nextLine().strip().toUpperCase();
		    }
		    game.discard("Player 1", discardTwo);
	
		    System.out.println("Player 1 hand: " + game.getHand("Player 1"));
	
		    // computer discarding
		    List<Card> compDiscards = computer.chooseDiscarded();
		    for (Card card : compDiscards) {
		        game.discard("Player 2", card.toString().toUpperCase());
		    }
	
		    System.out.println("Computer discarded 2 cards.");
	
		    System.out.println("Starting pegging play...");
	
		    game.onePlayerPeggingPlay(systemIn, computer);
		    // scores each players hand
		    game.regularPlay();
			System.out.println("Player 1 Score: " + game.getScore("player 1"));
			System.out.println("Player 2 Score: " + game.getScore("player 2"));
			System.out.println(game.getPegboard());
		    game.reset();
	
			System.out.println("NEW ROUND \n______________________________________________________________________________________"
					+ "__________________________________________________________________");
	    }
	    // print a message for the winner
		System.out.println(game.checkWin());
	}
	
	public static void handleTwoPlayers(Scanner systemIn, GameModel game) {
		System.out.println("Assign Player 1 and Player 2, type anything to start");
		String input = systemIn.nextLine().strip();

		game.determineDealer();
		
		while (game.checkWin() == null) {
			String dealer = game.getDealer();
			System.out.println(dealer + " is dealer");
	
			game.deal();
	
			System.out.println("Player 1 hand: " + game.getHand("Player 1"));
			System.out.println("Player 1 choose first card to discard (enter as seen, e.g. 'QUEEN DIAMONDS')");
			String discardOne = systemIn.nextLine().strip().toUpperCase();
			while (!game.getHand("Player 1").contains(discardOne)) {
				System.out.println("Invalid card. Try again:");
				discardOne = systemIn.nextLine().strip().toUpperCase();
			}
			game.discard("Player 1", discardOne);
			System.out.println("Player 1 hand: " + game.getHand("Player 1"));
	
			System.out.println("Player 1 choose second card to discard (enter as seen, e.g. 'QUEEN DIAMONDS')");
			String discardTwo = systemIn.nextLine().strip().toUpperCase();
			while (!game.getHand("Player 1").contains(discardTwo)) {
				System.out.println("Invalid card. Try again:");
				discardTwo = systemIn.nextLine().strip().toUpperCase();
			}
			game.discard("Player 1", discardTwo);
			System.out.println("Player 1 hand: " + game.getHand("Player 1"));
			System.out.println("");
	
			System.out.println("Player 2 hand: " + game.getHand("Player 2"));
			System.out.println("Player 2 choose first card to discard (enter as seen, e.g. 'QUEEN DIAMONDS')");
			discardOne = systemIn.nextLine().strip().toUpperCase();
			while (!game.getHand("Player 2").contains(discardOne)) {
				System.out.println("Invalid card. Try again:");
				discardOne = systemIn.nextLine().strip().toUpperCase();
			}
			game.discard("Player 2", discardOne);
			System.out.println("Player 2 hand: " + game.getHand("Player 2"));
	
			System.out.println("Player 2 choose second card to discard (enter as seen, e.g. 'QUEEN DIAMONDS')");
			discardTwo = systemIn.nextLine().strip().toUpperCase();
			while (!game.getHand("Player 2").contains(discardTwo)) {
				System.out.println("Invalid card. Try again:");
				discardTwo = systemIn.nextLine().strip().toUpperCase();
			}
			game.discard("Player 2", discardTwo);
			System.out.println("Player 2 hand: " + game.getHand("Player 2"));
			System.out.println("");
	
			System.out.println("Starting pegging play...");
			game.peggingPlay(systemIn);
			game.regularPlay();
			
			System.out.println("Player 1 Score: " + game.getScore("player 1"));
			System.out.println("Player 2 Score: " + game.getScore("player 2"));
			System.out.println(game.getPegboard());
			game.reset();
			System.out.println("NEW ROUND \n______________________________________________________________________________________"
					+ "__________________________________________________________________");
		}
		System.out.println(game.checkWin());
		return;
	}
	
}
