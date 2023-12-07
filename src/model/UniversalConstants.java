
package model ;

import javafx.geometry.Insets ;

/**
 * A collection to be used throughout the GUI for the sake of consistency.
 */
public final class UniversalConstants {
	
	private UniversalConstants( ) {}
	
	// CSS Constants
	public static final String	GUI_CSS_FILENAME		= "documents/GUI.css" ;
	public static final String	PANE_STYLECLASS		= "pane" ;
	public static final String	TITLE_STYLECLASS		= "title" ;
	public static final String	HEADING_STYLECLASS	= "heading1" ;
	public static final String	TEXT_STYLECLASS		= "normal" ;
	public static final String	BUTTON_STYLECLASS		= "button" ;
	
	public static final String	GUESS_GRID_CSS_FILENAME					= "documents/guessGrid.css" ;
	public static final String	CORRCET_SQUARE_STYLECLASS				= "correctSquare" ;
	public static final String	INCORRECT_SQUARE_STYLECLASS			= "incorrectSquare" ;
	public static final String	PARTIAL_CORRECT_SQUARE_STYLECLASS	= "parialCorrectSquare" ;
	public static final String	GUESS_GRID_TEXT_STYLECLASS				= "text" ;
	
	public static final String SCROLL_PANE_CSS_FILENAME = "documents/ScrollPaneStyle.css" ;
	
	// Size Constants
	public static final Insets	OUTER_PADDING	= new Insets ( 10, 10, 10, 10 ) ;
	public static final Insets	INNER_PADDING	= new Insets ( 5, 5, 5, 5 ) ;
	
	public static final double	BIG_BUTTON_WIDTH		= 250.0 ;
	public static final double	MED_BUTTON_WIDTH		= 150.0 ;
	public static final double	SMALL_BUTTON_WIDTH	= 100.0 ;
	public static final double	SPACING_EXTRA			= 20.0 ;
	public static final double	SPACING					= 10.0 ;
	public static final double	SPACING_SMALL			= 5.0 ;
	public static final double	FIELD_WIDTH				= 150.0 ;
	public static final double	GAME_PANE_WIDTH		= 630.0 ;
	public static final double	GAME_PANE_HEIGHT		= 690.0 ;
	public static final double	BIG_PANE_WIDTH			= 500.0 ;
	public static final double	BIG_PANE_HEIGHT		= 600.0 ;
	public static final double	MED_PANE_WIDTH			= 400.0 ;
	public static final double	MED_PANE_HEIGHT		= 500.0 ;
	public static final double	SMALL_PANE_WIDTH		= 300.0 ;
	public static final double	SMALL_PANE_HEIGHT		= 400.0 ;
	
}
