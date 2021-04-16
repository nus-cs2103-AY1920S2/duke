package duke;

import duke.core.Parser;
import duke.core.Storage;
import duke.core.Ui;
import duke.command.Command;
import duke.task.TaskList;

import duke.exception.DukeException;

import java.io.File;
import java.io.IOException;
import java.lang.Exception;

/**
 * Main class for the Duke chatbot application.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    /**
     * Constructs a fresh Duke instance with a supplied file storing the task list.
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser();
        ui = new Ui();
        File file = new File(Storage.FILEPATH);

        try {
            file.createNewFile();
        } catch (IOException e) {
            Ui.printLines("File creation failed.");
        }
    }

    /**
     * Retrieves Duke's response to the user input.
     * @param input The user input.
     * @return Duke's response.
     */
    public String getResponse(String input) {
        assert input.length() > 0 : "Input cannot be empty";

        String result = "";

        try {
            Command command = parser.parseCommand(input);
            result += command.execute(tasks, ui, storage);
        } catch (DukeException e) {
            result = e.getMessage();
        } catch (Exception e) {
            result = "Sorry, a mysterious unknown error occured:\n"
                    + e.getClass().getCanonicalName() + "\nPlease try again!";
        } 

        return result;
    }
}