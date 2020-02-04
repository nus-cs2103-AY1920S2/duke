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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image gooseImage = new Image(this.getClass().getResourceAsStream("/images/DaGoose.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setGoose(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getGooseDialog(response, gooseImage)
        );
        userInput.clear();
    }

    @FXML
    private String getResponse(String input) {
        boolean isBye = false;
        while (!isBye) {
            isBye = Parser.isBye(input);

            try {
                String command = Parser.parseCommand(input);
                if (command.equals("list")) {
                    return "Pretend that Goose showing list here.";
                } else if (command.equals("done")) {
                    return "Okay, pretend Goose marked task as done.";

                } else {
                    if (command.equals("todo")) {
                        return "Okay, goose added todo. Honk! You're welcome.";

                    } else if (command.equals("event")) {
                        return "Okay, goose added event. Honk! You're welcome.";

                    } else if (command.equals("deadline")) {
                        return "Okay, goose added deadline. Honk! You're welcome.";
                    }
                }
            } catch (GooseUnrecognisedException e) {
                return e.getMessage() + String.format(" Goose no recognise \"%s\"", input);
            }
        }
        return "Honk honk!";
    }
}