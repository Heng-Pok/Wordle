
package view_controller ;

import static model.UniversalConstants.* ;

import javafx.geometry.Pos ;
import javafx.scene.Scene ;
import javafx.scene.control.Alert ;
import javafx.scene.control.Button ;
import javafx.scene.control.ButtonType ;
import javafx.scene.control.Label ;
import javafx.scene.control.PasswordField ;
import javafx.scene.control.TextField ;
import javafx.scene.control.Alert.AlertType ;
import javafx.scene.layout.BorderPane ;
import javafx.scene.layout.HBox ;
import javafx.scene.layout.VBox ;
import javafx.stage.Stage ;
import model.AccountManager ;
import model.WordleApp ;

/**
 * An instance of the pane used for account management, such as changing username or password (coming soon: view available powerups, delete
 * account).
 */
public class ManageAccountPane extends Stage {
	
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
	private VBox			titleDisplay ;
	private VBox			initialDisplay ;
	private VBox			changeNameDisplay ;
	private VBox			changePassDisplay ;
	private HBox			changeNameSubmitCancelBox ;
	private HBox			changePassSubmitCancelBox ;
	
	private Label	titleLabel ;
	private Label	welcomeLabel ;
	private Label	promptLabel ;
	private Label	changeNameLabel ;
	private Label	changePassLabel ;
	
	private TextField			changeNameField ;
	private PasswordField	changePassField ;
	private PasswordField	changePassConfirmField ;
	
	private Button	chooseChangeNameButton ;
	private Button	chooseChangePassButton ;
	private Button	cancelButton ;
	private Button	changeNameSubmitButton ;
	private Button	changeNameCancelButton ;
	private Button	changePassSubmitButton ;
	private Button	changePassCancelButton ;
	
	private Alert	blankNameAlert ;
	private Alert	currentNameAlert ;
	private Alert	nameTakenAlert ;
	private Alert	confirmNameChangeAlert ;
	private Alert	nameChangedSuccessAlert ;
	private Alert	blankPassAlert ;
	private Alert	passNotMatchAlert ;
	private Alert	confirmPassChangeAlert ;
	private Alert	passChangedSuccessAlert ;
	
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
	
	private final String	TITLE_LABEL							= "Account Manager" ;
	private final String	WELCOME_LABEL						= "Welcome %s!" ;
	private final String	PROMPT_LABEL						= "What would you like to do?" ;
	private final String	CHANGE_NAME_LABEL					= "Change your username." ;
	private final String	CHANGE_PASS_LABEL					= "Change your password." ;
	private final String	CHANGE_NAME_BUTTON				= "Change Username" ;
	private final String	CHANGE_PASS_BUTTON				= "Change Password" ;
	private final String	SUBMIT_BUTTON						= "Submit" ;
	private final String	CANCEL_BUTTON						= "Cancel" ;
	private final String	BLANK_NAME_ALERT_HEADER			= "Username can't be blank." ;
	private final String	CURRENT_NAME_ALERT_HEADER		= "This is already your name!" ;
	private final String	NAME_TAKEN_ALERT_HEADER			= "This name is already in use." ;
	private final String	CONFIRM_NAME_ALERT_HEADER		= "Are you sure you want to change your username?" ;
	private final String	NAME_CHANGED_ALERT_HEADER		= "You have successfully changed your username." ;
	private final String	BLANK_PASS_ALERT_HEADER			= "Password cannot be blank." ;
	private final String	PASS_NOT_MATCH_ALERT_HEADER	= "Passwords don't match." ;
	private final String	CONFIRM_PASS_ALERT_HEADER		= "Are you sure you want to change your password?" ;
	private final String	PASS_CHANGED_ALERT_HEADER		= "You have successfully changed your password." ;
	private final String	CHANGE_NAME_PROMPT				= "Enter new username" ;
	private final String	CHANGE_PASS_PROMPT				= "Enter new password" ;
	private final String	CHANGE_PASS_CONFIRM_PROMPT		= "Confirm new password" ;
	
	/**
	 * Initialize an instance of the account manager pane.
	 */
	public ManageAccountPane( ) {
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
		pane								= new BorderPane ( ) ;
		titleDisplay					= new VBox ( ) ;
		initialDisplay					= new VBox ( ) ;
		changeNameDisplay				= new VBox ( ) ;
		changePassDisplay				= new VBox ( ) ;
		changeNameSubmitCancelBox	= new HBox ( ) ;
		changePassSubmitCancelBox	= new HBox ( ) ;
	}
	
