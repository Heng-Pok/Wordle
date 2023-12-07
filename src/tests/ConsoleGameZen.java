package tests;

import java.util.Scanner;

import model.Game;

/**
 * test class to test the zen functionality of the game class through the console. 
 * users should be alloted infinite guesses.
 * 
 * @author Parker Hines
 */
public class ConsoleGameZen {
	/**
	 * driver : initializes a new game with the "true" flag, indicating to start in zen mode
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game(true);
		Scanner reader = new Scanner(System.in);
		while(game.eligible())
		{
			char[] result = game.guess(reader.nextLine());
			if(result == null)
			{
				System.out.println("Invalid word");
			}
			else
			{
				for(int i = 0; i<result.length; i++)
				{
					System.out.print(result[i]+" ");
				}
				System.out.println();
				if(game.won)
					break;
			}
		}
		if(game.won)
		{
			System.out.println("You win!");
			System.out.println("Score: "+game.getScore());
		}
		else
			System.out.println("You lose :(");
		System.out.println("Word was: "+game.getWord());
		reader.close();
	}

}
