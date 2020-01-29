package duke.command;

import duke.exception.InvalidCommandException;

/**
 * Represents a Parser.
 * Used to to parse user input into commands.
 */
public class Parser {
    /**
     * Parse the user input into the respective command.
     *
     * @param input the command input.
     * @return The respective command if given a valid command input.
     * @throws InvalidCommandException If command input is invalid.
     */
    public static Command parseCommand(String input) throws InvalidCommandException {
        //split the command line
        String[] commandLine = input.split(" ", 2);

        //determine which type of command and return the respective command
        DukeCommand command = DukeCommand.getCommand(commandLine[0]);
        switch (command) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand(Integer.parseInt(commandLine[1]));
        case DELETE:
            return new DeleteCommand(Integer.parseInt(commandLine[1]));
        case TODO:
            if (commandLine.length < 2) {
                throw new InvalidCommandException("     ☹ OOPS!!! The description of a "
                        + "todo cannot be empty.");
            } else {
                return new TodoCommand(commandLine[1]);
            }
        case DEADLINE:
            if (commandLine.length < 2) {
                throw new InvalidCommandException("     ☹ OOPS!!!"
                        + " The description of a deadline cannot be empty.");
            } else {
                String[] deadlineDescriptionDate = commandLine[1].split(" /by ");
                return new DeadlineCommand(deadlineDescriptionDate);
            }
        case EVENT:
            if (commandLine.length < 2) {
                throw new InvalidCommandException("     ☹ OOPS!!! The description of a "
                        + "event cannot be empty.");
            } else {
                String[] eventDescriptionDate = commandLine[1].split(" /at ");
                return new EventCommand(eventDescriptionDate);
            }
        default:
            throw new InvalidCommandException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
