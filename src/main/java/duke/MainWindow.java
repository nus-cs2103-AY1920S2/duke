package duke;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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
    private Stage stage;


    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/photo1.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/photo2.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        welcomeMsg();
    }

    /**
     * Sets variable duke as current duke.
     * @param d current duke
     */
    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Sets variable stage as current stage.
     * @param s current stage
     */
    public void setStage(Stage s) {
        stage = s;
    }

    /**
     * Shows the welcome message in a DialogBox when the bot starts.
     */
    public void welcomeMsg() {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("    Hey there! I'm DingDing!\n"
                + "    What's up today? ;D", dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws Exception {
        String input = userInput.getText();
        String response = duke.getResponse(input);


        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (input.equals("bye")) {
            exit();
        }
    }

    private void exit() {
        PauseTransition pause = new PauseTransition(Duration.seconds(4));
        pause.setOnFinished(event -> stage.close());
        pause.play();
    }
}