package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * Class to process user input.
 */

public class Parser {

    /**
     * Processes user input to determine type of command, represented by an enum.
     *
     * @param input text entered by user
     * @return An enum for the command type
     * @throws DukeException exception specific to Duke
     */
    protected Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return Command.BYE;
        } else if (input.equals("list")) {
            return Command.LIST;
        } else if (input.startsWith("done")) {
            return Command.DONE;
        } else if (input.startsWith("todo")) {
            return Command.TODO;
        } else if (input.startsWith("deadline")) {
            return Command.DEADLINE;
        } else if (input.startsWith("event")) {
            return Command.EVENT;
        } else if (input.startsWith("delete")) {
            return Command.DELETE;
        } else if (input.startsWith("find")) {
            return Command.FIND;
        }
        throw new DukeException();
    }
}
