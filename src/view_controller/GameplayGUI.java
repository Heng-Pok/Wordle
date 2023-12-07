
package view_controller ;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets ;
import javafx.geometry.Pos ;
import javafx.scene.Scene ;
import javafx.scene.control.* ;
import javafx.scene.input.KeyCode ;
import javafx.scene.input.KeyEvent ;
import javafx.stage.Stage ;
import model.AccountManager;
import model.Game ;
import javafx.scene.layout.BorderPane ;
import javafx.scene.layout.HBox ;
import javafx.scene.layout.VBox ;

import java.util.ArrayList ;
import java.util.Random;

public class GameplayGUI extends Stage {
	
	private static GameplayGUI thisGUI ;
	
	private BorderPane						gameUI ;
	private Scene							scene ;
	private GuessGrid						guessGrid ;
	private KeyboardGrid 					keyboardGrid;
	private Game							theGame ;
	private HBox							guessesBox ;
	private VBox							promptBox ;
	private Label							guessesLabel ;
	private Label							guessesNum ;
	private Label							scoreLabel ;
	private Label							prompt ;
	private ArrayList < char [ ] >	guessList ;
	private int								currentChar ;
	private int								currentGuess ;
	private Button							smallPowerupBtn;
	private Button							mediumPowerupBtn;
	private Button							largePowerupBtn;
	private HBox							powerupBox;
	private Random							random;
	private Label							powerupLabel;
	
	private ScrollPane scrollPane ;
	private VBox bottomVBox;
	
	public GameplayGUI( Game game ) {
		thisGUI = this ;
		initializeAttributes ( game ) ;
		initializeLayout ( ) ;
		initializeHandlers ( ) ;
		thisGUI.setScene ( scene ) ;
	}
	
	private void initializeAttributes ( Game game ) {
		
		gameUI			= new BorderPane ( ) ;
		scrollPane		= new ScrollPane ( gameUI ) ;
		bottomVBox		= new VBox();
		scene			= new Scene ( scrollPane ) ;
		guessGrid		= new GuessGrid ( ) ;
		keyboardGrid	= new KeyboardGrid ( );
		theGame			= game ;
		guessesBox		= new HBox ( ) ;
		promptBox		= new VBox ( ) ;
		guessesLabel	= new Label ( INIT_GUESS_LABEL ) ;
		guessesNum		= new Label ( INIT_GUESS_NUM ) ;
		scoreLabel		= new Label ( YOUR_SCORE_LABEL ) ;
		prompt			= new Label ( INIT_PROMPT_LABEL ) ;
		powerupLabel	= new Label ( "" ) ;
		guessList		= new ArrayList <> ( ) ;
		guessList.add ( new char [ ] {
		   INIT_BLANK_CHAR, INIT_BLANK_CHAR, INIT_BLANK_CHAR, INIT_BLANK_CHAR, INIT_BLANK_CHAR
		} ) ;
		currentChar		= 0 ;
		currentGuess	= game.getGuessCount ( ) ;
		smallPowerupBtn = formatPowerupButton("Small Powerup");
		mediumPowerupBtn = formatPowerupButton("Medium Powerup");
		largePowerupBtn = formatPowerupButton("Large Powerup");
		powerupBox = new HBox(smallPowerupBtn, mediumPowerupBtn, largePowerupBtn);
		random = new Random();
	}
	
	private void initializeLayout ( ) {
		gameUI.setPrefSize ( PREF_WIDTH, PREF_HEIGHT ) ;
		gameUI.setPadding ( PADDING ) ;
		gameUI.getStylesheets ( ).add ( CSS_GUI_FILE_NAME ) ;
		gameUI.getStyleClass ( ).add ( CSS_PANE_CLASS ) ;
		scrollPane.getStylesheets ( ).add ( CSS_SCROLL_STYLE_FILE_NAME ) ;
		gameUI.setTop ( promptBox ) ;
		guessesBox.getChildren ( ).addAll ( guessesLabel, guessesNum ) ;
		
		powerupBox.setAlignment(Pos.BOTTOM_RIGHT);
		powerupBox.setSpacing(5);
		
		bottomVBox.getChildren().add ( powerupLabel ) ;
		bottomVBox.getChildren().add ( prompt );
		bottomVBox.getChildren().add ( keyboardGrid );
		bottomVBox.getChildren().add ( powerupBox );
		gameUI.setBottom ( bottomVBox ) ;
		gameUI.setCenter ( guessGrid.getDisplay ( ) ) ;
		
		guessesBox.setAlignment ( Pos.TOP_CENTER ) ;
		bottomVBox.setAlignment ( Pos.CENTER ) ;
		promptBox.getChildren ( ).add ( guessesBox ) ;
		promptBox.setAlignment ( Pos.CENTER ) ;
		guessGrid.getDisplay ( ).setAlignment ( Pos.TOP_CENTER ) ;
		BorderPane.setAlignment ( prompt, Pos.BOTTOM_CENTER ) ;
		bottomVBox.setSpacing(10);
		
		guessesLabel.getStyleClass ( ).add ( CSS_HEADING1_CLASS ) ;
		guessesNum.getStyleClass ( ).add ( CSS_HEADING1_CLASS ) ;
		scoreLabel.getStyleClass ( ).add ( CSS_HEADING1_CLASS ) ;
	}
	
