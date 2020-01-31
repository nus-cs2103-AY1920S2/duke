package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.ListCommand;
import duke.enums.ErrorCodes;
import duke.enums.TaskTypes;
import duke.exceptions.DukeException;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        Command command = null;
        switch (fullCommand) {
        case "list":
            command = new ListCommand();
            break;
        case "bye":
            command = new ExitCommand();
            break;
        default:
            String[] splitString = fullCommand.split(" ", 2);
            if (splitString.length != 2) {
                throw new DukeException(ErrorCodes.MISSING_TASK_NAME);
            }
            String userCommand = splitString[0];
            String userArgs = splitString[1];
            int taskNo = -1;

            switch (userCommand) {
            case "done":
                taskNo = Integer.parseInt(userArgs) - 1;
                command = new DoneCommand(taskNo);
                break;
            case "delete":
                taskNo = Integer.parseInt(userArgs) - 1;
                command = new DeleteCommand(taskNo);
                break;
            case "deadline":
                command = new AddCommand(TaskTypes.DEADLINE, userArgs);
                break;
            case "todo":
                command = new AddCommand(TaskTypes.TODO, userArgs);
                break;
            case "event":
                command = new AddCommand(TaskTypes.EVENT, userArgs);
                break;
            default:
                throw new DukeException(ErrorCodes.UNKNOWN_COMMAND);
            };
        }
        return command;
    }
}