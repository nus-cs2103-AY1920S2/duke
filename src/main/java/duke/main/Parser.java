package duke.main;

import duke.command.ClearCommand;
import duke.command.TodoCommand;
import duke.command.DeadlineCommand;
import duke.command.ByeCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.DeleteCommand;
import duke.command.CalendarCommand;
import duke.command.DoneCommand;

import duke.exceptions.DukeException;
import duke.exceptions.UnknownCommandException;

public class Parser {
    static boolean parseCommand(String input, TaskList taskList) {
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
                ByeCommand.run();
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
            default:
                throw new UnknownCommandException();
            }
        } catch (DukeException ex) {
            System.out.println(ex);
        }
        return true;
    }
}
