package duke;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Controller for duke.MainWindow. Provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {

    // GUI Controls
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    // Supporting Images for Duke and User display photos
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDukeCustom.png"));

    /**
     * Initialises the Main Window for the Application, prompting user to use Duke.
     * Sets size constraints, then presents visual cues to prompt the user to use the program.
     */
    @FXML
    public void initialize() {

        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.welcome(), dukeImage)
        );

    }

    /**
     * We can use this Duke to perform as a chatbot.
     */
    private Duke duke;

    /**
     * Sets the Duke of choice to be the Duke chatbot handling the user inputs.
     *
     * @param d Duke chosen to handle user inputs.
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {

        // User and Duke query and response come as a pair
        String userText = getUserResponse(userInput.getText());
        String dukeText = getDukeResponse(userInput.getText());

        // Both the user and Duke's messages are required to be put into a container (VBox)
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(dukeText, dukeImage)
        );

        // Clear the user input
        userInput.clear();
    }

    /**
     * Provides user input with user as the author.
     *
     * @param input input entered by the user.
     * @return String to represent input entered by the user.
     */
    private String getUserResponse(String input) {
        return "You: " + input;
    }

    /**
     * Generates a response for Duke to process the user input.
     *
     * @param input user input entered for Duke to respond to.
     * @return Duke's response after processing the user input.
     */
    private String getDukeResponse(String input) {

        String result = "\n";

        // automatically exit leaving Ui's goodbye message
        if (input.equals("bye")) {
            Timer timer = new Timer();
            TimerTask exitTask = new TimerTask() {
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            };
            timer.schedule(exitTask,3000);
        }

        result += (input.equals("bye")) ? Ui.goodbye() : duke.run(input);

        assert (result != null || result.equals(" ")) : "Duke has to reply something, which is currently missing.";

        return "E-1337: " + result;
    }

}