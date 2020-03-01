import exceptions.IllegalDateTimeFormatException;
import exceptions.InvalidStorageFilePathException;
import exceptions.NoDescriptionException;
import exceptions.StorageOperationException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke_waving.svg.png"), 80, 80, false, false);

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setDuke(Duke d) {
        try {
            duke = d;
            duke.start();
        } catch (InvalidStorageFilePathException | IOException e) {
            throw new RuntimeException(e);
        } catch (StorageOperationException | NoDescriptionException | IllegalDateTimeFormatException err) {
            dukeSpeak(err.getMessage());
        }
    }

    public void dukeSpeak(String input) {
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(input, dukeImage)
        );
    }

    private void userSpeak(String input) {
        dialogContainer.getChildren().add(
                DialogBox.getUserDialog(input, userImage)
        );
    }

    /**
     * exit with status 0.
     */
    private void exit() {
        Platform.exit();
        System.exit(0);
    }

    private void halt(int numSeconds) {
        try {
            TimeUnit.SECONDS.sleep(numSeconds);
        } catch (InterruptedException e) {
            dukeSpeak(e.getMessage());
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText().trim();
        String response = duke.getResponse(input);
        userSpeak(input);
        dukeSpeak(response);
        userInput.clear();

        if (Duke.isExitKey(input)) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    exit();
                }
            }, 1000);
        }
    }
}