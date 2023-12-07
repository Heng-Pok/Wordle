/*
 * Ethan Toledo
 */

package view_controller ;

import static model.UniversalConstants.* ;

import javafx.geometry.Pos ;
import javafx.scene.Scene ;
import javafx.scene.control.Button ;
import javafx.scene.control.Label ;
import javafx.scene.layout.BorderPane ;
import javafx.scene.layout.VBox ;
import javafx.stage.Stage ;
import model.AccountManager ;
import model.Game ;
import model.WordleApp ;

/**
 * The main menu for the WordleApp. Acts as a hub to launch the game, manage user accounts, and view tutorials and leaderboards.
 */
public class WordleAppGUI extends Stage {
	
	/*
	 * ''''''_____''''''_''''''''''''_'''''''''''''
	 * '''''|''__'\''''(_)''''''''''|'|''''''''''''
	 * '''''|'|__)'|'__'___'''____'_|'|_'___'''''''
	 * '''''|''___/''__|'\'\'/'/'_`'|'__/'_'\''''''
	 * '''''|'|'''|'|''|'|\'V'/'(_|'|'||''__/''''''
	 * ''__'|_|'''|_|''|_|'\_/'\__,_|\__\___|''''''
	 * '|''\/''|''''''''''''''|'|''''''''''''''''''
	 * '|'\''/'|'___'_'__'___'|'|__'''___'_'__'___'
	 * '|'|\/|'|/'_'\''_'`'_'\|''_'\'/'_'\''__/'__|
	 * '|'|''|'|''__/'|'|'|'|'|'|_)'|''__/'|''\__'\
	 * '|_|''|_|\___|_|'|_|'|_|_.__/'\___|_|''|___/
	 * ''''''''''''''''''''''''''''''''''''''''''''
	 * ''''''''''''''''''''''''''''''''''''''''''''
	 */
	
	private Scene			appScene ;
	private BorderPane	appPane ;
	private VBox			titleBar ;
	private VBox			buttonBox ;
	
	private Label	titleLabel ;
	private Label	welcomeLabel ;
	
	private Button	playButton ;
	private Button	zenButton ;
	private Button	tutorialButton ;
	private Button	leaderboardButton ;
	private Button	loginSignUpButton ;
	private Button	logoutButton ;
	private Button	manageAccountButton ;
	
	/*
	 * ''''''''_____''''''_''''''''''''_''''''''''''''
	 * '''''''|''__'\''''(_)''''''''''|'|'''''''''''''
	 * '''''''|'|__)'|'__'___'''____'_|'|_'___''''''''
	 * '''''''|''___/''__|'\'\'/'/'_`'|'__/'_'\'''''''
	 * '''''''|'|'''|'|''|'|\'V'/'(_|'|'||''__/'''''''
	 * '''____|_|'''|_|''|_|'\_/'\__,_|\__\___|'''''''
	 * ''/'____|''''''''''''''|'|''''''''''''|'|''''''
	 * '|'|'''''___''_'__''___|'|_'__'_'_'__'|'|_'___'
	 * '|'|''''/'_'\|''_'\/'__|'__/'_`'|''_'\|'__/'__|
	 * '|'|___|'(_)'|'|'|'\__'\'||'(_|'|'|'|'|'|_\__'\
	 * ''\_____\___/|_|'|_|___/\__\__,_|_|'|_|\__|___/
	 * '''''''''''''''''''''''''''''''''''''''''''''''
	 * '''''''''''''''''''''''''''''''''''''''''''''''
	 */
	
	private final String	TITLE_LABEL				= "HelloWordle!" ;
	private final String	PLAY_BUTTON				= "Play Game" ;
	private final String	ZEN_BUTTON				= "Zen Mode" ;
	private final String	TUTORIAL_BUTTON		= "How to Play" ;
	private final String	LEADERBOARD_BUTTON	= "Leaderboard" ;
	private final String	LOGIN_BUTTON			= "Login/Sign Up" ;
	private final String	LOGOUT_BUTTON			= "Logout" ;
	private final String	MANAGE_BUTTON			= "Manage Account" ;
	private final String	WELCOME_LABEL			= "Welcome!" ;
	private final String	WELCOME_USER_LABEL	= "Welcome %s!" ;
	
	/**
	 * Initializes the GUI for the main menu.
	 */
	public WordleAppGUI( ) {
		initializeMembers ( ) ;
		initializeStyles ( ) ;
		initializeLayout ( ) ;
		initializeHandlers ( ) ;
		this.setScene ( appScene ) ;
	}
	
