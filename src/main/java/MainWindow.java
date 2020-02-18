import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/LuLu.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/ChuChu.jpg"));

    /**
     * Initialises the window.
     */
    @FXML
    public void initialize() {


        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog("Hello! I'm Chu Chu \n What can I do for you ? \n",
                dukeImage));
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
        String input = userInput.getText();
        String response = "";

        try {
            response = duke.getResponse(input);

        } catch (Exception e) {

            response = "Input cannot be empty";
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage));
        userInput.clear();

        if (duke.getIsExit()) {

            PauseTransition pause = new PauseTransition(Duration.seconds(2.0));
            pause.setOnFinished(event -> Main.end());
            pause.play();
        }

    }
}
