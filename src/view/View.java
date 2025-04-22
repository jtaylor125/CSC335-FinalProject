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
			handleOnePlayer();
		} else {
			handleTwoPlayers();
		}
	}
	
	public static void handleOnePlayer() {
		return;
	}
	
	public static void handleTwoPlayers() {
		return;
	}
	
}
