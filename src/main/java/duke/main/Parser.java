package duke.main;

import duke.command.Command;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.FindCommand;
import duke.command.SnoozeCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.ByeCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.CalendarCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;

import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;

/**
 * Class that parses user inputs into executed commands
 */
public class Parser {
    /**
     * Parses user input to run respective commands
     * @param input String user input
     * @param taskList TaskList object containing list of Tasks
     * @return boolean true if ByeCommand is invoked by user
     */
    static boolean parseCommand(String input, TaskList taskList) throws DukeException {
        try {
            String param;
            String[] tokens = input.split(" ", 2);
            Command.CommandType command = Command.CommandType.valueOf(tokens[0].toUpperCase());
            if (tokens.length > 1) {
                param = tokens[1];
            } else {
                param = "";
            }
            switch (command) {
            case CLEAR:
                ClearCommand.run(taskList);
                break;
            case TODO:
                TodoCommand.run(taskList, param);
                break;
            case DEADLINE:
                DeadlineCommand.run(taskList, param);
                break;
            case EVENT:
                EventCommand.run(taskList, param);
                break;
            case BYE:
                ByeCommand.run(taskList);
                return false;
            case LIST:
                ListCommand.run(taskList);
                break;
            case DONE:
                DoneCommand.run(taskList, param);
                break;
            case DELETE:
                DeleteCommand.run(taskList, param);
                break;
            case CALENDAR:
                CalendarCommand.run(taskList, param);
                break;
            case FIND:
                FindCommand.run(taskList, param.split(" "));
                break;
            case SNOOZE:
                SnoozeCommand.run(taskList, param);
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (IllegalArgumentException ex) {
            throw new UnknownCommandException();
        } catch (DukeException ex) {
           throw ex;
        }
        return true;
    }
}
