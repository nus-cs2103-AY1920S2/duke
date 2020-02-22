package duke.ui;

import duke.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    private static final String INIT_FAIL_ERROR_MESSAGE = "Due to initialization error"
            + ", please exit the application and fix them before relaunching.";
    /** ScrollPane object of the UI.*/
    @FXML
    private ScrollPane scrollPane;
    /** VBox object of the UI.*/
    @FXML
    private VBox dialogContainer;
    /** TextField object of the UI.*/
    @FXML
    private TextField userInput;
    /** Main program logic. */
    private Duke duke = new Duke();
    /** Image for the user. */
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    /** Image for the program. */
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initialises the main application window and prints welcome message and reminders.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        if (duke.getHasInitError()) {
            dialogContainer.getChildren().add(
                    DialogBox.getDukeDialog(duke.getUiMessage(), dukeImage));
            return;
        }
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getWelcomeMessage(), dukeImage));
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getReminder(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * the program's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = "";

        //Ensure that program only allows quitting when there is initialization error to protect save file.
        if (duke.getHasInitError() && !input.equals("bye")) {
            response = INIT_FAIL_ERROR_MESSAGE;
        } else {
            response = duke.getResponse(input);
        }

        //append dialog boxes to dialog container and clear user input.
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        //exit the program if required.
        if (input.equals("bye")) {
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                Platform.exit();
            }).start();
        }
    }
}
