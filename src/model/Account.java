
package model ;

import java.io.Serializable ;
import java.util.HashMap ;

/**
 * Store a particular user's data
 */
public class Account implements Serializable {
	
	/**
	 * serialized UID
	 */
	private static final long				serialVersionUID	= 5337456579596935215L ;
	/**
	 * User's chosen username
	 */
	private String								username ;
	/**
	 * password to get into the account
	 */
	private String								password ;
	/**
	 * the number of wins the user has achieved, indexed by the number of guesses it took them (number = index+1)
	 */
	private int [ ]							winCounts			= new int [ 5 ] ;
	/**
	 * The number of wins the user has achieved organized by each given word
	 */
	private HashMap < String, int [ ] >	winCountsByWord	= new HashMap < String, int [ ] > ( ) ;
	/**
	 * The number of power ups the user has stored for use
	 */
	private HashMap < String, Integer > powerUps = new HashMap < String, Integer > ( ) ;
	
	/**
	 * @param username the username of the new account
	 * @param password the password for the new account
	 */
	public Account( String username, String password ) {
		this.username	= username ;
		this.password	= password ;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername ( ) { return username ; }
	
	/**
	 * @return the password
	 */
	public String getPassword ( ) { return password ; }
	
	/**
	 * @param newUsername the new username for this account
	 */
	public void setUsername ( String newUsername ) { this.username = newUsername ; }
	
	/**
	 * @param newPassword the new password for this account
	 */
	public void setPassword ( String newPassword ) { this.password = newPassword ; }
	
	/**
	 * Testing method to print data about the account to the console
	 */
	public void print ( ) {
		System.out.println ( "Username: " + this.username + "\nPassword: " + this.password ) ;
	}
	
	/**
	 * @return number of wins the user has with each guess number
	 */
	public int [ ] getWinCounts ( ) { return winCounts ; }
	
	/**
	 * @param word the word to get win counts for
	 * @return the number of wins the user has for a specific word with each guess number
	 */
	public int [ ] getWinCounts ( String word ) {
		return winCountsByWord.get ( word ) ;
	}
	
	/**
	 * record a user's win
	 * @param guesses the number of guesses it took to win
	 * @param word the word the user was guessing
	 */
	public void registerWin ( int guesses, String word ) {
		winCounts [ guesses - 1 ] += 1 ;
		if ( winCountsByWord.containsKey ( word ) ) {
			winCountsByWord.get ( word ) [ guesses - 1 ] += 1 ;
		} else {
			winCountsByWord.put ( word, new int [ 5 ] ) ;
			winCountsByWord.get ( word ) [ guesses - 1 ] += 1 ;
		}
		updatePowerUps();
	}
	
	/**
	 * checks if the win is a multiple of 5/10/20, and updates
	 * the associated power-up/hint if so.
	 */
	public void updatePowerUps ( ) {
		int wins = getTotalWins ( ) ;
		if ( wins % 20 == 0 ) {
			powerUps.merge("largeHint", 1, Integer::sum);
			System.out.println("Large powerup achieved.");
			System.out.println("Total large powerups:" + powerUps.get("largeHint"));
			return;
		} else if ( wins % 10 == 0 ) {
			powerUps.merge("mediumHint", 1, Integer::sum);
			System.out.println("Medium powerup achieved.");
			System.out.println("Total medium powerups:" + powerUps.get("mediumHint"));
			return;
		} else if ( wins % 5 == 0 ) {
			powerUps.merge("smallHint", 1, Integer::sum);
			System.out.println("Small powerup achieved.");
			System.out.println("Total small powerups:" + powerUps.get("smallHint"));
			return;
		}
	}
	
	/**
	 * called when a powerup is used in the GameplayGUI. "spends" the associated powerup in
	 * the account.
	 * 
	 * @param powerupStr the strength of the powerup being used/decremented
	 */
	public void spendPowerup(String powerupStr) {
		String str = "";
		if (powerupStr.equals("Small Powerup")) {
			str = "smallHint";
		} else if (powerupStr.equals("Medium Powerup")) {
			str = "mediumHint";
		} else if (powerupStr.equals("Large Powerup")) {
			str = "largeHint";
		}
		if (powerUps.containsKey(str) && powerUps.get(str) > 0) {
			powerUps.merge(str, -1, Integer::sum);
			if (powerUps.get(str) <= 0) {
		        powerUps.remove(str);
		        System.out.println("Out of " + powerupStr + "s.");
		    } else {
		        System.out.println("Used " + powerupStr + ": " + powerUps.get(str) + " remaining.");
		    }
	    }
	}
	
	/**
	 * getter for the powerups associated with the active account
	 * @return this.powerUps hashmap of the powerups associated with the active/current account
	 */
	public HashMap<String, Integer> getPowerups() {
		return this.powerUps;
	}
	
	/**
	 * loops through winCounts to manually sum the number of wins at each guess interval
	 * 
	 * @return wins nummber of wins associated with the account
	 */
	public int getTotalWins ( ) {
		int wins = 0;
		for ( int i = 0;  i < winCounts.length; i++) {
			wins += winCounts[i];
		}
		System.out.println("Total Wins for " + this.username + ": " + wins);
		return wins;
	}
	
}
