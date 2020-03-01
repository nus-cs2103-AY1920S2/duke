package duke.ui;

import duke.Duke;
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

    // Image retrieved from https://gallery.yopriceville.com/Free-Clipart-Pictures/Cartoons-PNG/
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/charlie.png"));
    // Image retrieved from https://gallery.yopriceville.com/Free-Clipart-Pictures/Cartoons-PNG/
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/lucy.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the chat-bot for this main window.
     *
     * @param duke the chat-bot instance.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    public void init() {
        if (!this.duke.isLoaded()) {
            addDialogBox(DialogBox.getDukeDialog(duke.getLoadingError(), dukeImage));
        }

        addDialogBox(DialogBox.getDukeDialog(duke.getWelcome(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        addDialogBox(DialogBox.getUserDialog(input, userImage));

        if (!duke.isExit()) {
            String response = duke.getResponse(input);
            addDialogBox(DialogBox.getDukeDialog(response, dukeImage));
        }

        userInput.clear();
    }

    /**
     * Adds a list of dialog boxes.
     *
     * @param dialogBoxes a list of dialog boxes.
     */
    private void addDialogBox(DialogBox... dialogBoxes) {
        assert dialogBoxes.length > 0 : "Should be adding at least one dialog box,"
                + " not " + dialogBoxes.length;

        dialogContainer.getChildren().addAll(dialogBoxes);
    }
}
