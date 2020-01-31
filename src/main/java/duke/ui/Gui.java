package duke.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.concurrent.Semaphore;

import duke.Duke;

/**
 * Controller for the Main Window of the GUI for Duke.
 */
public class Gui extends AnchorPane implements Ui {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Semaphore inputLock;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        inputLock = new Semaphore(1);
    }

    /**
     * Releases the semaphore to allow getting user input.
     */
    @FXML
    private void handleUserInput() {
        inputLock.release();
    }

    /**
     * Configures Duke to accept self as Ui and self's semaphore for synchronisation.
     */
    public void setDuke(Duke duke) {
        duke.useUi(this);
        duke.addSemaphore(inputLock);
    }

    @FXML
    public void showReply(String reply) {
        Platform.runLater(() -> dialogContainer.getChildren().add(DialogBox.getDukeDialog(reply, dukeImage)));
    }

    @FXML
    public void showError(String error) {
        Platform.runLater(() -> dialogContainer.getChildren().add(DialogBox.getDukeDialog(error, dukeImage)));
    }

    @FXML
    public void showGreeting() {
        String greeting = "Hello! I'm Duke\nGive me a moment while I locate your save file...";
        Platform.runLater(() -> dialogContainer.getChildren().add(DialogBox.getDukeDialog(greeting, dukeImage)));
    }

    @FXML
    public String getInput() {
        try {
            inputLock.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String input = userInput.getText();
        Platform.runLater(() -> userInput.clear());
        Platform.runLater(() -> dialogContainer.getChildren().add(DialogBox.getUserDialog(input, userImage)));
        inputLock.release();
        return input;
    }

    public void shutDown() {
        try {
            Thread.sleep(1000);
            Platform.exit();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
    }
}