
package view_controller ;

import static model.UniversalConstants.* ;

import javafx.geometry.Pos ;
import javafx.scene.Scene ;
import javafx.scene.control.Alert ;
import javafx.scene.control.Button ;
import javafx.scene.control.Label ;
import javafx.scene.control.PasswordField ;
import javafx.scene.control.TextField ;
import javafx.scene.control.Alert.AlertType ;
import javafx.scene.layout.BorderPane ;
import javafx.scene.layout.HBox ;
import javafx.scene.layout.VBox ;
import javafx.stage.Stage ;
import model.Account ;
import model.AccountManager ;
import model.WordleApp ;

/**
 * An instance of the pane used to create a new account or login to an existing one.
 */
public class LoginSignUpPane extends Stage {
	
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
	
	private Scene			scene ;
	private BorderPane	pane ;
	private VBox			signUpDisplay ;
	private VBox			loginDisplay ;
	private HBox			signUpSubmitCancelBox ;
	private HBox			loginSubmitCancelBox ;
	
	private Label	titleLabel ;
	private Label	signUpPromptLabel ;
	private Label	loginPromptLabel ;
	private Label	signUpSwitchLabel ;
	private Label	loginSwitchLabel ;
	
	private TextField			signUpNameField ;
	private TextField			loginNameField ;
	private PasswordField	signUpPassField ;
	private PasswordField	signUpPassConfirmField ;
	private PasswordField	loginPassField ;
	
	private Button	signUpSubmitButton ;
	private Button	signUpCancelButton ;
	private Button	signUpSwitchButton ;
	private Button	loginSubmitButton ;
	private Button	loginCancelButton ;
	private Button	loginSwitchButton ;
	
	private Alert	blankNameAlert ;
	private Alert	blankPassAlert ;
	private Alert	nameTakenAlert ;
	private Alert	passNotMatchingAlert ;
	private Alert	signUpSuccessAlert ;
	private Alert	loginSuccessAlert ;
	private Alert	loginErrorAlert ;
	
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
	
	private final String	LOGIN_TITLE_LABEL						= "Login" ;
	private final String	SIGNUP_TITLE_LABEL					= "Create Account" ;
	private final String	LOGIN_PROMPT_LABEL					= "Enter your username and password to log in." ;
	private final String	SIGNUP_PROMPT_LABEL					= "Enter a username and password to sign up." ;
	private final String	LOGIN_SWITCH_PROMPT					= "Need to create an account?" ;
	private final String	SIGNUP_SWITCH_PROMPT					= "Already have an account?" ;
	private final String	NAME_FIELD_PROMPT						= "Enter Username" ;
	private final String	PASS_FIELD_PROMPT						= "Enter Password" ;
	private final String	CONFIRM_PASS_FIELD_PROMPT			= "Confirm Password" ;
	private final String	SIGNUP_BUTTON							= "Sign Up" ;
	private final String	LOGIN_BUTTON							= "Log In" ;
	private final String	SUBMIT_BUTTON							= "Submit" ;
	private final String	CANCEL_BUTTON							= "Cancel" ;
	private final String	BLANK_NAME_ALERT_HEADER				= "Username cannot be blank." ;
	private final String	BLANK_PASS_ALERT_HEADER				= "Password cannot be blank." ;
	private final String	NAME_TAKEN_ALERT_HEADER				= "This username is already taken." ;
	private final String	NAME_TAKEN_ALERT_CONTEXT			= "If you believe this is you, try logging in." ;
	private final String	PASS_NOT_MATCHING_ALERT_HEADER	= "Your passwords do not match." ;
	private final String	SIGNUP_SUCCESS_ALERT_HEADER		= "You have successfully created an account!" ;
	private final String	LOGIN_SUCCESS_ALERT_HEADER			= "You have successfully logged in!" ;
	private final String	LOGIN_ERROR_ALERT_HEADER			= "Unable to login with this information." ;
	private final String	LOGIN_ERROR_ALERT_CONTEXT
																		   = "Check your username and password and try again. If you need to create an account, hit the 'Sign Up' button." ;
	
	/**
	 * Initializes an instance of the login/signup pane.
	 */
	public LoginSignUpPane( ) {
		initializeMembers ( ) ;
		initializeStyles ( ) ;
		initializeLayout ( ) ;
		initializeHandlers ( ) ;
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
		initializeFields ( ) ;
		initializeButtons ( ) ;
		initializeAlerts ( ) ;
	}
	
