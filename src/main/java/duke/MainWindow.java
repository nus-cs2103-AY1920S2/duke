package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for duke.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    // GUI Controls.
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    // GUI support.
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    // Non-GUI members.

    // We can use this Duke to perform as a chat engine.
    private Duke duke;

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        String userText = getUserResponse(userInput.getText());
        String dukeText = getDukeResponse(userInput.getText());

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Provides user input with user as the author.
     */
    private String getUserResponse(String input) {
        return "You: " + input;
    }

    /**
     * Generates a response for Duke to the user input.
     */
    private String getDukeResponse(String input) {
        return "Duke: " + EDIT THIS;
}
    ///////////////////////// EDIT THIS ////////////////////////////////////


}