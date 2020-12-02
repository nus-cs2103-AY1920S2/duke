package duke.gui;

import duke.Duke;
import javafx.application.Platform;
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
    // Mr Robot: By USA Network - USA Network, Public Domain,
    // https://commons.wikimedia.org/w/index.php?curid=49685837
    private Image mrRobotIcon = new Image(
            this.getClass().getResourceAsStream("/images/Mr-Robot-Logo.png"));

    /**
     * GUI start up.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        // Get greeting message and display it
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(greeting(), mrRobotIcon));
    }

    /**
     * Returns a String representing the GUI's greeting message.
     *
     * @return String used for greeting user
     */
    public String greeting() {
        String newline = System.lineSeparator();
        return "Hello friend. Hello friend?" + newline
                + "That's lame. Maybe I should give you a name." + newline
                + "But that's a slippery slope, you're only in my head," + newline
                + "we have to remember that.";
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
        if (response.equals("Goodbye friend.")) {
            Platform.exit();
        }
    }
}