	private void initializeLabels ( ) {
		titleLabel			= new Label ( TITLE_LABEL ) ;
		welcomeLabel		= new Label ( String.format ( WELCOME_LABEL, AccountManager.accountManager.getCurrentAccount ( ).getUsername ( ) ) ) ;
		promptLabel			= new Label ( PROMPT_LABEL ) ;
		changeNameLabel	= new Label ( CHANGE_NAME_LABEL ) ;
		changePassLabel	= new Label ( CHANGE_PASS_LABEL ) ;
	}
	
	private void initializeFields ( ) {
		changeNameField			= new TextField ( ) ;
		changePassField			= new PasswordField ( ) ;
		changePassConfirmField	= new PasswordField ( ) ;
	}
	
	private void initializeButtons ( ) {
		chooseChangeNameButton	= new Button ( CHANGE_NAME_BUTTON ) ;
		chooseChangePassButton	= new Button ( CHANGE_PASS_BUTTON ) ;
		cancelButton				= new Button ( CANCEL_BUTTON ) ;
		changeNameSubmitButton	= new Button ( SUBMIT_BUTTON ) ;
		changeNameCancelButton	= new Button ( CANCEL_BUTTON ) ;
		changePassSubmitButton	= new Button ( SUBMIT_BUTTON ) ;
		changePassCancelButton	= new Button ( CANCEL_BUTTON ) ;
	}
	
	private void initializeAlerts ( ) {
		blankNameAlert				= new Alert ( AlertType.WARNING ) ;
		currentNameAlert			= new Alert ( AlertType.WARNING ) ;
		nameTakenAlert				= new Alert ( AlertType.WARNING ) ;
		confirmNameChangeAlert	= new Alert ( AlertType.CONFIRMATION ) ;
		nameChangedSuccessAlert	= new Alert ( AlertType.INFORMATION ) ;
		blankPassAlert				= new Alert ( AlertType.WARNING ) ;
		passNotMatchAlert			= new Alert ( AlertType.WARNING ) ;
		confirmPassChangeAlert	= new Alert ( AlertType.CONFIRMATION ) ;
		passChangedSuccessAlert	= new Alert ( AlertType.INFORMATION ) ;
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
		this.setResizable ( false ) ;
		
		pane.getStylesheets ( ).add ( GUI_CSS_FILENAME ) ;
		pane.getStyleClass ( ).add ( PANE_STYLECLASS ) ;
		
		BorderPane.setAlignment ( welcomeLabel, Pos.TOP_CENTER ) ;
		BorderPane.setAlignment ( initialDisplay, Pos.CENTER ) ;
		BorderPane.setAlignment ( changeNameDisplay, Pos.CENTER ) ;
		BorderPane.setAlignment ( changePassDisplay, Pos.CENTER ) ;
		
		titleDisplay.setAlignment ( Pos.CENTER ) ;
		initialDisplay.setAlignment ( Pos.CENTER ) ;
		changeNameDisplay.setAlignment ( Pos.CENTER ) ;
		changePassDisplay.setAlignment ( Pos.CENTER ) ;
		changeNameSubmitCancelBox.setAlignment ( Pos.CENTER ) ;
		changePassSubmitCancelBox.setAlignment ( Pos.CENTER ) ;
		
		pane.setPadding ( OUTER_PADDING ) ;
		titleDisplay.setPadding ( INNER_PADDING ) ;
		initialDisplay.setPadding ( INNER_PADDING ) ;
		changeNameDisplay.setPadding ( INNER_PADDING ) ;
		changePassDisplay.setPadding ( INNER_PADDING ) ;
		changeNameSubmitCancelBox.setPadding ( INNER_PADDING ) ;
		changePassSubmitCancelBox.setPadding ( INNER_PADDING ) ;
		
		titleDisplay.setSpacing ( SPACING ) ;
		initialDisplay.setSpacing ( SPACING ) ;
		changeNameDisplay.setSpacing ( SPACING ) ;
		changePassDisplay.setSpacing ( SPACING ) ;
		changeNameSubmitCancelBox.setSpacing ( SPACING ) ;
		changePassSubmitCancelBox.setSpacing ( SPACING ) ;
	}
	
	private void initializeLabelStyles ( ) {
		titleLabel.getStyleClass ( ).add ( TITLE_STYLECLASS ) ;
		welcomeLabel.getStyleClass ( ).add ( HEADING_STYLECLASS ) ;
		promptLabel.getStyleClass ( ).add ( TEXT_STYLECLASS ) ;
		changeNameLabel.getStyleClass ( ).add ( TEXT_STYLECLASS ) ;
		changePassLabel.getStyleClass ( ).add ( TEXT_STYLECLASS ) ;
	}
	
