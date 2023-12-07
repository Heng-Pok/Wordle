
package model ;

import java.io.Serializable ;
import java.util.ArrayList ;

/**
 * list of account
 */
public class AccountCollection implements Serializable {
	
	/**
	 * Serialized UID
	 */
	private static final long		serialVersionUID	= 4552632623118814876L ;
	/**
	 * list of valid accounts
	 */
	private ArrayList < Account >	accountArrayList ;
	
	/**
	 * initializes the internal array
	 */
	public AccountCollection( ) {
		accountArrayList = new ArrayList <> ( ) ;
	}
	
	/**
	 * adds an account
	 * @param account the account to add
	 * @return whether the account was successfully added (will not add account with duplicate username)
	 */
	public boolean add ( Account account ) {
		if ( contains ( account.getUsername ( ) ) ) { return false ; }
		accountArrayList.add ( account ) ;
		return true ;
	}
	
	/**
	 * @return the internal list
	 */
	public ArrayList < Account > getAccountArrayList ( ) { return accountArrayList ; }
	
	/**
	 * get a specific account
	 * @param username the username of the account to find
	 * @param password the password of the account to find
	 * @return the account found or null if none was found
	 */
	public Account getAccount ( String username, String password ) {
		for ( Account currentAccount : accountArrayList ) {
			if ( currentAccount.getUsername ( ).equals ( username ) && currentAccount.getPassword ( ).equals ( password ) ) { return currentAccount ; }
		}
		return null ;
	}
	
	/**
	 * check if there is an account with the passed username
	 * @param username the username to check fpr
	 * @return whether there is an account with the passed username
	 */
	public boolean contains ( String username ) {
		for ( Account currentAccount : accountArrayList ) {
			if ( currentAccount.getUsername ( ).equals ( username ) ) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * check if there is an account with the passed username and password
	 * @param username username to check for
	 * @param password password to check for
	 * @return whether an appropriate account was found
	 */
	public boolean correctPassword ( String username, String password ) {
		for ( Account currentAccount : accountArrayList ) {
			if ( currentAccount.getUsername ( ).equals ( username ) && currentAccount.getPassword ( ).equals ( password ) ) { return true ; }
		}
		return false ;
	}
	
	/**
	 * testing method to print details on every account in the collection
	 */
	public void print ( ) {
		for ( Account account : accountArrayList ) {
			account.print ( ) ;
			System.out.println ( ) ;
		}
	}
	
}
