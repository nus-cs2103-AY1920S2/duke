package duke.utils;

import duke.command.Command;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommand;
import duke.command.FindCommand;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.SnoozeCommand;
import duke.command.HelpCommand;

/**
 * Parser to parse commands made by user.
 */
public class CommandParser {

    /**
     * Parses users' request and return the appropriate action.
     * @param request users's request
     * @return Command object
     */
    public static Command commandParser(String request) {
        String[] token = request.split(" ", 2);
        if (token[0].equals("bye")) {
            return new ExitCommand();
        } else if (token[0].equals("list")) {
            return new ListCommand();
        } else if (token[0].equals("done")) {
            return new DoneCommand();
        } else if (token[0].equals("delete")) {
            return new DeleteCommand();
        } else if (token[0].equals("todo") || token[0].equals("deadline") || token[0].equals("event")) {
            return new AddCommand();
        } else if (token[0].equals("find")) {
            return new FindCommand();
        } else if (token[0].equals("help")) {
            return new HelpCommand();
        } else if (token[0].equals("snooze")) {
            return new SnoozeCommand();
        } else {
            return new UnknownCommand();
        }
    }
}