	private void initializeFieldStyles ( ) {
		changeNameField.setPrefWidth ( FIELD_WIDTH ) ;
		changePassField.setPrefWidth ( FIELD_WIDTH ) ;
		changePassConfirmField.setPrefWidth ( FIELD_WIDTH ) ;
		
		changeNameField.setPromptText ( CHANGE_NAME_PROMPT ) ;
		changePassField.setPromptText ( CHANGE_PASS_PROMPT ) ;
		changePassConfirmField.setPromptText ( CHANGE_PASS_CONFIRM_PROMPT ) ;
	}
	
	private void initializeButtonStyles ( ) {
		chooseChangeNameButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		chooseChangeNameButton.setPrefWidth ( BIG_BUTTON_WIDTH ) ;
		chooseChangeNameButton.setMaxWidth ( BIG_BUTTON_WIDTH ) ;
		chooseChangeNameButton.setMinWidth ( BIG_BUTTON_WIDTH ) ;
		
		chooseChangePassButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		chooseChangePassButton.setPrefWidth ( BIG_BUTTON_WIDTH ) ;
		chooseChangePassButton.setMaxWidth ( BIG_BUTTON_WIDTH ) ;
		chooseChangePassButton.setMinWidth ( BIG_BUTTON_WIDTH ) ;
		
		cancelButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		cancelButton.setPrefWidth ( BIG_BUTTON_WIDTH ) ;
		cancelButton.setMaxWidth ( BIG_BUTTON_WIDTH ) ;
		cancelButton.setMinWidth ( BIG_BUTTON_WIDTH ) ;
		
		changeNameSubmitButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		changeNameSubmitButton.setPrefWidth ( SMALL_BUTTON_WIDTH ) ;
		changeNameSubmitButton.setMaxWidth ( SMALL_BUTTON_WIDTH ) ;
		changeNameSubmitButton.setMinWidth ( SMALL_BUTTON_WIDTH ) ;
		
		changeNameCancelButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		changeNameCancelButton.setPrefWidth ( SMALL_BUTTON_WIDTH ) ;
		changeNameCancelButton.setMaxWidth ( SMALL_BUTTON_WIDTH ) ;
		changeNameCancelButton.setMinWidth ( SMALL_BUTTON_WIDTH ) ;
		
		changePassSubmitButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		changePassSubmitButton.setPrefWidth ( SMALL_BUTTON_WIDTH ) ;
		changePassSubmitButton.setMaxWidth ( SMALL_BUTTON_WIDTH ) ;
		changePassSubmitButton.setMinWidth ( SMALL_BUTTON_WIDTH ) ;
		
		changePassCancelButton.getStyleClass ( ).add ( BUTTON_STYLECLASS ) ;
		changePassCancelButton.setPrefWidth ( SMALL_BUTTON_WIDTH ) ;
		changePassCancelButton.setMaxWidth ( SMALL_BUTTON_WIDTH ) ;
		changePassCancelButton.setMinWidth ( SMALL_BUTTON_WIDTH ) ;
	}
	
	private void initializeAlertStyles ( ) {
		blankNameAlert.setHeaderText ( BLANK_NAME_ALERT_HEADER ) ;
		currentNameAlert.setHeaderText ( CURRENT_NAME_ALERT_HEADER ) ;
		nameTakenAlert.setHeaderText ( NAME_TAKEN_ALERT_HEADER ) ;
		confirmNameChangeAlert.setHeaderText ( CONFIRM_NAME_ALERT_HEADER ) ;
		nameChangedSuccessAlert.setHeaderText ( NAME_CHANGED_ALERT_HEADER ) ;
		blankPassAlert.setHeaderText ( BLANK_PASS_ALERT_HEADER ) ;
		passNotMatchAlert.setHeaderText ( PASS_NOT_MATCH_ALERT_HEADER ) ;
		confirmPassChangeAlert.setHeaderText ( CONFIRM_PASS_ALERT_HEADER ) ;
		passChangedSuccessAlert.setHeaderText ( PASS_CHANGED_ALERT_HEADER ) ;
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
		initializeTitle ( ) ;
		initializeStartingChoices ( ) ;
		initializeChangeNameDisplay ( ) ;
		initializeChangePassDisplay ( ) ;
		initializePane ( ) ;
		scene = new Scene ( pane, MED_PANE_WIDTH, MED_PANE_HEIGHT ) ;
		this.setScene ( scene ) ;
	}
	
