package duke.main;

import duke.command.ByeCommand;
import duke.command.CalendarCommand;
import duke.command.CommandType;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;
import duke.exception.DukeException;
import duke.exception.MissingDetailsException;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;

public class Parser {
    /**
     * parseCommand Method parses CommandTypes (if valid, and to perform the logic).
     *
     * @param input    is the input passed in for parsing
     * @param taskList is the list of Tasks are saved and manipulated
     * @return value true only CommandType.BYE is registered to exit the program
     */
    public static String parseCommand(String input, TaskList taskList) {
        String[] inputBreakdown = input.split(" ", 2);
        CommandType commandType;
        String commandSuffix = null;

        try {
            //First test for valid commands from input
            try {
                commandType = CommandType.valueOf(inputBreakdown[0].toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new UnknownCommandException();
            }

            //Second test for valid command suffixes from input, for commands that require it
            if (commandType != CommandType.BYE && commandType != CommandType.LIST) {
                try {
                    commandSuffix = inputBreakdown[1];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new MissingDetailsException();
                }
            }

            switch (commandType) {
            case BYE:
                return ByeCommand.run();

            case CALENDAR:
                return CalendarCommand.run(taskList, commandSuffix);

            case DEADLINE:
                return DeadlineCommand.run(taskList, commandSuffix);

            case DELETE:
                return taskList.delete(commandSuffix);

            case DONE:
                return taskList.done(commandSuffix);

            case EVENT:
                return EventCommand.run(taskList, commandSuffix);

            case FIND:
                return FindCommand.run(taskList, commandSuffix.split(" "));

            case LIST:
                return ListCommand.run(taskList);

            case TODO:
                return TodoCommand.run(taskList, commandSuffix);

            default:
                return new UnknownCommandException().toString();
            }
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
