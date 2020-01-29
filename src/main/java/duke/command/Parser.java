package duke.command;

import duke.exception.InvalidCommandException;

public class Parser {
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