	private void initializeContainers ( ) {
		pane							= new BorderPane ( ) ;
		signUpDisplay				= new VBox ( ) ;
		loginDisplay				= new VBox ( ) ;
		signUpSubmitCancelBox	= new HBox ( ) ;
		loginSubmitCancelBox		= new HBox ( ) ;
	}
	
	private void initializeLabels ( ) {
		titleLabel			= new Label ( LOGIN_TITLE_LABEL ) ;
		signUpPromptLabel	= new Label ( SIGNUP_PROMPT_LABEL ) ;
		loginPromptLabel	= new Label ( LOGIN_PROMPT_LABEL ) ;
		signUpSwitchLabel	= new Label ( SIGNUP_SWITCH_PROMPT ) ;
		loginSwitchLabel	= new Label ( LOGIN_SWITCH_PROMPT ) ;
	}
	
	private void initializeFields ( ) {
		signUpNameField			= new TextField ( ) ;
		loginNameField				= new TextField ( ) ;
		signUpPassField			= new PasswordField ( ) ;
		signUpPassConfirmField	= new PasswordField ( ) ;
		loginPassField				= new PasswordField ( ) ;
	}
	
	private void initializeButtons ( ) {
		signUpSubmitButton	= new Button ( SUBMIT_BUTTON ) ;
		signUpCancelButton	= new Button ( CANCEL_BUTTON ) ;
		signUpSwitchButton	= new Button ( LOGIN_BUTTON ) ;
		loginSubmitButton		= new Button ( SUBMIT_BUTTON ) ;
		loginCancelButton		= new Button ( CANCEL_BUTTON ) ;
		loginSwitchButton		= new Button ( SIGNUP_BUTTON ) ;
		
		loginSubmitButton.setDefaultButton ( true ) ;
	}
	
	private void initializeAlerts ( ) {
		blankNameAlert			= new Alert ( AlertType.WARNING ) ;
		blankPassAlert			= new Alert ( AlertType.WARNING ) ;
		nameTakenAlert			= new Alert ( AlertType.WARNING ) ;
		passNotMatchingAlert	= new Alert ( AlertType.WARNING ) ;
		signUpSuccessAlert	= new Alert ( AlertType.INFORMATION ) ;
		loginSuccessAlert		= new Alert ( AlertType.INFORMATION ) ;
		loginErrorAlert		= new Alert ( AlertType.ERROR ) ;
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
		initializeFieldStyles ( ) ;
		initializeButtonStyles ( ) ;
		initializeAlertStyles ( ) ;
	}
	
	private void initializeContainerStyles ( ) {
		pane.getStylesheets ( ).add ( GUI_CSS_FILENAME ) ;
		pane.getStyleClass ( ).add ( PANE_STYLECLASS ) ;
		
		BorderPane.setAlignment ( titleLabel, Pos.TOP_CENTER ) ;
		BorderPane.setAlignment ( signUpDisplay, Pos.CENTER ) ;
		BorderPane.setAlignment ( loginDisplay, Pos.CENTER ) ;
		
		signUpDisplay.setAlignment ( Pos.CENTER ) ;
		loginDisplay.setAlignment ( Pos.CENTER ) ;
		signUpSubmitCancelBox.setAlignment ( Pos.CENTER ) ;
		loginSubmitCancelBox.setAlignment ( Pos.CENTER ) ;
		
		pane.setPadding ( OUTER_PADDING ) ;
		signUpDisplay.setPadding ( INNER_PADDING ) ;
		loginDisplay.setPadding ( INNER_PADDING ) ;
		signUpSubmitCancelBox.setPadding ( INNER_PADDING ) ;
		loginSubmitCancelBox.setPadding ( INNER_PADDING ) ;
		
		signUpDisplay.setSpacing ( SPACING ) ;
		loginDisplay.setSpacing ( SPACING ) ;
		signUpSubmitCancelBox.setSpacing ( SPACING ) ;
		loginSubmitCancelBox.setSpacing ( SPACING ) ;
	}
	
	private void initializeLabelStyles ( ) {
		titleLabel.getStyleClass ( ).add ( TITLE_STYLECLASS ) ;
		signUpPromptLabel.getStyleClass ( ).add ( HEADING_STYLECLASS ) ;
		loginPromptLabel.getStyleClass ( ).add ( HEADING_STYLECLASS ) ;
		signUpSwitchLabel.getStyleClass ( ).add ( TEXT_STYLECLASS ) ;
		loginSwitchLabel.getStyleClass ( ).add ( TEXT_STYLECLASS ) ;
	}
	
