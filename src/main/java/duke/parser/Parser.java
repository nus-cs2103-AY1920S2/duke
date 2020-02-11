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
import duke.storage.Storage;
import duke.tasks.TaskList;

public class Parser {

    /**
     * Returns the command corresponding to user input
     * @param input Input received from user
     * @return Command requested by user
     */
    public static String parse(String input, TaskList taskList, Storage storage) {

        String[] inputArr = input.split(" ",2);
        String cmd = inputArr[0].toUpperCase();

        CommandType commandType;
        try {
            commandType = CommandType.valueOf(cmd);
        } catch (IllegalArgumentException e) {
            return "command not recognized";
        }
        String commandDetails = "";

        if (commandType != CommandType.BYE || commandType != CommandType.LIST) {
            try {
                commandDetails = inputArr[1];
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("details required");
            }
        }

        switch (commandType) {
        case TODO:
            return AddTodoCommand.execute(commandDetails, taskList, storage);
        case BYE:
            return ExitCommand.execute(commandDetails, taskList, storage);
        case DEADLINE:
            return AddDeadlineCommand.execute(commandDetails, taskList, storage);
        case DELETE:
            return DeleteCommand.execute(commandDetails, taskList, storage);
        case DONE:
            return DoneCommand.execute(commandDetails, taskList, storage);
        case EVENT:
            return AddEventCommand.execute(commandDetails, taskList, storage);
        case FIND:
            return FindCommand.execute(commandDetails, taskList, storage);
        case LIST:
            return ListCommand.execute(commandDetails, taskList, storage);
        default:
            return "failed to understand command";
        }
    }
}
