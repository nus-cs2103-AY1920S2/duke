package duke.command;

import duke.exception.InvalidCommandException;

/**
 * Represents a Parser.
 * Used to to parse user input into commands.
 */
public class Parser {
    private static final String INVALID_DONE_COMMAND_MESSAGE = "HEY!!! I need to know what task"
            + " you want to mark as done!";
    private static final String INVALID_DELETE_COMMAND_MESSAGE = "HEY!!! I need to know what task to"
            + " target my explosion. Perhaps I will just target Beldia's castle...";
    private static final String INVALID_TODO_COMMAND_MESSAGE = "HEY!!! The description of a todo cannot be empty."
            + " Maybe I will name it ChunChunMaru for you! Just kidding, please don't hit me!";
    private static final String INVALID_DEADLINE_COMMAND_MESSAGE = "HEY!!! The description of a deadline cannot"
            + " be empty. Maybe I will name it Chomusuke for you! Just kidding, please don't hit me!";
    private static final String INVALID_EVENT_COMMAND_MESSAGE = "HEY!!! The description of an event cannot be empty."
            + " Maybe I will name it Hyoizaburoo for you! Just kidding, please don't hit me!";
    private static final String INVALID_FIND_COMMAND_MESSAGE = "HEY!!! Please state what you want to find.";
    private static final String INVALID_REMINDER_COMMAND_MESSAGE = "HEY!!! Please state"
            + " what you want to be reminded of.";
    private static final String NO_SUCH_COMMAND_MESSAGE = "HEY!!! I don't know what that means :-(";
    private static final String INVALID_INDEX_MESSAGE = "HEY!!! You need to enter a number for the task's index!";

    /**
     * Parses the user input into the respective command.
     *
     * @param input the command input.
     * @return The respective command if given a valid command input.
     * @throws InvalidCommandException If command input is invalid.
     */
    public static Command parseCommand(String input) throws InvalidCommandException {
        //split the command line to get the command and arguments
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
                throw new InvalidCommandException(INVALID_DONE_COMMAND_MESSAGE);
            }

            try {
                return new DoneCommand(Integer.parseInt(commandLine[1]));
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(INVALID_INDEX_MESSAGE);
            }
        case DELETE:
            if (commandLine.length < 2) {
                throw new InvalidCommandException(INVALID_DELETE_COMMAND_MESSAGE);
            }

            try {
                return new DeleteCommand(Integer.parseInt(commandLine[1]));
            } catch (NumberFormatException e) {
                throw new InvalidCommandException(INVALID_INDEX_MESSAGE);
            }
        case TODO:
            if (commandLine.length < 2) {
                throw new InvalidCommandException(INVALID_TODO_COMMAND_MESSAGE);
            }

            return new TodoCommand(commandLine[1]);
        case DEADLINE:
            if (commandLine.length < 2) {
                throw new InvalidCommandException(INVALID_DEADLINE_COMMAND_MESSAGE);
            }

            String[] deadlineDescriptionDate = commandLine[1].split(" /by ");
            return new DeadlineCommand(deadlineDescriptionDate);
        case EVENT:
            if (commandLine.length < 2) {
                throw new InvalidCommandException(INVALID_EVENT_COMMAND_MESSAGE);
            }

            String[] eventDescriptionDate = commandLine[1].split(" /at ");
            return new EventCommand(eventDescriptionDate);
        case FIND:
            if (commandLine.length < 2) {
                throw new InvalidCommandException(INVALID_FIND_COMMAND_MESSAGE);
            }

            return new FindCommand(commandLine[1]);
        case HELP:
            return new HelpCommand();
        case REMINDER:
            if (commandLine.length < 2) {
                throw new InvalidCommandException(INVALID_REMINDER_COMMAND_MESSAGE);
            }

            return new ReminderCommand(commandLine[1]);
        default:
            throw new InvalidCommandException(NO_SUCH_COMMAND_MESSAGE);
        }
    }
}
