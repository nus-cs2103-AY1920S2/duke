package duke.main;

import duke.command.Command;
import duke.command.CommandType;
import duke.exception.DukeException;
import duke.exception.MissingDetailsException;
import duke.exception.UnknownCommandException;
import duke.task.TaskList;

public class Parser {
    //Custom parseCommand to parse CommandTypes (if valid, and to perform the logic)
    public static boolean parseCommand(String input, TaskList taskList) {
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
                return Command.byeCommand(taskList);

            case CALENDAR:
                Command.calendarCommand(taskList, commandSuffix);
                break;

            case DEADLINE:
                Command.deadlineCommand(taskList, commandSuffix);
                break;

            case DELETE:
                taskList.deleteTask(commandSuffix);
                break;

            case DONE:
                taskList.doneTask(commandSuffix);
                break;

            case EVENT:
                Command.eventCommand(taskList, commandSuffix);
                break;

            case LIST:
                Command.listCommand(taskList);
                break;

            case TODO:
                Command.todoCommand(taskList, commandSuffix);
                break;

            default:
                break;
            }
        } catch (DukeException e) {
            Ui.print(e.toString());
        }

        return true;
    }
}
