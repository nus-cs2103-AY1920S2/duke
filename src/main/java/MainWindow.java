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
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Duke duke;
    // Images retrieved from:
    // https://vignette.wikia.nocookie.net/characters/images/7/7f/Tom_Cat.jpg/revision/latest/scale-to-width-down/340?cb=20190404160415
    // https://lh3.googleusercontent.com/proxy/l-96E2T1rPzS0xBTgaZZNfYQ4f2GymIie60JMw3oY1RvEUpqmqap19OvLhmJeZgh7oA5vEak_TYbOOVdhl0YMw3q26H4G27TOsgwSCzMS1UpUGlHs_BZTQP_wdGSY6PTpxaoE22FBzgFehLs
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.jpg"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.jpg"));

    /**
     * This method initialises the main window of the GUI that is responsible for
     * user interaction.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog("Hello I am Tom. What can I do for you today?"
                                + "\n Type 'help' to see the list of commands that I accept",
                        dukeImage));
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
        assert !input.isEmpty() : "User input should not be empty";
        assert !response.isEmpty() : "Duke response should not be empty";

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();

        // //Solution below adapted from
        // https://stackoverflow.com/questions/52393982/javafx-problem-with-platform-runlater-delayed-rendering-of-canvas-graphic
        if (response.startsWith("Bye-bye!")) {
            Ui ui = new Ui();
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException exc) {
                    ui.showError(exc.getMessage());
                }
                Platform.exit();
            }).start();
        }
    }

}