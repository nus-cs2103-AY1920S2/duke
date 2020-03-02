package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Initializes the setting and prepare respond to user input.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Setup the list of task based on the local file.
     *
     * @param filePath The filepath of the lacal file to store the tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        } catch (Exception e) {
            ui.showError(new DukeException("Unknown Error, Please try to restart the application!"));
        }
    }

    /**
     * Return the response based on user input.
     *
     * @param input user input from the GUI
     * @return return the response from the system
     */
    public String getResponse(String input) {
        //Solution below adapted from https://stackoverflow.com/questions/8708342
        ByteArrayOutputStream respond = new ByteArrayOutputStream();
        PrintStream uiOutput = new PrintStream(respond);
        final PrintStream systemOutput = System.out;
        System.setOut(uiOutput);

        assert tasks != null : "The tasks should be initialise at this stage";
        assert ui != null : "The Ui object should be initialise at this stage";
        assert storage != null : "The storage object should be initialise at this stage";

        try {
            Command c = Parser.parse(input);
            c.execute(tasks, ui, storage);
        } catch (DukeException ex) {
            ui.showError(ex);
        }

        System.out.flush();
        System.setOut(systemOutput);
        return respond.toString();
    }
}
