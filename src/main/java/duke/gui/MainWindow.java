package duke.gui;

import duke.Duke;
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
    // @FXML annotation marks a private or protected member and makes it accessible
    // to FXML despite its modifier. Without the annotation, we will have to make
    // everything public and expose our UI to unwanted changes.
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;

    // User icon image obtained from
    // https://www.hiclipart.com/free-transparent-background-png-clipart-dlzoi/download
    private Image userIcon = new Image(
            this.getClass().getResourceAsStream("/images/user-icon-hiclipart.png"));
    // Mr Robot: https://www.usanetwork.com/mrrobot
    private Image mrRobotIcon = new Image(
            this.getClass().getResourceAsStream("/images/mr-robot-logo.jpg"));

    @FXML
    public void initialize() {
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
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userIcon),
                DialogBox.getDukeDialog(response, mrRobotIcon)
        );
        userInput.clear();
    }
}
