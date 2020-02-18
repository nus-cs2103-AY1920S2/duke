package duke.ui;

import duke.Duke;
import duke.exception.DukeException;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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
     * Sets the duke to the specified duke.
     *
     * @param duke The specified duke.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
    }

    /**
     * Handles the event when the window of application is closed.
     */
    public void handleCloseWindowEvent() {
        try {
            duke.getStorage().saveTasksToStorage(duke.getTasks());
        } catch (DukeException e) {
            dialogContainer.getChildren().addAll(
                    DialogBox.getDukeDialog(e.getMessage(), dukeImage)
            );
        }
    }

    /**
     * Initialises the scrollPane and the dialog box to show welcome message.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.generateWelcomeMessage(), dukeImage)
        );
    }


    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage)
        );
        //pause awhile before duke replies.
        PauseTransition pause = new PauseTransition(Duration.seconds(0.25));
        pause.setOnFinished(event -> dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(response, dukeImage)
        ));
        pause.play();

        userInput.clear();
        if (duke.isExit()) {
            //pause awhile before duke closes the application.
            pause = new PauseTransition(Duration.seconds(0.75));
            pause.setOnFinished(event -> Platform.exit());
            pause.play();
        }
    }

}