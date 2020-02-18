package duke.interact;

import duke.TaskList;
import duke.command.Command;
import duke.command.CommandAdd;
import duke.command.CommandBye;
import duke.command.CommandDel;
import duke.command.CommandDone;
import duke.command.CommandFind;
import duke.command.CommandList;
import duke.command.CommandUpDate;
import duke.command.CommandUpDetail;
import duke.exception.DukeException;
import duke.exception.DukeExceptionCommand;

/**
 * Parses user input and checks whether the user has given wrongly formatted input.
 */
public class Parser {

    /**
     * Returns a Command representing the user input.
     * @param line String input by user.
     * @param tasks Current TaskList.
     * @return Command representing user input.
     * @throws DukeException Exception thrown if user input is wrongly formatted.
     */
    public Command parse(String line, TaskList tasks) throws DukeException {
        int size = tasks.getSize();
        switch (line) {
        case "bye":
            return new CommandBye();
        case "list":
            return new CommandList();
        default:
            String inputCommand = line.split("\\s")[0];
            switch (inputCommand) {
            case "done":
                return new CommandDone(line, size);
            case "delete":
                return new CommandDel(line, size);
            case "todo":
                String details = line.substring(inputCommand.length());
                return new CommandAdd(details);
            case "event":
                return new CommandAdd(line, "event");
            case "deadline":
                return new CommandAdd(line, "deadline");
            case "find":
                return new CommandFind(line.substring(4));
            case "updatem":
                return new CommandUpDetail(line, size);
            case "updated":
                return new CommandUpDate(line, size);
            default:
                throw new DukeExceptionCommand();
            }
        }
    }
}
