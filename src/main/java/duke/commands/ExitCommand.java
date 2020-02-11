package duke.commands;

import duke.tasks.TaskList;
import duke.storage.Storage;
import duke.exceptions.DukeException;

public class ExitCommand implements Command {

    /**
     * Exits the program upon user input of "bye"
     */
    public static String execute() {
        return "See you again!";
    }
}
