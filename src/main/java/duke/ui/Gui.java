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
import duke.exceptions.DukeException;

/**
 * Controller for the Main Window of the GUI for Duke.
 */
public class Gui extends AnchorPane implements Ui {
    // Solution below adapted from JavaFx Tutorial by Jeffry Lum.
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
     * Configures Duke to accept self as Ui and self's semaphore for
     * synchronisation.
     */
    public void setDuke(Duke duke) {
        duke.useUi(this);
        duke.addSemaphore(inputLock);
    }

    @FXML
    public void showReply(String reply) {
        Platform.runLater(() -> addDialog(reply, true));
    }

    @FXML
    public void showError(String error) {
        Platform.runLater(() -> addDialog(error, true));
    }

    @FXML
    public void showGreeting() {
        String greeting = "Hello! I'm Duke\nGive me a moment while I locate your save file...";
        Platform.runLater(() -> addDialog(greeting, true));
    }

    /**
     * Adds a new DialogBox containing the given text to the dialogContainer.
     * 
     * @param text Text to be displayed.
     * @param isDuke Determines whether to add Duke DialogBox or User DialogBox.
     */
    private void addDialog(String text, Boolean isDuke) {
        try {
            if (isDuke) {
                dialogContainer.getChildren().add(DialogBox.getDukeDialog(text, dukeImage));
            } else {
                dialogContainer.getChildren().add(DialogBox.getUserDialog(text, userImage));
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Obtains input from the user.
     */
    @FXML
    public String getInput() {
        try {
            inputLock.acquire();
        } catch (InterruptedException e) {
            showError("Interrupted!");
        }
        String input = userInput.getText();
        Platform.runLater(() -> userInput.clear());
        Platform.runLater(() -> addDialog(input, false));
        inputLock.release();
        return input;
    }

    /**
     * Shuts down the Gui.
     */
    public void shutDown() {
        try {
            Thread.sleep(1000);
            Platform.exit();
        } catch (InterruptedException e) {
            showError("Interrupted!");
        }
    }

    /**
     * Passes a "bye" input to Duke's CommandHandler.
     */
    public void bye() {
        userInput.setText("bye");
        inputLock.release();
    }
}