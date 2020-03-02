import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class serves as the controller for the MainWindow
 * FXML file, which contains the layout design of the root node.
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
    private final String BYE_MESSAGE = "    Bye. Hope to see you again soon!\n";

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DukeImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Interacts with the Duke class, the main logical
     * control centre of the program. Also initialises the
     * application with the welcome message.
     * @param d The Duke object running the program.
     */
    public void setDuke(Duke d) {
        duke = d;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getWelcomeMessage(), dukeImage)
        );
        assert dialogContainer.getChildren().size() > 0 : "Welcome message missing";
    }

    /**
     * Creates two dialog boxes in response to user input -
     * the first echoing the user's input and the second containing
     * Duke's reply - and appends them to the dialog container.
     * The user's input is cleared after processing.
     * Also, the method does not respond if the text field is empty,
     * even if the Enter key or Send button is pressed.
     * Finally, this method closes the application when Duke
     * outputs its goodbye message.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        if (input.equals("")) {
            return;
        }

        String response = duke.getResponse(input);
        userInput.clear();
        Node userDialog = DialogBox.getUserDialog(input, userImage);
        Node dukeDialog = DialogBox.getDukeDialog(response, dukeImage);
        dialogContainer.getChildren().addAll(userDialog, dukeDialog);

        if (response.equals(BYE_MESSAGE)) {
            TimerTask exit = new TimerTask() {
                @Override
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            };
            Timer timer = new Timer();
            timer.schedule(exit, 1000);
        }
    }
}