	private void initializeHandlers ( ) {
		thisGUI.addEventFilter ( KeyEvent.KEY_PRESSED, ( KeyEvent key ) -> keyInputHandler ( key ) ) ;
		for (Button[] buttonRow: keyboardGrid.getButtonsArray()) {
		    for (Button button: buttonRow) {
			if ( button == null )
			    break ;
		    	button.setOnAction ( new buttonListener( ) );
		    	button.setFocusTraversable(false);
		    }
		}
	}
	
	    private class buttonListener implements EventHandler<ActionEvent> {
	        @Override
	        public void handle(ActionEvent arg0){
			if ( theGame.isStillPlaying ( ) ) {
			    Button buttonClicked = (Button) arg0.getSource();
				switch ( currentChar ) {
					case 0 : // no backspace
						if ( buttonClicked.getText( ).length( ) == 1 && Character.isLetter( buttonClicked.getText( ).charAt(0) ) ) {
							addLetter ( buttonClicked.getText( ) ) ;
						}
						break ;
					case 1, 2, 3, 4 :
						if ( buttonClicked.getText( ).length( ) == 1 && Character.isLetter( buttonClicked.getText( ).charAt(0) ) ) {
							addLetter ( buttonClicked.getText() ) ;
						} else if ( buttonClicked.getText( ).equals( "<--" ) ) {
							backspace ( ) ;
						}
						break ;
					case 5 : // no more chars, can submit
						if ( buttonClicked.getText( ).equals( "<--" ) ) {
							backspace ( ) ;
						} else if ( buttonClicked.getText( ).equals( "ENTER" ) ) {
							enterGuess ( ) ;
						}
						break ;
					}}
	            }
	    }
	
	private void keyInputHandler ( KeyEvent key ) {
		if ( theGame.isStillPlaying ( ) ) {
			switch ( currentChar ) {
				case 0 : // no backspace
					if ( key.getCode ( ).isLetterKey ( ) ) {
						addLetter ( key ) ;
					}
					break ;
				case 1, 2, 3, 4 :
					if ( key.getCode ( ).isLetterKey ( ) ) {
						addLetter ( key ) ;
					} else if ( key.getCode ( ) == KeyCode.BACK_SPACE ) {
						backspace ( ) ;
					}
					break ;
				case 5 : // no more chars, can submit
					if ( key.getCode ( ) == KeyCode.BACK_SPACE ) {
						backspace ( ) ;
					} else if ( key.getCode ( ) == KeyCode.ENTER ) {
						enterGuess ( ) ;
					}
					break ;
			}
		}
	}
	
	
	private void addLetter ( KeyEvent key ) {
		char inputChar = key.getText ( ).charAt ( 0 ) ;
		guessList.get ( currentGuess ) [ currentChar ] = inputChar ;
		guessGrid.setSquareChar ( inputChar, currentGuess, currentChar, 2 ) ;
		currentChar++ ;
	}
	
	private void addLetter (String keyString) {
    		char inputChar = Character.toLowerCase( keyString.charAt ( 0 ) ) ;
    		guessList.get ( currentGuess ) [ currentChar ] = inputChar ;
    		guessGrid.setSquareChar ( inputChar, currentGuess, currentChar, 2 ) ;
    		currentChar++ ;
	}
	
	private void backspace ( ) {
		currentChar-- ;
		guessList.get ( currentGuess ) [ currentChar ] = INIT_BLANK_CHAR ;
		guessGrid.setSquareChar ( '_', currentGuess, currentChar, 3 ) ;
	}
	
