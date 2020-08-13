package duke;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.tasks.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    /**
     * Initializes and creates new Duke object.
     */
    public Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage();
            taskList = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Processes user input and returns a string containing response of Duke program.
     * @param input user input.
     * @return string to be printed onto chat box GUI.
     */
    public String getResponse(String input) {
        try {
            assert !input.isEmpty() : "YOU DIDN'T SAY ANYTHING!!";
            Command c = Parser.parseCommand(input);
            if (c instanceof ByeCommand) {
                System.exit(0);
            }
            return c.execute(taskList, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}