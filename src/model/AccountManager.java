
package model ;

import java.io.FileInputStream ;
import java.io.FileNotFoundException ;
import java.io.FileOutputStream ;
import java.io.IOException ;
import java.io.ObjectInputStream ;
import java.io.ObjectOutputStream ;
import java.util.HashMap;

/**
 * Singleton to manage all saved accounts
 */
public class AccountManager {
	
	/**
	 * Singleton reference to the instance of the manager
	 */
	public static AccountManager accountManager ;
	
	/**
	 * internal list of accounts
	 */
	private AccountCollection accountCollection ;
	
	/**
	 * the account currently logged in
	 */
	private Account currentAccount ;
	
	/**
	 * the global leaderboard
	 */
	private GlobalLeaderboard globLead ;
	
	/**
	 * name of the account collection serialized file
	 */
	private final String	ACCOUNT_COLLECTION_FILE_NAME	= "AccountCollection.ser" ;
	/**
	 * name of the global leaderboard serialized file
	 */
	private final String	LEADERBOARD_FILE_NAME			= "GlobalLeaderboard.ser" ;
	
	/**
	 * initialize the account manager
	 */
	public AccountManager( ) {
		accountManager = this ;
		loadLeaderboard ( ) ;
		loadAccounts ( ) ;
		if ( accountCollection == null ) accountCollection = new AccountCollection ( ) ;
		if ( globLead == null ) globLead = new GlobalLeaderboard ( ) ;
	}
	
	/**
	 * save the accounts and leaderboards
	 */
	public void save ( ) {
		saveAccounts ( ) ;
		saveLeaderboard ( ) ;
	}
	
	/**
	 * @return the currently logged in account
	 */
	public Account getCurrentAccount ( ) { return currentAccount ; }
	
	/**
	 * @param account account to add
	 * @return whether the account was added successfully
	 */
	public boolean add ( Account account ) {
		return accountCollection.add ( account ) ;
	}
	
	/**
	 * @param username username to check for
	 * @return whether an account with the passed username exists
	 */
	public boolean contains ( String username ) {
		return accountCollection.contains ( username ) ;
	}
	
	/**
	 * @param username username to check for
	 * @param password password to check for
	 * @return whether there is an account matching the passed username and password
	 */
	public boolean correctPassword ( String username, String password ) {
		return accountCollection.correctPassword ( username, password ) ;
	}
	
	/**
	 * @param username username of the account to log in to
	 * @param password password of the account to log in to
	 * @return whether or not the user was successfully logged in
	 */
	public boolean login ( String username, String password ) {
		Account temp = accountCollection.getAccount ( username, password ) ;
		if ( temp == null ) return false ;
		currentAccount = temp ;
		return true ;
	}
	
	/**
	 * log the currently active user out
	 */
	public void logout ( ) {
		currentAccount = null ;
	}
	
	/**
	 * @return the current global guess counts
	 */
	public int [ ] getGlobalScore ( ) { return globLead.getWinCounts ( ) ; }
	
	/**
	 * @param word the word to get guess counts for
	 * @return the current global guess counts for the passed word
	 */
	public int [ ] getGlobalScore ( String word ) {
		return globLead.getWinCounts ( word ) ;
	}
	
	/**
	 * @return the overall guess counts for the current account
	 */
	public int [ ] getCurrentScore ( ) {
		if ( currentAccount == null ) return null ;
		return currentAccount.getWinCounts ( ) ;
	}
	
	/**
	 * @param word the word to get guess counts for
	 * @return the guess counts for the passed word for the user currently logged in
	 */
	public int [ ] getCurrentScore ( String word ) {
		if ( currentAccount == null ) return null ;
		return currentAccount.getWinCounts ( word ) ;
	}
	
	/**
	 * getter for the powerups associated with the currentAccount
	 * 
	 * @param score a score the user achieved in a game
	 * @param word  the target word for that game
	 */
	public HashMap<String, Integer> getPowerups ( ) {
		return currentAccount.getPowerups();
	}
	
	/**
	 * uses a powerup on the current account
	 * 
	 * @param powerupStr the stregnth of the powerup being used
	 */
	public void usePowerup(String powerupStr) {
		currentAccount.spendPowerup(powerupStr);
	}
	
	/**
	 * records a win for the current user
	 * 
	 * @param score a score the user achieved in a game
	 * @param word  the target word for that game
	 */
	public void addScore ( int score, String word ) {
		currentAccount.registerWin ( score, word ) ;
		globLead.registerWin ( score, word ) ;
	}
	
	/**
	 * saves the account collection
	 */
	private void saveAccounts ( ) {
		try {
			FileOutputStream		fileStream	= new FileOutputStream ( ACCOUNT_COLLECTION_FILE_NAME ) ;
			ObjectOutputStream	outFile		= new ObjectOutputStream ( fileStream ) ;
			outFile.writeObject ( accountCollection ) ;
			outFile.close ( ) ;
		} catch ( IOException e ) {
			e.printStackTrace ( ) ;
			System.exit ( 1 ) ;
		}
	}
	
	/**
	 * loads all saved accounts
	 */
	private void loadAccounts ( ) {
		try {
			FileInputStream	fileStream	= new FileInputStream ( ACCOUNT_COLLECTION_FILE_NAME ) ;
			ObjectInputStream	readIn		= new ObjectInputStream ( fileStream ) ;
			accountCollection = ( AccountCollection ) readIn.readObject ( ) ;
			readIn.close ( ) ;
		} catch ( FileNotFoundException e ) {} catch ( IOException e ) {
			e.printStackTrace ( ) ;
			System.exit ( 1 ) ;
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace ( ) ;
			System.exit ( 1 ) ;
		}
	}
	
	/**
	 * saves the global leaderboard
	 */
	private void saveLeaderboard ( ) {
		try {
			FileOutputStream		fileStream	= new FileOutputStream ( LEADERBOARD_FILE_NAME ) ;
			ObjectOutputStream	outFile		= new ObjectOutputStream ( fileStream ) ;
			outFile.writeObject ( globLead ) ;
			outFile.close ( ) ;
		} catch ( IOException e ) {
			e.printStackTrace ( ) ;
			System.exit ( 1 ) ;
		}
	}
	
	/**
	 * loads the global leaderboard from file
	 */
	private void loadLeaderboard ( ) {
		try {
			FileInputStream	fileStream	= new FileInputStream ( LEADERBOARD_FILE_NAME ) ;
			ObjectInputStream	readIn		= new ObjectInputStream ( fileStream ) ;
			globLead = ( GlobalLeaderboard ) readIn.readObject ( ) ;
			readIn.close ( ) ;
		} catch ( FileNotFoundException e ) {} catch ( IOException e ) {
			e.printStackTrace ( ) ;
			System.exit ( 1 ) ;
		} catch ( ClassNotFoundException e ) {
			e.printStackTrace ( ) ;
			System.exit ( 1 ) ;
		}
	}
	
}
