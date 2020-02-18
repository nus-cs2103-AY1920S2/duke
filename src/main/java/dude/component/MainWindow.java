package dude.component;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * Exposes input and output methods for application logic classes (i.e. GUI) to interact with.
 * @@author j-lum-reused
 * Reused from tutorials/javaFxTutorialPart4.md with modifications
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        DialogBox userMessage = DialogBox.getUserDialog(getInput(), userImage);
        dialogContainer.getChildren().add(userMessage);
        duke.serve();
        userInput.clear();
    }

    /**
     * Obtains input from user.
     *
     * @return what user typed into TextField after pressing Send or hitting Enter.
     */
    public String getInput() {
        return userInput.getText();
    }

    /**
     * Creates DialogBox containing Duke's response and pushes it onto the dialogContainer.
     *
     * @param response the response to the user.
     */
    public void displayResponse(String response) {
        DialogBox reply = DialogBox.getDukeDialog(response, dukeImage);
        dialogContainer.getChildren().add(reply);
    }
}
