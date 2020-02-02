package duke.ui;

import duke.DukeException;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

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

    private String horizontalLine = "\n    ________________________________________________________\n";

    private Storage storage;
    private TaskList tasks;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/duke.png"));

    /**
     * Initialises Storage and TaskList used by Duke. Shows welcome message.
     */
    @FXML
    public void initialize() {
        storage = new Storage("data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        showWelcome();
    }

    /**
     * Executes the exit command and terminates the programme when the close button is pressed.
     */
    public void closeWindowButtonClicked() {
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        String output = horizontalLine;
        ExitCommand c = new ExitCommand();
        output += c.execute(tasks, storage) + horizontalLine;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(output, dukeImage));
        pause.setOnFinished(event -> {
            Platform.exit();
        });
        pause.play();
    }

    /**
     * Handles the user input. Parses user input and creates a command object, and executes the command.
     * Displays the output in DialogBox. If input is bye, it displays exit message and closes the
     * application after 1.5 seconds.
     */
    @FXML
    private void handleUserInput() {
        String output = horizontalLine;
        String input = userInput.getText();
        boolean isExit = false;
        try {
            Command c = Parser.parse(input);
            output += c.execute(tasks, storage);
            isExit = c.isExit();
        } catch (DukeException e) {
            output += "     " + e.getMessage();
        } finally {
            output += horizontalLine;
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, userImage),
                    DialogBox.getDukeDialog(output, dukeImage)
            );
            userInput.clear();
            if (isExit) {
                PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
                pause.setOnFinished(event -> {
                    Platform.exit();
                });
                pause.play();
            }
        }
    }

    /**
     * Shows welcome message when application starts.
     */
    @FXML
    private void showWelcome() {
        String logo = " ___            _\n"
                + "|  _  \\ _    _|  |   ___\n"
                + "| |  | | |  |  |  |/  / _  \\\n"
                + "| |_| | |_|  |      <  __/\n"
                + "|___/ \\__,_|_|\\_\\__|\n";
        String output = "Hello from\n" + logo + horizontalLine + "     Hello! I'm Duke\n     What can I do "
                + "for you?" + horizontalLine;
        dialogContainer.getChildren().addAll(DialogBox.getDukeDialog(output, dukeImage));
    }

}