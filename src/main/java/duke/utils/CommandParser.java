package duke.utils;

import duke.command.*;

public class CommandParser {

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
        } else {
            return new UnknownCommand();
        }
    }
}
