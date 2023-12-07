/*
 * Ethan Toledo
 */

package view_controller ;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets ;
import javafx.geometry.Pos ;
import javafx.scene.layout.GridPane ;
import javafx.scene.layout.StackPane ;
import javafx.scene.shape.Rectangle ;
import javafx.scene.text.Text ;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

public class GuessGrid {
	
	private GridPane guessDisplay ;
	
	public GuessGrid( ) {
		guessDisplay = new GridPane ( ) ;
		guessDisplay.getStylesheets ( ).add ( CSS_FILE_NAME ) ;
		guessDisplay.setPadding ( PADDING ) ;
		guessDisplay.setHgap ( HGAP ) ;
		guessDisplay.setVgap ( VGAP ) ;
		guessDisplay.setAlignment ( Pos.TOP_CENTER ) ; 
		addBlankRow ( 0, 1 ) ;
		addBlankRow ( 1, 1 ) ;
		addBlankRow ( 2, 1 ) ;
		addBlankRow ( 3, 1 ) ;
		addBlankRow ( 4, 1 ) ;
		addBlankRow ( 5, 1 ) ;
		
	}
	
	public void addBlankRow ( int rowNum, int animationNumber ) {
		for ( int i = 0 ; i < 5 ; i++ ) {
			guessDisplay.add ( generateSquare ( INITIAL_CHAR, CSS_INCORRECT_CLASS, animationNumber ), i, rowNum ) ;
		}
	}
	
	public void setSquareChar ( char c, int row, int col, int animationNumber ) {
		guessDisplay.add ( generateSquare ( c, CSS_INCORRECT_CLASS, animationNumber ), col, row ) ;
	}
	
	public void displayResults ( char [ ] guess, char [ ] results, int row ) {
		for ( int i = 0 ; i < 5 ; i++ ) {
			String type = "" ;
			switch ( results [ i ] ) {
				case CORRECT :
					type = CSS_CORRECT_CLASS ;
					break ;
				case INCORRECT :
					type = CSS_INCORRECT_CLASS ;
					break ;
				case PART_CORRECT :
					type = CSS_PARTIAL_CLASS ;
					break ;
			}
			guessDisplay.add ( generateSquare ( guess [ i ], type, 4 ), i, row ) ;
		}
	}
	
	private StackPane generateSquare ( char c, String type, int animationNumber ) { //animationNumber = 0, 1, 2 or 3 (0 = do not animate the rect, 1-3 = animate the rect)
		StackPane sp = new StackPane ( ) ;
		sp.getStylesheets ( ).add ( CSS_FILE_NAME ) ;
		Rectangle r = new Rectangle ( ) ;
		r.setHeight ( PREF_SIZE ) ;
		r.setWidth ( PREF_SIZE ) ;
		r.getStyleClass ( ).add ( type ) ;
		switch (animationNumber) {
        		case 1:
        		    animateRectangleAtStartUp ( r ) ;
        		    break;
        		case 2:
        		    animateRectangleWhenAddingChars ( r ) ;
        		    break;
        		case 3:
        		    animateRectangleWhenBackspace ( r ) ;
        		    break;
        		case 4:
        		    animateRectangleAtResult ( r ) ;
        		    break;
		}
		Text t = new Text ( String.valueOf ( c ) ) ;
		t.getStyleClass ( ).add ( CSS_TEXT_CLASS ) ;
		sp.getChildren ( ).addAll ( r, t ) ;
		return sp ;
	}
	
	    private void animateRectangleAtStartUp(Rectangle rectangle) {
	        // Fade-in animation
	        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), rectangle);
	        fadeTransition.setFromValue(0);
	        fadeTransition.setToValue(1);

	        // Scale animation
	        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.5), rectangle);
	        scaleTransition.setFromX(0);
	        scaleTransition.setToX(1);

	        // Rotate animation
	        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), rectangle);
	        rotateTransition.setByAngle(360);

	        // SequentialTransition to combine multiple animations
	        SequentialTransition sequentialTransition = new SequentialTransition(rectangle,
	                fadeTransition,
	                scaleTransition,
	                rotateTransition);

	        // Set up animation cycle count and auto-reverse
	        sequentialTransition.setCycleCount(1);
	        sequentialTransition.setAutoReverse(false);

	        // Play the animation
	        sequentialTransition.play();
	    }
	
	    private void animateRectangleAtResult ( Rectangle rectangle ) {
		// flip animation
	        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.4), rectangle);
	        rotateTransition.setAxis(Rotate.Y_AXIS);
	        rotateTransition.setByAngle(180);

	        // SequentialTransition to combine multiple animations
	        SequentialTransition sequentialTransition = new SequentialTransition(rectangle,
	                rotateTransition);

	        // Set up animation cycle count and auto-reverse
	        sequentialTransition.setCycleCount(1);
	        sequentialTransition.setAutoReverse(false);

	        // Play the animation
	        sequentialTransition.play();
	    }
	    
	    private void animateRectangleWhenAddingChars(Rectangle rectangle) {
	        // Rotate animation
	        RotateTransition rotateTransition = new RotateTransition(Duration.seconds(0.5), rectangle);
	        rotateTransition.setByAngle(360);

	        // SequentialTransition to combine multiple animations
	        SequentialTransition sequentialTransition = new SequentialTransition(rectangle,
	                rotateTransition);

	        // Set up animation cycle count and auto-reverse
	        sequentialTransition.setCycleCount(1);
	        sequentialTransition.setAutoReverse(false);

	        // Play the animation
	        sequentialTransition.play();
	    }
	    
	 private void animateRectangleWhenBackspace(Rectangle rectangle) {
	     
	        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.3), rectangle);
	        scaleTransition.setFromX(0);
	        scaleTransition.setToX(1);
	        scaleTransition.setFromY(0);
	        scaleTransition.setToY(1);

	        // SequentialTransition to combine multiple animations
	        SequentialTransition sequentialTransition = new SequentialTransition(rectangle,
	                scaleTransition);

	        // Set up animation cycle count and auto-reverse
	        sequentialTransition.setCycleCount(1);
	        sequentialTransition.setAutoReverse(false);

	        // Play the animation
	        sequentialTransition.play();
	    }
	 
	
	public GridPane getDisplay ( ) { return guessDisplay ; }
	
	private final double	PREF_SIZE				= 50.0 ;
	private final double	HGAP						= 5.0 ;
	private final double	VGAP						= 10.0 ;
	private final char	CORRECT					= 'r' ;
	private final char	INCORRECT				= 'w' ;
	private final char	PART_CORRECT			= 'p' ;
	private final char	INITIAL_CHAR			= '_' ;
	private final String	CSS_FILE_NAME			= "documents/guessGrid.css" ;
	private final String	CSS_CORRECT_CLASS		= "correctSquare" ;
	private final String	CSS_INCORRECT_CLASS	= "incorrectSquare" ;
	private final String	CSS_PARTIAL_CLASS		= "partialCorrectSquare" ;
	private final String	CSS_TEXT_CLASS			= "text" ;
	private final Insets	PADDING					= new Insets ( 10, 10, 10, 10 ) ;
	
}
