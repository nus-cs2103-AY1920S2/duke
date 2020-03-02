import commons.Duke;
import commons.Messages;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import logic.LogicManager;
import logic.command.CommandException;
import logic.command.CommandResult;
import logic.command.ExitCommand;
import logic.command.HelpCommand;
import logic.parser.exceptions.ParserException;

import java.io.IOException;

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
    private LogicManager logicManager;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/User.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/Duke.png"));

    /**
     * Set scroll pane nodes to be resized to match the height and width of the ScrollPane's viewport.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        start();
    }

    public void setDuke(Duke d) {
        duke = d;
        logicManager = d.getLogicManager();
    }

    public void setLogicManager(LogicManager logicManager) {
        this.logicManager = logicManager;
    }

    /**
     * Displays a welcome message when the programme starts.
     */
    public void start() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Messages.START, dukeImage)
        );
    }

    /**
     * Exits the programme after saving file.
     */
    @FXML
    public void handleExit() {
        try {
            duke.end();
            System.exit(0);
        } catch (IOException ioe) {
            System.err.println(ioe.getMessage());
        }
    }

    /**
     * Clears chat history.
     */
    @FXML
    public void clear() {
        dialogContainer.getChildren().clear();
    }

    /**
     * Displays command list and usage to user.
     */
    @FXML
    public void help() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(HelpCommand.HELP, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input);
        if (response.equals(ExitCommand.COMMAND_WORD)) {
            handleExit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    /**
     * Passes input to logic manager and retrieves response.
     *
     * @param input user input.
     * @return response from logic manager.
     */
    private String getResponse(String input) {
        try {
            CommandResult result = logicManager.execute(input);
            if (result.isExit()) {
                handleExit();
            }
            return result.getFeedbackToUser();
        } catch (CommandException | ParserException e) {
            return e.getMessage();
        }
    }
}