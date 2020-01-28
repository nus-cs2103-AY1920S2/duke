package duke.parser;

import duke.commands.*;

public class Parser {

    public static Command parse(String fullCommand) {

        String cmdType = fullCommand.split(" ")[0];

        if (cmdType.equalsIgnoreCase("todo")
                || cmdType.equalsIgnoreCase("deadline")
                || cmdType.equalsIgnoreCase("event")) {
            return new AddCommand(fullCommand.toLowerCase().trim());
        } else if (cmdType.equalsIgnoreCase("list")) {
            return new ListCommand(fullCommand.toLowerCase().trim());
        } else if (cmdType.equalsIgnoreCase("done")) {
            return new DoneCommand(fullCommand.toLowerCase().trim());
        } else if (cmdType.equalsIgnoreCase("delete")) {
            return new DeleteCommand(fullCommand.toLowerCase().trim());
        } if (cmdType.equalsIgnoreCase("bye")) {
            return new ExitCommand("bye");
        } else {
            return new Command("Unrecognized");
        }
    }
}
