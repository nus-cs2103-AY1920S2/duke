package duke.ui;

import duke.Duke;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import javafx.fxml.FXMLLoader;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    //@@author {thetruevincentchow}-reused
    //Reused from https://github.com/nus-cs2103-AY1920S2/duke/blob/master/tutorials/javaFxTutorialPart4.md with minor modifications
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Gui gui;

    private Image userImage = new Image(Duke.class.getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(Duke.class.getResourceAsStream("/images/DaDuke.png"));
    
    @FXML
    private void initialize() {
        //Scroll down to the end every time dialogContainer's height changes.
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        //dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    protected void setGui(Gui gui) {
        this.gui = gui;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = gui.getResponse(input);
        displayUserInput(input);
        displayDukeMessage(response);
        userInput.clear();
    }
    //@@author

    protected void displayUserInput(String input) {
        dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage));
    }
    
    protected void displayDukeMessage(String message) {
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(message, dukeImage));
    }
}