	private void enterGuess ( ) {
		currentChar = 0 ;
		String guess = "" ;
		for ( char c : guessList.get ( currentGuess ) ) {
			guess += c ;
		}
		if ( theGame.checkValidGuess ( guess ) ) {
			char [ ] results = theGame.guess ( guess ) ;
			guessGrid.displayResults ( guessList.get ( currentGuess ), results, currentGuess ) ;
			currentGuess = theGame.getGuessCount ( ) ;
			guessesNum.setText ( String.valueOf ( currentGuess ) ) ;
			if ( theGame.isStillPlaying ( ) ) {
				guessGrid.addBlankRow ( currentGuess, 0 ) ;
				guessList.add ( new char [ ] {
				   INIT_BLANK_CHAR, INIT_BLANK_CHAR, INIT_BLANK_CHAR, INIT_BLANK_CHAR, INIT_BLANK_CHAR
				} ) ;
				prompt.setText(INIT_PROMPT_LABEL);
				scrollPane.setVvalue(10.0);
			} else {
				guessesLabel.setText ( GAME_OVER_LABEL ) ;
				if ( theGame.won ) {
					guessesNum.setText ( YOU_WIN_LABEL ) ;
					if ( theGame.getGuessCount ( ) <= 6 ) {
						YOUR_SCORE_LABEL = "YOUR SCORE IS: " + ( theGame.getScore ( ) ) ;
						scoreLabel.setText ( YOUR_SCORE_LABEL ) ;
						System.out.println ( "Your score is: " + ( theGame.getScore ( ) ) ) ;
						promptBox.getChildren ( ).addAll ( scoreLabel ) ;
					}
					prompt.setText ( YOU_WIN_PROMPT ) ;
				} else {
					guessesNum.setText ( YOU_LOSE_LABEL ) ;
					prompt.setText ( YOU_LOSE_PROMPT + theGame.getWord ( ) ) ;
				}
			}
		} else {
			// invalid guess alert
			guessList.remove ( currentGuess ) ;
			guessList.add ( new char [ ] {
			   INIT_BLANK_CHAR, INIT_BLANK_CHAR, INIT_BLANK_CHAR, INIT_BLANK_CHAR, INIT_BLANK_CHAR
			} ) ;
			for ( int i = 0 ; i < 5 ; i++ ) {
				guessGrid.setSquareChar ( INIT_BLANK_CHAR, currentGuess, i, 0 ) ;
			}
			prompt.setText("Invalid word!");
		}
		
	        changeColorsOnKeyboard(guess);
	}

