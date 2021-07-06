import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

//@@author {BryanYap972}-reused
//https://github.com/nus-cs2103-AY1920S2/duke/tree/master/tutorials - javaFX tutorials

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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets duke object.
     * @param d duke object
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Runs the bot and executes user commands.
     */
    @FXML
    public void handleUserInput() {
        String input = userInput.getText();

        if (input.equals("bye")) {
            String response = duke.run(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );

            Platform.exit();
            System.exit(0);
        } else {
            String response = duke.run(input);
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }

    /**
     * Shows welcome message when bot is launched.
     */
    @FXML
    public void showWelcome() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.getUi().showWelcome(), dukeImage)
        );
    }
}

//@@author