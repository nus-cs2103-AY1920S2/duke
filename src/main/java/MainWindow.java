import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
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
    @FXML
    public ToggleButton toggleButton;
    private String lastInput;
    private Duke duke;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/Kapoo2.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Bunny2.png"));


    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        sendWelcomeText();
    }

    /**
     * Toggles the background colour from light to dark theme.
     */
    @FXML
    public void changeColour() {
        if (dialogContainer.getStyle().toString().equals("-fx-background-color: #f5e4ed;")) {
            dialogContainer.setStyle("-fx-background-color: #d14f6e;");
        } else {
            dialogContainer.setStyle("-fx-background-color: #f5e4ed;");
        }
    }

    /**
     * Function to exit the application. Activates when the exit button is pressed.
     */
    @FXML
    public void exitApplication() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.exit();
        System.exit(0);
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Sends the welcome text via the bot to the user.
     */
    public void sendWelcomeText() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Ui.welcomeMessage(), dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            response = duke.getResponse(input);
        } catch (Exception e) {
            response = "There is an error";
            e.printStackTrace();
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }
}