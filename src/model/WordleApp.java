/*
 * Ethan Toledo
 */

package model ;

import javafx.application.Application ;
import javafx.scene.Scene ;
import javafx.scene.layout.Pane ;
import javafx.stage.Stage ;
import view_controller.WordleAppGUI ;

/**
 * Launches GUI and contains account data.
 */
public class WordleApp extends Application {
	
	/**
	 * A static accessor for the WordleApp.
	 */
	public static WordleApp app ;
	
	private AccountManager	accounts	= new AccountManager ( ) ;
	private WordleAppGUI		gui ;
	
	/**
	 * @param args
	 */
	public static void main ( String [ ] args ) {
		launch ( args ) ;
	}
	
	/**
	 * Used to launch a pane as a separate window, reloading the main menu when it closes (depreciated, converting classes to use launchStage
	 * instead).
	 * 
	 * @param pane  The pane to be displayed.
	 * @param size1 The width of the pane.
	 * @param size2 The height of the pane.
	 */
	public void launchPane ( Pane pane, int size1, int size2 ) {
		Scene	scene	= new Scene ( pane, size1, size2 ) ;
		Stage	stage	= new Stage ( ) ;
		stage.setScene ( scene ) ;
		stage.setOnCloseRequest ( event -> reloadMainMenu ( ) ) ;
		
		stage.show ( ) ;
		gui.hide ( ) ;
	}
	
	/**
	 * Used to launch separate portions of the app, reloading the main menu when it closes.
	 * 
	 * @param stage The portion to be launched (game, login menu, etc.)
	 */
	public void launchStage ( Stage stage ) {
		stage.setOnCloseRequest ( event -> reloadMainMenu ( ) ) ;
		stage.show ( ) ;
		gui.hide ( ) ;
	}
	
	/**
	 * Refreshes the main menu and displays it.
	 */
	public void reloadMainMenu ( ) {
		gui.reloadGui ( ) ;
		gui.show ( ) ;
	}
	
	/**
	 * 
	 */
	@Override
	public void start ( Stage stage ) throws Exception {
		AccountManager.accountManager.add ( new Account ( "Admin", "Admin" ) ) ;
		
		app	= this ;
		gui	= new WordleAppGUI ( ) ;
		gui.setResizable ( false ) ;
		gui.show ( ) ;
	}
	
}