	/*
	 * ''''''''''__''__''''''''''''''''_''''''''''''''''''''''''''
	 * '''''''''|''\/''|''''''''''''''|'|'''''''''''''''''''''''''
	 * '''''''''|'\''/'|'___'_'__'___'|'|__'''___'_'__''''''''''''
	 * '''''''''|'|\/|'|/'_'\''_'`'_'\|''_'\'/'_'\''__|'''''''''''
	 * '''''''''|'|''|'|''__/'|'|'|'|'|'|_)'|''__/'|''''''''''''''
	 * ''_____''|_|''|_|\___|_|'|_|_|_|_.__/'\___|_|_'''''''''''''
	 * '|_'''_|'''''(_)'|'(_)'''''|'(_)''''''''|'|'(_)''''''''''''
	 * '''|'|''_'__''_|'|_'_''__'_|'|_'______'_|'|_'_''___''_'__''
	 * '''|'|'|''_'\|'|'__|'|/'_`'|'|'|_''/'_`'|'__|'|/'_'\|''_'\'
	 * ''_|'|_|'|'|'|'|'|_|'|'(_|'|'|'|/'/'(_|'|'|_|'|'(_)'|'|'|'|
	 * '|_____|_|'|_|_|\__|_|\__,_|_|_/___\__,_|\__|_|\___/|_|'|_|
	 * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 */
	
	private void initializeMembers ( ) {
		initializeContainers ( ) ;
		initializeLabels ( ) ;
		initializeButtons ( ) ;
	}
	
	private void initializeContainers ( ) {
		appPane		= new BorderPane ( ) ;
		appScene		= new Scene ( appPane ) ;
		titleBar		= new VBox ( ) ;
		buttonBox	= new VBox ( ) ;
	}
	
	private void initializeLabels ( ) {
		titleLabel		= new Label ( TITLE_LABEL ) ;
		welcomeLabel	= new Label ( ) ;
	}
	
	private void initializeButtons ( ) {
		playButton				= new Button ( PLAY_BUTTON ) ;
		zenButton				= new Button ( ZEN_BUTTON ) ;
		tutorialButton			= new Button ( TUTORIAL_BUTTON ) ;
		leaderboardButton		= new Button ( LEADERBOARD_BUTTON ) ;
		loginSignUpButton		= new Button ( LOGIN_BUTTON ) ;
		logoutButton			= new Button ( LOGOUT_BUTTON ) ;
		manageAccountButton	= new Button ( MANAGE_BUTTON ) ;
	}
	
	/*
	 * ''''''''''''_____'_'''''''''_''''''''''''''''''''''''''''''
	 * '''''''''''/'____|'|'''''''|'|'''''''''''''''''''''''''''''
	 * ''''''''''|'(___'|'|_'_'''_|'|'___'''''''''''''''''''''''''
	 * '''''''''''\___'\|'__|'|'|'|'|/'_'\''''''''''''''''''''''''
	 * '''''''''''____)'|'|_|'|_|'|'|''__/''''''''''''''''''''''''
	 * ''_____'''|_____/'\__|\__,'|_|\___|''''''_'''_'''''''''''''
	 * '|_'''_|'''''(_)'|'(_)'__/'|'(_)''''''''|'|'(_)''''''''''''
	 * '''|'|''_'__''_|'|_'_'|___/|'|_'______'_|'|_'_''___''_'__''
	 * '''|'|'|''_'\|'|'__|'|/'_`'|'|'|_''/'_`'|'__|'|/'_'\|''_'\'
	 * ''_|'|_|'|'|'|'|'|_|'|'(_|'|'|'|/'/'(_|'|'|_|'|'(_)'|'|'|'|
	 * '|_____|_|'|_|_|\__|_|\__,_|_|_/___\__,_|\__|_|\___/|_|'|_|
	 * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 */
	
	private void initializeStyles ( ) {
		initializeContainerStyles ( ) ;
		initializeLabelStyles ( ) ;
		initializeButtonStyles ( ) ;
	}
	
	private void initializeContainerStyles ( ) {
		appScene.getStylesheets ( ).add ( GUI_CSS_FILENAME ) ;
		
		appPane.getStyleClass ( ).add ( PANE_STYLECLASS ) ;
		appPane.setPrefSize ( MED_PANE_WIDTH, MED_PANE_HEIGHT ) ;
		appPane.setPadding ( OUTER_PADDING ) ;
		
		titleBar.setAlignment ( Pos.CENTER ) ;
		titleBar.setSpacing ( SPACING_SMALL ) ;
		titleBar.setPadding ( INNER_PADDING ) ;
		BorderPane.setAlignment ( titleBar, Pos.TOP_CENTER ) ;
		
		buttonBox.setAlignment ( Pos.TOP_CENTER ) ;
		buttonBox.setSpacing ( SPACING_EXTRA ) ;
		buttonBox.setPadding ( INNER_PADDING ) ;
		BorderPane.setAlignment ( buttonBox, Pos.TOP_CENTER ) ;
	}
	
