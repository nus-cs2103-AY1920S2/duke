import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;

/**
 * Controller for MainWindow
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

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.jpeg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/MrDuke.jpeg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getDukeDialog("Hello! I'm Duke. What can I do for you today?",
                dukeImage));
    }

    public void setDuke(Duke d) {
        duke = d;
    }


    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        if (response.startsWith("Bye")) {
            Thread curr = Thread.currentThread();
            try {
                curr.sleep(2000);
            } catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }
            Platform.exit();
        }
    }
}
