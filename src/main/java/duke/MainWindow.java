package duke;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
        scrollPane.setVisible(true);
    }

    /**
     * Sets up duke for this window.
     * @param d The Duke chat bot.
     */
    public void setDuke(Duke d) {
        duke = d;
        String input = "Hello, my name is Duke!\nWhat can I do for you?";
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(input, dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        assert !response.equals("");
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
        checkExit(response);
    }

    /**
     * Checks whether the chat bot has delivered its farewell message.
     * @param response The response form chat bot.
     */
    private void checkExit(String response) {
        if (response.equals(Ui.showBye())) {
            Stage stage = (Stage) scrollPane.getScene().getWindow();
            stage.close();
        }
    }
}