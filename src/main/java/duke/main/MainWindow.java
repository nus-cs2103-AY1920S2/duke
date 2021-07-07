package duke.main;

import javafx.animation.PauseTransition;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Steve Jobs.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Bill Gates.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());

        dialogContainer.setSpacing(20);

        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.showWelcome(), dukeImage)
        );
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates dialog boxes, one displaying the user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     * If the user inputs the 'bye' command, application will shut down after 2 seconds.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        if (duke.getExitStatus()) {
            delay.setOnFinished(event -> Main.closeWindow());
            delay.play();
        }
    }
}