	private void initializeFieldStyles ( ) {
		signUpNameField.setPromptText ( NAME_FIELD_PROMPT ) ;
		signUpNameField.setPrefWidth ( FIELD_WIDTH ) ;
		signUpNameField.setMaxWidth ( FIELD_WIDTH ) ;
		signUpNameField.setMinWidth ( FIELD_WIDTH ) ;
		
		loginNameField.setPromptText ( NAME_FIELD_PROMPT ) ;
		loginNameField.setPrefWidth ( FIELD_WIDTH ) ;
		loginNameField.setMaxWidth ( FIELD_WIDTH ) ;
		loginNameField.setMinWidth ( FIELD_WIDTH ) ;
		
		signUpPassField.setPromptText ( PASS_FIELD_PROMPT ) ;
		signUpPassField.setPrefWidth ( FIELD_WIDTH ) ;
		signUpPassField.setMaxWidth ( FIELD_WIDTH ) ;
		signUpPassField.setMinWidth ( FIELD_WIDTH ) ;
		
		signUpPassConfirmField.setPromptText ( CONFIRM_PASS_FIELD_PROMPT ) ;
		signUpPassConfirmField.setPrefWidth ( FIELD_WIDTH ) ;
		signUpPassConfirmField.setMaxWidth ( FIELD_WIDTH ) ;
		signUpPassConfirmField.setMinWidth ( FIELD_WIDTH ) ;
		
		loginPassField.setPromptText ( PASS_FIELD_PROMPT ) ;
		loginPassField.setPrefWidth ( FIELD_WIDTH ) ;
		loginPassField.setMaxWidth ( FIELD_WIDTH ) ;
		loginPassField.setMinWidth ( FIELD_WIDTH ) ;
	}
	
	private void initializeButtonStyles ( ) {
		signUpSubmitButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		signUpSubmitButton.setPrefWidth ( SMALL_BUTTON_WIDTH ) ;
		signUpSubmitButton.setMaxWidth ( SMALL_BUTTON_WIDTH ) ;
		signUpSubmitButton.setMinWidth ( SMALL_BUTTON_WIDTH ) ;
		
		signUpCancelButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		signUpCancelButton.setPrefWidth ( SMALL_BUTTON_WIDTH ) ;
		signUpCancelButton.setMaxWidth ( SMALL_BUTTON_WIDTH ) ;
		signUpCancelButton.setMinWidth ( SMALL_BUTTON_WIDTH ) ;
		
		signUpSwitchButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		signUpSwitchButton.setPrefWidth ( SMALL_BUTTON_WIDTH ) ;
		signUpSwitchButton.setMaxWidth ( SMALL_BUTTON_WIDTH ) ;
		signUpSwitchButton.setMinWidth ( SMALL_BUTTON_WIDTH ) ;
		
		loginSubmitButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		loginSubmitButton.setPrefWidth ( SMALL_BUTTON_WIDTH ) ;
		loginSubmitButton.setMaxWidth ( SMALL_BUTTON_WIDTH ) ;
		loginSubmitButton.setMinWidth ( SMALL_BUTTON_WIDTH ) ;
		
		loginCancelButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		loginCancelButton.setPrefWidth ( SMALL_BUTTON_WIDTH ) ;
		loginCancelButton.setMaxWidth ( SMALL_BUTTON_WIDTH ) ;
		loginCancelButton.setMinWidth ( SMALL_BUTTON_WIDTH ) ;
		
		loginSwitchButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		loginSwitchButton.setPrefWidth ( SMALL_BUTTON_WIDTH ) ;
		loginSwitchButton.setMaxWidth ( SMALL_BUTTON_WIDTH ) ;
		loginSwitchButton.setMinWidth ( SMALL_BUTTON_WIDTH ) ;
	}
	
