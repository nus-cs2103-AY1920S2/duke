package duke.gui;

import duke.Duke;
import javafx.application.Platform;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialise the scrollPane.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set Duke object to current duke and print the display message to the user with the dukeImage.
     *
     * @param d Duke object to be stored
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(DialogBox.getDukeDialog(d.getGreeting(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     * Finally exit the window if response equals to the goodbye message.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        showDialogBoxes(input, response);
        userInput.clear();

        checkIsExit(response);
    }

    /**
     * Show both user dialog box and Duke dialog box with the images and message printed in GUI.
     *
     * @param userInput Input from user
     * @param response  Reply message to user
     */
    private void showDialogBoxes(String userInput, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
    }

    /**
     * Check whether is it goodbye message, if yes, close gui.
     *
     * @param response Message reply to user
     */
    private void checkIsExit(String response) {
        if (response.equals("     Alright! See you next time!\n")) {
            Platform.exit();
        }
    }
}