	private void initializeTitle ( ) {
		titleDisplay.getChildren ( ).addAll ( titleLabel, welcomeLabel ) ;
	}
	
	private void initializeStartingChoices ( ) {
		initialDisplay.getChildren ( ).addAll ( promptLabel, chooseChangeNameButton, chooseChangePassButton, cancelButton ) ;
	}
	
	private void initializeChangeNameDisplay ( ) {
		changeNameSubmitCancelBox.getChildren ( ).addAll ( changeNameSubmitButton, changeNameCancelButton ) ;
		changeNameDisplay.getChildren ( ).addAll ( changeNameLabel, changeNameField, changeNameSubmitCancelBox ) ;
	}
	
	private void initializeChangePassDisplay ( ) {
		changePassSubmitCancelBox.getChildren ( ).addAll ( changePassSubmitButton, changePassCancelButton ) ;
		changePassDisplay.getChildren ( ).addAll ( changePassLabel, changePassField, changePassConfirmField, changePassSubmitCancelBox ) ;
	}
	
	private void initializePane ( ) {
		pane.setTop ( titleDisplay ) ;
		pane.setCenter ( initialDisplay ) ;
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
		chooseChangeNameButton.setOnAction ( event -> chooseChangeName ( ) ) ;
		chooseChangePassButton.setOnAction ( event -> chooseChangePass ( ) ) ;
		cancelButton.setOnAction ( event -> returnToMain ( ) ) ;
		changeNameSubmitButton.setOnAction ( event -> changeName ( ) ) ;
		changeNameCancelButton.setOnAction ( event -> revertToStart ( ) ) ;
		changePassSubmitButton.setOnAction ( event -> changePass ( ) ) ;
		changePassCancelButton.setOnAction ( event -> revertToStart ( ) ) ;
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
	
	private void changeName ( ) {
		String newName = changeNameField.getText ( ) ;
		if ( newName == "" || newName == null ) {
			blankNameAlert.showAndWait ( ) ;
		} else if ( AccountManager.accountManager.getCurrentAccount ( ).getUsername ( ).equals ( newName ) ) {
			currentNameAlert.showAndWait ( ) ;
		} else if ( AccountManager.accountManager.contains ( newName ) ) {
			nameTakenAlert.showAndWait ( ) ;
		} else {
			confirmNameChangeAlert.showAndWait ( ).ifPresent ( response -> {
				if ( response == ButtonType.OK ) {
					AccountManager.accountManager.getCurrentAccount ( ).setUsername ( newName ) ;
					AccountManager.accountManager.save ( ) ;
					nameChangedSuccessAlert.showAndWait ( ) ;
					welcomeLabel.setText ( String.format ( WELCOME_LABEL, AccountManager.accountManager.getCurrentAccount ( ).getUsername ( ) ) ) ;
					revertToStart ( ) ;
				}
			} ) ;
		}
		changeNameField.clear ( ) ;
		changeNameField.requestFocus ( ) ;
	}
	
	private void changePass ( ) {
		String newPass = changePassField.getText ( ) ;
		if ( newPass == "" || newPass == null ) {
			blankPassAlert.showAndWait ( ) ;
		} else if ( !changePassConfirmField.getText ( ).equals ( newPass ) ) {
			passNotMatchAlert.showAndWait ( ) ;
		} else {
			confirmPassChangeAlert.showAndWait ( ).ifPresent ( response -> {
				AccountManager.accountManager.getCurrentAccount ( ).setPassword ( newPass ) ;
				AccountManager.accountManager.save ( ) ;
				passChangedSuccessAlert.showAndWait ( ) ;
				revertToStart ( ) ;
			} ) ;
		}
		changePassField.clear ( ) ;
		changePassConfirmField.clear ( ) ;
		changePassField.requestFocus ( ) ;
	}
	
	private void chooseChangeName ( ) {
		pane.setCenter ( changeNameDisplay ) ;
		changeNameSubmitButton.setDefaultButton ( true ) ;
	}
	
	private void chooseChangePass ( ) {
		pane.setCenter ( changePassDisplay ) ;
		changePassSubmitButton.setDefaultButton ( true ) ;
	}
	
	private void revertToStart ( ) {
		pane.setCenter ( initialDisplay ) ;
		changeNameSubmitButton.setDefaultButton ( false ) ;
		changePassSubmitButton.setDefaultButton ( false ) ;
	}
	
	private void returnToMain ( ) {
		WordleApp.app.reloadMainMenu ( ) ;
		this.close ( ) ;
	}
	
}
