import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * MainWindow
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 31 Jan 2020
 *
 */

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * @author Mario Lorenzo
 */

public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the window.
     */

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Stores the Duke instance in a variable.
     * @param d The Duke instance.
     */

    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(getWelcomeMessage(), dukeImage),
                DialogBox.getDukeDialog(getResponse("reminder"), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        checkExit(input);
    }

    /**
     * Gets the output result from Duke.
     * @param input The command input from the client.
     * @return The resulting output message from Duke.
     */

    private String getResponse(String input) {
        return duke.processCommand(input);
    }

    /**
     * Gets the welcome message from Duke.
     * @return The welcome message.
     */

    private String getWelcomeMessage() {
        return "Hi! I'm Duke (actually, my real name is Alice lol). Please enter your command!";
    }

    /**
     * Checks the input whether it is a bye command. Close the
     * app if it is.
     * @param input The string input by the command.
     */
    private void checkExit(String input) {
        if (input.equals("bye")) {
            Stage stage = (Stage) scrollPane.getScene().getWindow();
            stage.close();
        }
    }
}