import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;

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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/BugCat.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Capoo.png"));

    @FXML
    public void initialize() throws IOException {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(greetings(), dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() throws IOException, DukeException {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Returns string with welcome message.
     * @return string containing welcome message.
     */
    public static String greetings() {
        String tinga = "         __\n"
                + "     _|    |_    ○\n"
                + "    |_       _\\    _      _   ___        _____          ___  __\n"
                + "       |    |      |   \\  |    '       \\   /     __    \\    /     _ `   |\n"
                + "       |    |      |    | |    |  |    | |    |__|    | |    (__|    |\n"
                + "       |__|      |__| |__|  |__|   \\____     |   \\____, __|\n"
                + "                                                /  \\__|   |\n"
                + "                                                \\______/\n";
        tinga += "Hey there! I'm tinga :)\n";
        tinga += "What can I do for you?\n";
        tinga += "Bugcat capoo images by Yara\n";
        return tinga;
    }
}