package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.logic.Parser;
import duke.logic.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;
import javafx.application.Platform;

/**
 * Duke is a friendly chatbot who will keep track of your tasks
 * and whether they have been completed.
 */
public class Duke {

    private static final String filePath = "data/tasks.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean canLoad = true;
    private String errorMsg; // For debug

    /**
     * Creates a Duke object and initialises Ui, Storage and TaskList.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            canLoad = false;
            errorMsg = e.getMessage();
            tasks = new TaskList();
        }
    }

    /**
     * Returns the message that Duke will show the user once it is initialised.
     * @return Duke's first message.
     */
    public String getFirstMsg() {
        if (canLoad) {
            return ui.getWelcome();
        } else {
            return ui.getError(errorMsg);
            //return ui.getLoadingError();
        }
    }

    /**
     * Returns Duke's response to user input.
     * @param input User input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        boolean isExit;
        String response = "";
        try {
            Command c = Parser.parse(input);
            response = c.execute(tasks, ui, storage);
            isExit = c.isExit();
            if (isExit) {
                Platform.exit();
            }
        } catch (DukeException e) {
            response = ui.getError(e.getMessage());
        }
        return response;
    }
}
