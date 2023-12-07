package view_controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
public class KeyboardGrid extends BorderPane{
    
    private Button[][] buttonsArray;
    private HBox top;
    private HBox mid;
    private HBox bottom;
    
    
    KeyboardGrid() {
        String[][] alphabet = {
                {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P"},
                {"A", "S", "D", "F", "G", "H", "J", "K", "L"},
                {"<--", "Z", "X", "C", "V", "B", "N", "M", "ENTER"}
        };
        
        top = new HBox(); 		top.setSpacing(10);
        mid = new HBox(); 		mid.setSpacing(10);		mid.setPadding(new Insets(5, 0, 5, 20));
        bottom = new HBox(); 	bottom.setSpacing(10);
        buttonsArray = new Button[3][10];
        
        // Add buttons to the grid for each alphabet key
        for (int row = 0; row < alphabet.length; row++) {
            for (int col = 0; col < alphabet[row].length; col++) {
                String letter = alphabet[row][col];
                Button button = createKeyboardButton(letter);
                if (row == 0)
                    top.getChildren().add(button);
                if (row == 1)
                    mid.getChildren().add(button);
                if (row == 2)
                    bottom.getChildren().add(button);
                buttonsArray[row][col] = button;
            }
        }
        top.setPadding( new Insets ( 0, 0, 0, 40 ) );
        mid.setPadding( new Insets ( 0, 0, 0, 60 ) );
        bottom.setPadding( new Insets ( 0, 0, 0, 30 ) );
        this.setTop(top);
        this.setCenter(mid);
        this.setBottom(bottom);
        this.setMinWidth(300);
        this.setMaxWidth(520);
        //this.setStyle("-fx-border-color: red; -fx-border-width: 2px;");
    }
    
    private Button createKeyboardButton(String letter) {
        Button button = new Button(letter);
        return button;
    }

    
    public Button[][] getButtonsArray () {
	return buttonsArray;
    }

}
