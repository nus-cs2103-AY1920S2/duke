import core.Duke;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

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
    private static final String TERMINATE = "bye";
    private static final String WELCOME_MESSAGE = "A long time ago in a galaxy far, far away...\n"
            + "Hello! I'm Luke\n"
            + "How may i serve you.";
    private static final long delayDuration = 2000;
    private boolean disableInput = false;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/stormtrooper.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/luke.png"));


    /**
     * Initializes the duke with welcome message.
     */
    @FXML
    public void initialize() {
        startUp();
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing
     * Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        if (!disableInput) {
            String input = userInput.getText();
            String response = duke.getResponse(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
            if (input.equals(TERMINATE)) {
                disableInput = true;
                exit();
            }
        }
    }

    /**
     * Displays the welcome message during the start up.
     */
    private void startUp() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(WELCOME_MESSAGE, dukeImage)
        );
    }

    /**
     * Delays the exit and give time to display the exit message.
     */
    private void exit() {
        new Timer().schedule(new TimerTask() {
            public void run() {
                Platform.exit();
            }
        }, delayDuration);
    }
}