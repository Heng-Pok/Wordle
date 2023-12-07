
package view_controller ;

import javafx.geometry.Insets ;
import javafx.scene.control.Label ;
import javafx.scene.image.Image ;
import javafx.scene.image.ImageView ;
import javafx.scene.layout.GridPane ;
import javafx.scene.layout.VBox ;
import javafx.scene.text.Font ;

/**
 * Pane to display instructions to the user.
 * 
 * @author Parker Hines
 */
public class InstructionsPane extends GridPane {
	
	private final Insets	padding	= new Insets ( 10, 10, 10, 10 ) ;
	private final double	hGap		= 5.0 ;
	private final double	vGap		= 5.0 ;
	
	private VBox everything ;
	
	// labels for instruction titles/text
	private Label	instructionsMainTitle ;
	private Label	howToPlayTitle ;
	private Label	actualInstructions ;
	private Label	exampleTitle ;
	private Label	exampleExplanation ;
	
	// example image, placeholder until our board is 100% finished.
	private Image exampleWordle = new Image ( getClass ( ).getResourceAsStream ( "/documents/example.png" ) ) ;
	
	/**
	 * initialize an instructions pane
	 */
	public InstructionsPane( ) {
		format ( ) ;
		setLabels ( ) ;
		fillVBox ( ) ;
		this.add ( everything, 0, 0 ) ;
		this.getStylesheets ( ).add ( "documents/GUI.css" ) ;
		this.getStyleClass ( ).add ( "pane" ) ;
	}
	
	/**
	 * fills the VBox 'everything' with all class attributes:
	 * main title, how to play title, the instructions text block, "example" title,
	 * the example image, and the example explanation text block.
	 */
	private void fillVBox ( ) {
		ImageView wordleImageView = new ImageView ( exampleWordle ) ;
		wordleImageView.setFitWidth ( 315 ) ;
		wordleImageView.setPreserveRatio ( true ) ;
		
		everything = new VBox ( 10 ) ;
		everything.setPadding ( padding ) ;
		
		everything
		   .getChildren ( )
		   .addAll ( instructionsMainTitle, howToPlayTitle, actualInstructions, exampleTitle, wordleImageView, exampleExplanation ) ;
		
	}
	
	/**
	 * sets the label text for all 5 label attributes:
	 * main title, how to play title, the instructions text block, "example" title,
	 * and the example explanation text block.
	 */
	private void setLabels ( ) {
		String instructionsMainTitleText = "Instructions" ;
		instructionsMainTitle = new Label ( instructionsMainTitleText ) ;
		instructionsMainTitle.getStyleClass ( ).add ( "title" ) ;
		
		String howToPlayTitleText = "How To Play" ;
		howToPlayTitle = new Label ( howToPlayTitleText ) ;
		howToPlayTitle.getStyleClass ( ).add ( "heading1" ) ;
		
		String actualInstructionsText
		   = "1. You have 6 tries to guess a random word.\n"
		      + "2. You are only able to guess valid English 5-letter words.\n"
		      + "3. Pay attention to tile colors in your past guesses for clues!" ;
		actualInstructions = new Label ( actualInstructionsText ) ;
		actualInstructions.setFont ( Font.font ( "Arial", 14 ) ) ;
		actualInstructions.setWrapText ( true ) ;
		
		String exampleTitleText = "Example" ;
		exampleTitle = new Label ( exampleTitleText ) ;
		exampleTitle.getStyleClass ( ).add ( "heading1" ) ;
		
		String exampleExplanationText
		   = "In the above guess \"S K I T S\": \n"
		      + "1. The letter S is not in the final answer.\n"
		      + "2. The letters K and T are in the final answer, but in different places "
		      + "than what you see here.\n"
		      + "3. The letter I is in the final answer, in this exact position. \n\n"
		      + "The correct answer in this scenario might be:\n\n \"T H I N K\"" ;
		exampleExplanation = new Label ( exampleExplanationText ) ;
		exampleExplanation.setFont ( Font.font ( "Arial", 14 ) ) ;
		exampleExplanation.setWrapText ( true ) ;
		
	}
	
	/**
	 * format the pane for display
	 */
	private void format ( ) {
		this.setPadding ( padding ) ;
		this.setHgap ( hGap ) ;
		this.setVgap ( vGap ) ;
	}
	
}