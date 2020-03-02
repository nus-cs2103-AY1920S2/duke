package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

import duke.commons.exceptions.DukeException;
import duke.logic.TaskList;
import duke.logic.commands.Command;
import duke.logic.parser.Parser;
import duke.storage.Storage;
import duke.ui.DialogBox;
import duke.ui.Ui;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;

/**
 * Main class of the program.
 */
public class Duke {

    public boolean isTerminated = false;
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for <code>Duke</code>.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage("data/tasks.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            storage.initialiseData();
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Parses the input and executes the corresponding <code>Command</code>,
     * then returns the <code>String</code> message showing the result of the execution.
     *
     * @param input input <code>String</code> provided by user.
     * @return the <code>String</code> message showing the result of the execution.
     */
    public String getResponse(String input) {
        String output = "";
        try {
            Command c = Parser.parse(input);
            output = c.execute(tasks, ui, storage);
            if (c.isExit()) {
                isTerminated = true;
            }
        } catch (DukeException e) {
            output = ui.showCommandError(e.getMessage());
        }
        return output;
    }

    public static void main(String[] args) {
    }
}