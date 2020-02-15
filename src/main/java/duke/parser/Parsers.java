package duke.parser;

import duke.command.Command;

import duke.exception.DukeException;

public class Parsers {
    /**
     * Returns a valid user command.
     *
     * @param fullCommand a line of user input.
     * @return a valid user command.
     * @throws DukeException if command is empty.
     * @throws DukeException if command could not be parsed.
     * @throws DukeException if input arguments do not match.
     */
    public static Command parse(String fullCommand) throws DukeException {

        String[] input = fullCommand.stripTrailing().split("\\s+", 2);
        String command = input[0];

        // Handle different commands
        switch (command) {
        case "bye":
            return CommandParser.parseExit(input);

        case "deadline":
            return TaskParser.parseDeadline(input);

        case "delete":
            return CommandParser.parseDelete(input);

        case "done":
            return CommandParser.parseDone(input);

        case "event":
            return TaskParser.parseEvent(input);

        case "find":
            return CommandParser.parseFind(input);

        case "list":
            return CommandParser.parseList(input);

        case "todo":
            return TaskParser.parseTodo(input);

        default:
            if (command.strip().isEmpty()) {
                // Blank input
                throw new DukeException("I'm sorry. You didn't ask me anything.");
            } else {
                // Unknown command
                throw new DukeException("I'm sorry, but I don't know what that means :(");
            }
        }
    }
}
