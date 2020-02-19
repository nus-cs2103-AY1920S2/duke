import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * Controller for MainWindow. Provides the layout for the other controls.
 * A UI Controller class. For UI-related code.
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaIU.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaParkSeoJun2.jpg"));

    /**
     * Bind the dialog container to the button of the scroll pane, and greet the user.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Links the Duke instance and greets the user.
     *
     * @param d The duke instance to link.
     */
    public void setDuke(Duke d) {
        duke = d;
        // Greet the user
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(new Ui().showHello(), dukeImage)
        );
    }

    /**
     * Listens to the input field and button and carries out the main of Duke.
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        String byeMessage = "Bye. Hope to see you again soon!\n";
        // Exit application programmatically if necessary
        if (response.equals(byeMessage)) {
            // Hacky way to get the stage
            Stage stage = (Stage) scrollPane.getScene().getWindow();
            stage.fireEvent(new WindowEvent(stage, WindowEvent.WINDOW_CLOSE_REQUEST));
        } else {
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(response, dukeImage)
            );
            // We can do this since we (1) Defined userInput in MainWindow.fxml (2) gave userInput the @FXML tag
            userInput.clear();
        }
    }
}
