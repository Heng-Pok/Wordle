package tests;

import java.util.Scanner;

import model.Game;

/**
 * test class to test game in console
 * 
 * @author Charles Scott
 */
public class ConsoleGame {
	/**
	 * initializes a new game in console
	 * @param args
	 */
	public static void main(String[] args) {
		Game game = new Game();
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
