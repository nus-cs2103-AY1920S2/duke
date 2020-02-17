package duke.ui;

import duke.Duke;
import duke.exception.IncorrectCommandException;
import duke.exception.StorageOperationException;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import static duke.common.Messages.WELCOME_MSG;

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

    private Duke duke;

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Set the duke application instance and the window instance of this application.
     *
     * @param d The duke instance.
     */
    public void setDuke(Duke d) {
        duke = d;
        showMessagesToUser(WELCOME_MSG);
        try {
            duke.initStorage();
        } catch (StorageOperationException e) {
            showMessagesToUser(e.getMessage());
            d.enterStorageErrorMode();
        }
    }

    public void showMessagesToUser(String message) {
        dialogContainer.getChildren().add(new DukeDialogBox(message));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        dialogContainer.getChildren().add(new UserDialogBox(input));
        showMessagesToUser(getResponse(input));
        userInput.clear();
    }

    private String getResponse(String input) {
        try {
            return duke.parseAndExecuteSingleCommand(input);
        } catch (StorageOperationException | IncorrectCommandException e) {
            return e.getMessage();
        }
    }

}