	private void initializeLabelStyles ( ) {
		titleLabel.getStyleClass ( ).add ( TITLE_STYLECLASS ) ;
		welcomeLabel.getStyleClass ( ).add ( HEADING_STYLECLASS ) ;
	}
	
	private void initializeButtonStyles ( ) {
		playButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		zenButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		tutorialButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		leaderboardButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		loginSignUpButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		logoutButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		manageAccountButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		
		playButton.setPrefWidth ( BIG_BUTTON_WIDTH ) ;
		zenButton.setPrefWidth ( BIG_BUTTON_WIDTH ) ;
		tutorialButton.setPrefWidth ( BIG_BUTTON_WIDTH ) ;
		leaderboardButton.setPrefWidth ( BIG_BUTTON_WIDTH ) ;
		loginSignUpButton.setPrefWidth ( BIG_BUTTON_WIDTH ) ;
		logoutButton.setPrefWidth ( BIG_BUTTON_WIDTH ) ;
		manageAccountButton.setPrefWidth ( BIG_BUTTON_WIDTH ) ;
	}
	
	/*
	 * '''''''''''_'''''''''''''''''''''''''''''_'''''''''''''''''
	 * ''''''''''|'|'''''''''''''''''''''''''''|'|''''''''''''''''
	 * ''''''''''|'|'''''__'_'_'''_''___''_'''_|'|_'''''''''''''''
	 * ''''''''''|'|''''/'_`'|'|'|'|/'_'\|'|'|'|'__|''''''''''''''
	 * ''''''''''|'|___|'(_|'|'|_|'|'(_)'|'|_|'|'|_'''''''''''''''
	 * ''''''''''|______\__,_|\__,'|\___/'\__,_|\__|''''''''''''''
	 * ''_____'''''''_'_'''_'''__/'|'_''''''''''_'''_'''''''''''''
	 * '|_'''_|'''''(_)'|'(_)'|___/'(_)''''''''|'|'(_)''''''''''''
	 * '''|'|''_'__''_|'|_'_''__'_|'|_'______'_|'|_'_''___''_'__''
	 * '''|'|'|''_'\|'|'__|'|/'_`'|'|'|_''/'_`'|'__|'|/'_'\|''_'\'
	 * ''_|'|_|'|'|'|'|'|_|'|'(_|'|'|'|/'/'(_|'|'|_|'|'(_)'|'|'|'|
	 * '|_____|_|'|_|_|\__|_|\__,_|_|_/___\__,_|\__|_|\___/|_|'|_|
	 * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 */
	
	private void initializeLayout ( ) {
		initializeTitleBar ( ) ;
		initializeButtonBox ( ) ;
		
		appPane.setTop ( titleBar ) ;
		appPane.setCenter ( buttonBox ) ;
	}
	
	private void initializeTitleBar ( ) {
		titleBar.getChildren ( ).addAll ( titleLabel, welcomeLabel ) ;
		setGreeting ( ) ;
	}
	
	private void setGreeting ( ) {
		if ( AccountManager.accountManager.getCurrentAccount ( ) != null ) {
			welcomeLabel.setText ( String.format ( WELCOME_USER_LABEL, AccountManager.accountManager.getCurrentAccount ( ).getUsername ( ) ) ) ;
		} else {
			welcomeLabel.setText ( WELCOME_LABEL ) ;
		}
	}
	
	private void initializeButtonBox ( ) {
		buttonBox.getChildren ( ).addAll ( playButton, zenButton, tutorialButton, leaderboardButton ) ;
		setLoginRelevantButtons ( ) ;
	}
	
	private void setLoginRelevantButtons ( ) {
		if ( AccountManager.accountManager.getCurrentAccount ( ) != null ) {
			buttonBox.getChildren ( ).addAll ( logoutButton, manageAccountButton ) ;
		} else {
			buttonBox.getChildren ( ).add ( loginSignUpButton ) ;
		}
	}
	
