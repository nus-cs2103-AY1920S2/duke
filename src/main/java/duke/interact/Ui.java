package duke.interact;

import duke.Storage;
import duke.TaskList;
import duke.command.Command;
import duke.exception.DukeException;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    private Parser parser;

    public Ui(Parser parser) {
        this.parser = parser;
    }

    /**
     * Gets the output for the user input, including wrongly formatted user input.
     * @param input String input by the user.
     * @return String response from the program.
     */
    public String getOutput(String input, TaskList tasks, Storage storage) {
        try {
            Command c = parser.parse(input, tasks);
            c.execute(tasks, storage);
            return c.output();
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
