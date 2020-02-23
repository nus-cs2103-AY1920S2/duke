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

    @FXML
    private Duke duke;
    @FXML
    private Ui ui = new Ui();

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/gudetama2.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/gudetama1.png"));
    private Image dukeTroubled = new Image(this.getClass().getResourceAsStream("/images/gudetama-troubled.png"));
    private Image dukeDone = new Image(this.getClass().getResourceAsStream("/images/gudetama-done.png"));
    private Image dukeBye = new Image(this.getClass().getResourceAsStream("/images/gudetama-bye.png"));
    private Image dukeFind = new Image(this.getClass().getResourceAsStream("/images/gudetama-find.png"));
    private Image dukeTask = new Image(this.getClass().getResourceAsStream("/images/gudetama-meh.png"));

    /**
     * Initialize the dialogbox with the welcome message.
     */
    @FXML
    public void initialize() {
        dialogContainer.getChildren().addAll(
            DialogBox.getDukeDialog(ui.welcomeMessage(), dukeImage)
        );
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String cmd = userInput.getText();
        String response = duke.getResponse(cmd);
        boolean isTodo = cmd.contains("todo");
        boolean isEvent = cmd.contains("event");
        boolean isdeadline = cmd.contains("deadline");

        if (cmd.contains("bye")) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(cmd, userImage),
                DialogBox.getDukeDialog(response, dukeBye)
            );
            System.exit(0); //exits program
        } else if (response.contains("OOPS") || response.contains("Invalid")) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(cmd, userImage),
                DialogBox.getDukeDialog(response, dukeTroubled)
            );
            userInput.clear();
        } else if (cmd.contains("done") || cmd.contains("delete")) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(cmd, userImage),
                DialogBox.getDukeDialog(response, dukeDone)
            );
            userInput.clear();
        } else if (response.contains("matching")) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(cmd, userImage),
                DialogBox.getDukeDialog(response, dukeFind)
            );
            userInput.clear();
        } else if (isTodo || isEvent || isdeadline) {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(cmd, userImage),
                DialogBox.getDukeDialog(response, dukeTask)
            );
            userInput.clear();
        } else {
            dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(cmd, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
            );
            userInput.clear();
        }
    }

}