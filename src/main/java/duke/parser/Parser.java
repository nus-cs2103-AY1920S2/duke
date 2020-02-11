/**
 * Filters user input to determine type of command to be used
 */
package duke.parser;

import duke.commands.AddTodoCommand;
import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.CommandType;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.exceptions.DukeException;
import duke.exceptions.MissingDetailsException;
import duke.exceptions.UnrecognizedCommandException;
import duke.storage.Storage;
import duke.tasks.TaskList;

public class Parser {

    /**
     * Returns the command corresponding to user input
     * @param input Input received from user
     * @return Command requested by user
     */
    public static String parse(String input, TaskList taskList, Storage storage) throws DukeException {

        String[] inputArr = input.split(" ",2);
        String cmd = inputArr[0].toUpperCase();

        CommandType commandType;
        try {
            // Check if input command is in enum list
            commandType = CommandType.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            throw new UnrecognizedCommandException();
        }

        String commandDetails = "";
        // Only BYE and LIST commands do not have further details
        if (commandType != CommandType.BYE && commandType != CommandType.LIST) {
            try {
                commandDetails = inputArr[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new MissingDetailsException();
            }
        }

        switch (commandType) {
        case BYE:
            return ExitCommand.execute();
        case DEADLINE:
            return AddDeadlineCommand.execute(commandDetails, taskList, storage);
        case DELETE:
            return DeleteCommand.execute(commandDetails, taskList, storage);
        case DONE:
            return DoneCommand.execute(commandDetails, taskList, storage);
        case EVENT:
            return AddEventCommand.execute(commandDetails, taskList, storage);
        case FIND:
            return FindCommand.execute(commandDetails, taskList);
        case LIST:
            return ListCommand.execute(taskList);
        case TODO:
            return AddTodoCommand.execute(commandDetails, taskList, storage);
        default:
            return "failed to understand command";
        }
    }
}