	private void initializeAlertStyles ( ) {
		blankNameAlert.setHeaderText ( BLANK_NAME_ALERT_HEADER ) ;
		blankPassAlert.setHeaderText ( BLANK_PASS_ALERT_HEADER ) ;
		nameTakenAlert.setHeaderText ( NAME_TAKEN_ALERT_HEADER ) ;
		nameTakenAlert.setContentText ( NAME_TAKEN_ALERT_CONTEXT ) ;
		passNotMatchingAlert.setHeaderText ( PASS_NOT_MATCHING_ALERT_HEADER ) ;
		signUpSuccessAlert.setHeaderText ( SIGNUP_SUCCESS_ALERT_HEADER ) ;
		loginSuccessAlert.setHeaderText ( LOGIN_SUCCESS_ALERT_HEADER ) ;
		loginErrorAlert.setHeaderText ( LOGIN_ERROR_ALERT_HEADER ) ;
		loginErrorAlert.setContentText ( LOGIN_ERROR_ALERT_CONTEXT ) ;
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
		initializeSignUpDisplay ( ) ;
		initializeLoginDisplay ( ) ;
		initializePane ( ) ;
		scene = new Scene ( pane, SMALL_PANE_WIDTH, SMALL_PANE_HEIGHT ) ;
		this.setScene ( scene ) ;
	}
	
	private void initializeSignUpDisplay ( ) {
		signUpSubmitCancelBox.getChildren ( ).addAll ( signUpSubmitButton, signUpCancelButton ) ;
		signUpDisplay
		   .getChildren ( )
		   .addAll ( signUpNameField, signUpPassField, signUpPassConfirmField, signUpSubmitCancelBox, signUpSwitchLabel, signUpSwitchButton ) ;
	}
	
	private void initializeLoginDisplay ( ) {
		loginSubmitCancelBox.getChildren ( ).addAll ( loginSubmitButton, loginCancelButton ) ;
		loginDisplay.getChildren ( ).addAll ( loginNameField, loginPassField, loginSubmitCancelBox, loginSwitchLabel, loginSwitchButton ) ;
	}
	
	private void initializePane ( ) {
		pane.setTop ( titleLabel ) ;
		pane.setCenter ( loginDisplay ) ;
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
		signUpSubmitButton.setOnAction ( event -> signUp ( ) ) ;
		signUpCancelButton.setOnAction ( event -> returnToMain ( ) ) ;
		signUpSwitchButton.setOnAction ( event -> switchToLogin ( ) ) ;
		loginSubmitButton.setOnAction ( event -> login ( ) ) ;
		loginCancelButton.setOnAction ( event -> returnToMain ( ) ) ;
		loginSwitchButton.setOnAction ( event -> switchToSignUp ( ) ) ;
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
	
	private void signUp ( ) {
		String	name	= signUpNameField.getText ( ) ;
		String	pass	= signUpPassField.getText ( ) ;
		if ( name == "" || name == null ) {
			blankNameAlert.showAndWait ( ) ;
		} else if ( pass == "" || pass == null ) {
			blankPassAlert.showAndWait ( ) ;
		} else if ( AccountManager.accountManager.contains ( name ) ) {
			nameTakenAlert.showAndWait ( ) ;
		} else if ( !signUpPassConfirmField.getText ( ).equals ( pass ) ) {
			passNotMatchingAlert.showAndWait ( ) ;
		} else {
			Account newUser = new Account ( name, pass ) ;
			AccountManager.accountManager.add ( newUser ) ;
			AccountManager.accountManager.save ( ) ;
			AccountManager.accountManager.login ( name, pass ) ;
			signUpSuccessAlert.showAndWait ( ) ;
			returnToMain ( ) ;
		}
		signUpNameField.clear ( ) ;
		signUpPassField.clear ( ) ;
		signUpPassConfirmField.clear ( ) ;
		signUpNameField.requestFocus ( ) ;
	}
	
	private void login ( ) {
		String	name	= loginNameField.getText ( ) ;
		String	pass	= loginPassField.getText ( ) ;
		
		if ( AccountManager.accountManager.login ( name, pass ) ) {
			loginSuccessAlert.showAndWait ( ) ;
			returnToMain ( ) ;
		} else {
			loginErrorAlert.showAndWait ( ) ;
		}
		loginNameField.clear ( ) ;
		loginPassField.clear ( ) ;
	}
	
	private void switchToSignUp ( ) {
		loginSubmitButton.setDefaultButton ( false ) ;
		signUpSubmitButton.setDefaultButton ( true ) ;
		titleLabel.setText ( SIGNUP_TITLE_LABEL ) ;
		pane.setCenter ( signUpDisplay ) ;
	}
	
	private void switchToLogin ( ) {
		signUpSubmitButton.setDefaultButton ( false ) ;
		loginSubmitButton.setDefaultButton ( true ) ;
		titleLabel.setText ( LOGIN_TITLE_LABEL ) ;
		pane.setCenter ( loginDisplay ) ;
	}
	
	private void returnToMain ( ) {
		WordleApp.app.reloadMainMenu ( ) ;
		this.close ( ) ;
	}
	
}
