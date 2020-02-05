package duke.command;

import duke.exception.InvalidCommandException;

/**
 * Represents a Parser.
 * Used to to parse user input into commands.
 */
public class Parser {
    /**
     * Parses the user input into the respective command.
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
            if (commandLine.length < 2) {
                throw new InvalidCommandException("HEY!!! I need to know what task you want mark as done!");
            } else {
                return new DoneCommand(Integer.parseInt(commandLine[1]));
            }
        case DELETE:
            if (commandLine.length < 2) {
                throw new InvalidCommandException("HEY!!! I need to know what task to target my explosion."
                        + " Perhaps I will just target Beldia's castle...");
            } else {
                return new DeleteCommand(Integer.parseInt(commandLine[1]));
            }
        case TODO:
            if (commandLine.length < 2) {
                throw new InvalidCommandException("HEY!!! The description of a todo cannot be empty."
                        + " Maybe I will name it ChunChunMaru for you! Just kidding, please don't hit me!");
            } else {
                return new TodoCommand(commandLine[1]);
            }
        case DEADLINE:
            if (commandLine.length < 2) {
                throw new InvalidCommandException("HEY!!! The description of a deadline cannot be empty."
                        + " Maybe I will name it Chomusuke for you! Just kidding, please don't hit me!");
            } else {
                String[] deadlineDescriptionDate = commandLine[1].split(" /by ");
                return new DeadlineCommand(deadlineDescriptionDate);
            }
        case EVENT:
            if (commandLine.length < 2) {
                throw new InvalidCommandException("HEY!!! The description of an event cannot be empty."
                        + " Maybe I will name it Hyoizaburoo for you! Just kidding, please don't hit me!");
            } else {
                String[] eventDescriptionDate = commandLine[1].split(" /at ");
                return new EventCommand(eventDescriptionDate);
            }
        case FIND:
            if (commandLine.length < 2) {
                throw new InvalidCommandException("HEY!!! Please state what you want to find.");
            } else {
                return new FindCommand(commandLine[1]);
            }
        default:
            throw new InvalidCommandException("HEY!!! I don't know what that means :-(");
        }
    }
}
