package view;

import java.util.Scanner;

import model.GameModel;

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
			} else if (input.equals("one")){
				onePlayer = true;
				System.out.println("One player mode chosen");
				break;
			} else {
				System.out.println("Invalid entry, please enter 'one' or 'two'");
				input = systemIn.nextLine().strip();
			}
		}
		
		if (onePlayer) {
			handleOnePlayer(systemIn, game);
		} else {
			handleTwoPlayers(systemIn, game);
		}
	}
	
	public static void handleOnePlayer(Scanner systemIn, GameModel game) {
		return;
	}
	
	public static void handleTwoPlayers(Scanner systemIn, GameModel game) {
		System.out.println("Assign Player 1 and Player 2, type anything to start");
		String input = systemIn.nextLine().strip();
		
		game.determineDealer();
		
		String dealer = game.getDealer();
		
		System.out.println(dealer + " is dealer");
		
		game.deal();
		
		System.out.println("Player 1 hand: " + game.getHand("Player 1"));
		
		System.out.println("Player 1 choose first card to discard (enter as seen, e.g. 'QUEEN DIAMONDS')");
		String discardOne = systemIn.nextLine().strip();
		game.discard("Player 1", discardOne);
		System.out.println("Player 1 hand: " + game.getHand("Player 1"));
		
		System.out.println("Player 1 choose second card to discard (enter as seen, e.g. 'QUEEN DIAMONDS')");
		String discardTwo = systemIn.nextLine().strip();
		game.discard("Player 1", discardTwo);
		System.out.println("Player 1 hand: " + game.getHand("Player 1"));
		System.out.println("");
		
		System.out.println("Player 2 hand: " + game.getHand("Player 2"));
		
		System.out.println("Player 2 choose first card to discard (enter as seen, e.g. 'QUEEN DIAMONDS')");
		discardOne = systemIn.nextLine().strip();
		game.discard("Player 2", discardOne);
		System.out.println("Player 2 hand: " + game.getHand("Player 2"));
		
		System.out.println("Player 2 choose second card to discard (enter as seen, e.g. 'QUEEN DIAMONDS')");
		discardTwo = systemIn.nextLine().strip();
		game.discard("Player 2", discardTwo);
		System.out.println("Player 2 hand: " + game.getHand("Player 2"));
		System.out.println("");
		
		System.out.println("Crib: " + game.getCrib());
		
		return;
	}
	
}
