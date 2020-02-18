package duke.gui;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.common.command.Command;
import duke.common.command.DummyCommand;

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

    private Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/github.png"));
    private Image dukeImage = new Image(this.getClass()
            .getResourceAsStream("/images/reddit.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Sets the duke of the display window and add a greeting from duke.
     * @param duke The Duke object used.
     */
    public void setDuke(Duke duke) {
        this.duke = duke;
        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(duke.getGreeting(), dukeImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other 
     * containing Duke's reply and then appends them to the dialog container. 
     * Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        Command command = new DummyCommand();
        String input = userInput.getText();
        String response;

        try {
            command = Parser.parse(input);
            response = duke.getResponse(command);
        } catch (DukeException e) {
            response = e.getMessage();
        }

        createDialogBoxes(response);

        if (command.isExit()) {
            exit();
        }
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other 
     * containing Duke's reply and then appends them to the dialog container. 
     * Clears the user input after processing.
     * @param response The response of duke.
     */
    @FXML
    private void createDialogBoxes(String response) {
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userInput.getText(), userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Exits the application after 1 second so that duke's exit response will
     * be shown.
     */
    private void exit() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        }).start();
    }
}