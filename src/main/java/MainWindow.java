import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.Timer;
import java.util.TimerTask;

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
    private GuiUi ui = new GuiUi();

    private Image startUpImage = new Image(this.getClass().getResourceAsStream("images/startUp.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("images/DaDuke.png"));
    private Image invalidImage  = new Image(this.getClass().getResourceAsStream("images/wrongCommand.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        duke = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply
     * and then appends them to the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = duke.getResponse(input);

        if (response.contains("OOPS!!!")) {
                dialogContainer.getChildren().addAll(
                        DialogBox.getUserDialog(input, userImage),
                        DialogBox.getDukeDialog(response, invalidImage)
                );
        } else {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        }

        userInput.clear();

        if (response.contains("Bye")) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    Platform.exit();
                    System.exit(0);
                }
            }, 1500);
        }
    }

    /**
     * Prints the start-up greeting of Duke, including any error faced during start-up.
     */
    @FXML
    public void startUp() {
        String output = ui.getGreeting();
        String error = duke.prepareList();

        if (!error.isEmpty()) {
            output = output.concat(error);
        }

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(output, startUpImage));
    }
}