	private void changeColorsOnKeyboard(String guess) {
	    for (int i = 0; i < guess.length(); i++) {
	        char currentChar = Character.toUpperCase ( guess.charAt ( i ) ) ;
	        // if the current character of the guess is not part of the answer
	        if ( theGame.knownInformation.containsKey ( currentChar ) && theGame.knownInformation.get ( currentChar ) == 0 ) {
	            	//System.out.println ( "Disabling a key......" ) ;
        	    	for (Button[] buttonRow: keyboardGrid.getButtonsArray()) {
        	    	    for (Button button: buttonRow) {
        	    		if ( button != null && Character.toLowerCase( button.getText( ).charAt( 0 ) ) == Character.toLowerCase ( currentChar ) && !button.getText().equals("ENTER") ) {
        	    		    //System.out.println ( "Disabling char: " + button.getText( ).charAt( 0 ) ) ;
        	    		    button.setStyle("-fx-background-color: gray; -fx-text-fill: black;");}}}
	        } else if ( theGame.knownInformation.containsKey ( currentChar ) && theGame.knownInformation.get ( currentChar ) == 1 ) {  
            	    	for (Button[] buttonRow: keyboardGrid.getButtonsArray()) {
        	    	    for (Button button: buttonRow) {
        	    		if ( button != null && Character.toLowerCase( button.getText( ).charAt( 0 ) ) == Character.toLowerCase ( currentChar ) && !button.getText().equals("ENTER") ) {
        	    		    //System.out.println ( "Disabling char: " + button.getText( ).charAt( 0 ) ) ;
        	    		    button.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");}}}
	        } else if ( theGame.knownInformation.containsKey ( currentChar ) && theGame.knownInformation.get ( currentChar ) == 2 ) {  
        	    	for (Button[] buttonRow: keyboardGrid.getButtonsArray()) {
        	    	    for (Button button: buttonRow) {
        	    		if ( button != null && Character.toLowerCase( button.getText( ).charAt( 0 ) ) == Character.toLowerCase ( currentChar ) && !button.getText().equals("ENTER") ) {
        	    		    //System.out.println ( "Disabling char: " + button.getText( ).charAt( 0 ) ) ;
        	    		    button.setStyle("-fx-background-color: #42f56c; -fx-text-fill: black;");}}}
	        }}
	}
	
	private Button formatPowerupButton(String powerupStr) {
		Button button = new Button();
		button.setMinSize(20, 20);
		button.setMaxSize(20, 20);
		button.setPrefSize(20, 20);
		button.setTooltip(new Tooltip(powerupStr));
		button.setFocusTraversable(false);
		if (AccountManager.accountManager.getCurrentAccount() == null) { // no current account, return a disabled button
			button.setStyle("-fx-background-color: grey;");
			return button;
		}
		// set up coloring
		if (powerupStr.equals("Small Powerup")) {
			if (AccountManager.accountManager.getPowerups().getOrDefault("smallHint", 0) >= 1) {
				button.setStyle("-fx-background-color: red;");
			} else {
				button.setStyle("-fx-background-color: grey;");
				return button; // no small powerups, return disabled button
			}
		} else if (powerupStr.equals("Medium Powerup")) {
			if (AccountManager.accountManager.getPowerups().getOrDefault("mediumHint", 0) >= 1) {
				button.setStyle("-fx-background-color: yellow;");
			} else {
				button.setStyle("-fx-background-color: grey;");
				return button;
			}
		} else if (powerupStr.equals("Large Powerup")) {
			if (AccountManager.accountManager.getPowerups().getOrDefault("largeHint", 0) >= 1) {
				button.setStyle("-fx-background-color: green;");
			} else {
				button.setStyle("-fx-background-color: grey;");
				return button;
			}
		}
		
		button.setOnAction(event -> {
			if (theGame.isStillPlaying()) {
				AccountManager.accountManager.usePowerup(powerupStr);
				AccountManager.accountManager.save();
				button.setStyle("-fx-background-color: grey;");

				if (powerupStr.equals("Small Powerup")) {
					if (!theGame.getUnguessedLetters().equals("")) {
						int index = random.nextInt(theGame.getUnguessedLetters().length());
						powerupLabel.setText("This letter is included in the answer: " + theGame.getUnguessedLetters().charAt(index));
					} else {
						powerupLabel.setText("You've already guessed all 5 characters!");
					}
				} else if (powerupStr.equals("Medium Powerup")) {
					if (!theGame.getUnguessedIndices().isEmpty()) {
						int index = theGame.getUnguessedIndices().get(random.nextInt(theGame.getUnguessedIndices().size()));
						powerupLabel.setText("The letter at the " + (index+1) + " spot is: " + theGame.getWord().charAt(index));
					}
				} else if (powerupStr.equals("Large Powerup")) {
					powerupLabel.setText("The answer contains these letters: " + theGame.getShuffledAnswer());
				}
				button.setDisable(true); // only 1 power up of each type per game
			}
		});
		
		return button;
	}
	
	private final String	INIT_GUESS_LABEL				= "Guesses: " ;
	private final String	INIT_GUESS_NUM					= "0" ;
	private final String	INIT_PROMPT_LABEL				= "Type your guess, then press enter." ;
	private final String	CSS_GUI_FILE_NAME				= "documents/GUI.css" ;
	private final String	CSS_SCROLL_STYLE_FILE_NAME	= "documents/ScrollPaneStyle.css" ;
	private final String	CSS_PANE_CLASS					= "pane" ;
	private final String	CSS_HEADING1_CLASS			= "heading1" ;
	private final String	GAME_OVER_LABEL				= "Game Over: " ;
	private final String	YOU_WIN_LABEL					= "You Win!" ;
	private String			YOUR_SCORE_LABEL				= "" ;
	private final String	YOU_WIN_PROMPT					= "Close the screen to play again!" ;
	private final String	YOU_LOSE_LABEL					= "You lose..." ;
	private final String	YOU_LOSE_PROMPT				= "The word was " ;
	
	private final char INIT_BLANK_CHAR = '_' ;
	
	private final int	PREF_WIDTH	= 630 ;
	private final int	PREF_HEIGHT	= 690 ;
	
	private final Insets PADDING = new Insets ( 10, 10, 10, 10 ) ;
	
}