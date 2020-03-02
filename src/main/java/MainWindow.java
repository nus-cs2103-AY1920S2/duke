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
import logic.parser.exceptions.ParserException;

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

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);
        start();
    }

    public void setDuke(Duke d) {
        duke = d;
        logicManager = d.getLogicManager();
    }

    public void setLogicManager(LogicManager logicManager) {
        this.logicManager = logicManager;
    }

    public void start() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Messages.START, dukeImage)
        );
    }

    @FXML
    public void handleExit() {
        duke.end();
        System.exit(0);
    }

    @FXML
    public void clear() {
        dialogContainer.getChildren().clear();
    }

    @FXML
    public void help() {
        dialogContainer.getChildren().addAll(
                DialogBox.getDukeDialog(Messages.HELP, dukeImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = getResponse(input, duke);
        if (response.equals(ExitCommand.COMMAND_WORD)) {
            handleExit();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getDukeDialog(response, dukeImage)
        );
        userInput.clear();
    }

    private String getResponse(String input, Duke duke) {
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