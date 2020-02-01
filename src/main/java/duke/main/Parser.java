package duke.main;

import duke.command.*;
import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;

public class Parser {
    static boolean parseCommand(String input, TaskList taskList) throws UnknownCommandException {
        try {
            String param;
            String[] tokens = input.split(" ", 2);
            Duke.Command command = Duke.Command.valueOf(tokens[0].toUpperCase());
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
                FindCommand.run(taskList, param);
                break;
            default:
                throw new UnknownCommandException();
            }
        } catch (IllegalArgumentException ex) {
            throw new UnknownCommandException();
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return true;
    }
}
