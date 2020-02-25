import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

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

    private GuiDuke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Initializes the window with the FXML file.
     */
    @FXML
    public void initialize() {
        // set width to follow scroll pane
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.getStyleClass().addAll("scrollContent");
    }

    /**
     * Sets the given Duke as the logic of the chatbot.
     *
     * @param d The instance of Duke to be used as the logic
     */
    public void setDuke(GuiDuke d) {
        duke = d;

        // show welcome message
        duke.ui.showWelcome();
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(duke.ui.getResponse(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.isEmpty()) {
            return;
        }

        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        // quit the app in another thread to allow UI to update first
        new Thread(() -> {
            if (duke.isExit(input)) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    // suppress
                }
                Platform.exit();
            }
        }).start();
    }
}