	/*
	 * '''''''''_''''_'''''''''''''''''_'_''''''''''''''''''''''''
	 * ''''''''|'|''|'|'''''''''''''''|'|'|'''''''''''''''''''''''
	 * ''''''''|'|__|'|'__'_'_'__'''__|'|'|'___'_'__''''''''''''''
	 * ''''''''|''__''|/'_`'|''_'\'/'_`'|'|/'_'\''__|'''''''''''''
	 * ''''''''|'|''|'|'(_|'|'|'|'|'(_|'|'|''__/'|''''''''''''''''
	 * ''_____'|_|''|_|\__,_|_|'|_|\__,_|_|\___|_|''_'''''''''''''
	 * '|_'''_|'''''(_)'|'(_)'''''|'(_)''''''''|'|'(_)''''''''''''
	 * '''|'|''_'__''_|'|_'_''__'_|'|_'______'_|'|_'_''___''_'__''
	 * '''|'|'|''_'\|'|'__|'|/'_`'|'|'|_''/'_`'|'__|'|/'_'\|''_'\'
	 * ''_|'|_|'|'|'|'|'|_|'|'(_|'|'|'|/'/'(_|'|'|_|'|'(_)'|'|'|'|
	 * '|_____|_|'|_|_|\__|_|\__,_|_|_/___\__,_|\__|_|\___/|_|'|_|
	 * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 * '''''''''''''''''''''''''''''''''''''''''''''''''''''''''''
	 */
	
	private void initializeHandlers ( ) {
		playButton.setOnAction ( event -> launchGame ( ) ) ;
		zenButton.setOnAction ( event -> launchZenGame ( ) ) ;
		tutorialButton.setOnAction ( event -> launchTutorial ( ) ) ;
		leaderboardButton.setOnAction ( event -> launchLeaderboard ( ) ) ;
		loginSignUpButton.setOnAction ( event -> launchLoginSignUp ( ) ) ;
		logoutButton.setOnAction ( event -> launchLogout ( ) ) ;
		manageAccountButton.setOnAction ( event -> launchManageAccount ( ) ) ;
	}
	
	/*
	 * ''______''''''''''''''''_'''_'''''''''''''''''
	 * '|''____|''''''''''''''|'|'(_)''''''''''''''''
	 * '|'|__'_'''_'_'__'''___|'|_'_''___''_'__''___'
	 * '|''__|'|'|'|''_'\'/'__|'__|'|/'_'\|''_'\/'__|
	 * '|'|''|'|_|'|'|'|'|'(__|'|_|'|'(_)'|'|'|'\__'\
	 * '|_|'''\__,_|_|'|_|\___|\__|_|\___/|_|'|_|___/
	 * ''''''''''''''''''''''''''''''''''''''''''''''
	 * ''''''''''''''''''''''''''''''''''''''''''''''
	 */
	
	private void launchGame ( ) {
		Game			game		= new Game ( ) ;
		GameplayGUI	gameGUI	= new GameplayGUI ( game ) ;
		WordleApp.app.launchStage ( gameGUI ) ;
	}
	
	private void launchZenGame ( ) {
		Game			zenGame	= new Game ( true ) ;
		GameplayGUI	gameGUI	= new GameplayGUI ( zenGame ) ;
		WordleApp.app.launchStage ( gameGUI ) ;
	}
	
	private void launchTutorial ( ) {
		InstructionsPane instructions = new InstructionsPane ( ) ;
		WordleApp.app.launchPane ( instructions, 425, 500 ) ;
	}
	
	private void launchLeaderboard ( ) {
		LeaderboardPane leaderboard = new LeaderboardPane ( ) ;
		WordleApp.app.launchPane ( leaderboard, 350, 500 ) ;
	}
	
	private void launchLoginSignUp ( ) {
		LoginSignUpPane loginSignUpPane = new LoginSignUpPane ( ) ;
		WordleApp.app.launchStage ( loginSignUpPane ) ;
	}
	
	private void launchLogout ( ) {
		AccountManager.accountManager.logout ( ) ;
		reloadGui ( ) ;
	}
	
	private void launchManageAccount ( ) {
		ManageAccountPane manageAccountPane = new ManageAccountPane ( ) ;
		WordleApp.app.launchStage ( manageAccountPane ) ;
	}
	
	/**
	 * Refreshes the GUI, changes the buttons depending on whether a user is logged in.
	 */
	public void reloadGui ( ) {
		titleBar.getChildren ( ).clear ( ) ;
		buttonBox.getChildren ( ).clear ( ) ;
		initializeTitleBar ( ) ;
		initializeButtonBox ( ) ;
	}
	
}
