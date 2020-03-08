package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;



/**
 * Duke is a chat bot that manages your tasks.
 */
public class Duke {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;
    static String path = "data/tasks.txt";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));

    /**
     * Creates a Duke object and initializes Ui, Storage and TaskList objects.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(path);
        try {
            this.taskList = new TaskList(storage.loadFile());
        } catch (IOException e) {
            ui.printFileNotFoundError();
            taskList = new TaskList();
        }
    }

    public Duke(String path) throws IOException {
        this.ui = new Ui();
        this.storage = new Storage(path);
        try {
            this.taskList = new TaskList(storage.loadFile());
        } catch (IOException e) {
            ui.printFileNotFoundError();
            taskList = new TaskList();
        }
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     *
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String dukeText = getResponse(userInput.getText());
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, user),
                DialogBox.getDukeDialog(dukeText, duke)
        );
        userInput.clear();
    }


    /**
     * Returns Duke's response in the GUI when user's input is parsed.
     * @param input User's input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            if (input.equals("bye")) {
                Executors.newSingleThreadScheduledExecutor().schedule(
                        () -> System.exit(0), 500, TimeUnit.MILLISECONDS);
            }
            return command.action(taskList, storage, ui);
        } catch (IOException | DukeException e) {
            return e.getMessage();
        }
    }
}