package duke;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/charlie.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/lucy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the chat-bot for this main window.
     *
     * @param d the chat-bot instance.
     */
    public void setDuke(Duke d) {
        duke = d;

        if (!d.isLoaded()) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(duke.getLoadingError(), dukeImage)
            );
        }

        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getWelcome(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );

        if (!duke.isExit()) {
            String response = duke.getResponse(input);
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(response, dukeImage)
            );
        }

        userInput.clear();
    }

    /*
    TODO: Replace adding dialog boxes with a varags method

    private void addDialogBox(DialogBox... dialogBoxes) {
        dialogContainer.getChildren().addAll(dialogBoxes);
    }
    */
}