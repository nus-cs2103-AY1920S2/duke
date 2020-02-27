package duke;

//@@author j-lum-reused
//Reused from JavaFX Tutorial Part 4 with modifications
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * A controller for {@code MainWindow}, which provides the layout for the other controls.
 */
public class MainWindow extends AnchorPane {
    // Duke image taken from https://wiki.openjdk.java.net/display/duke/Gallery
    private static final Image DUKE_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/duke.png"));
    private static final Image USER_IMAGE = new Image(MainWindow.class.getResourceAsStream("/images/user.png"));
    private static final Color DUKE_COLOR = Color.GHOSTWHITE;
    private static final Color USER_COLOR = Color.ROYALBLUE;

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set the {@code Duke} object to be used.
     *
     * @param duke the {@code Duke} object to be used
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        String greeting = duke.getGreeting();
        dialogContainer.getChildren().add(DialogBox.getReceivedDialog(DUKE_IMAGE, greeting, DUKE_COLOR));
    }

    /**
     * Reads the user input and gets {@code Duke}'s response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            if (input.length() == 0) {
                return;
            }
            Duke.Command command = duke.getCommand(input);
            response = duke.getResponse(command, input);
            updateScreen(input, response);
            if (command.isTerminating()) {
                System.exit(0);
            }
        } catch (DukeException e) {
            response = duke.getExceptionMessage(e);
            updateScreen(input, response);
        }
    }

    /**
     * Creates two dialog boxes, one cantaining the user input and the other containing {@code Duke}'s response, and
     * appends them to the dialog container. Clears the user input after processing.
     */
    private void updateScreen(String input, String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getSentDialog(USER_IMAGE, input, USER_COLOR),
                DialogBox.getReceivedDialog(DUKE_IMAGE, response, DUKE_COLOR)
        );
        userInput.clear();
    }
}
//@@author
