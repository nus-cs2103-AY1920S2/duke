import commons.UI;
import commons.Duke;
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

    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        start();
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    public void start() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(UI.START, dukeImage)
        );
    }

    @FXML
    public void exit() {
        duke.end();
        System.exit(0);
    }

    @FXML
    public void clear() {
        dialogContainer.getChildren().clear();
    }

    @FXML
    public void help() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(UI.HELP, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input, duke);
        if (response.equals(UI.BYE)) {
            exit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    private String getResponse(String input, Duke duke) {
        return duke.getController().readInput(input, duke.getTaskList(), duke.getFriendlierSyntax());
    }
}