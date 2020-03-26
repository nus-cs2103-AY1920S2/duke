package duke.utils;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.HelloCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.PokeCommand;
import duke.commands.SaveCommand;
import duke.commands.TodoCommand;
import duke.commands.UndoCommand;
import duke.commands.exceptions.CommandException;

/**
 * The <code>CommandParser</code> class represents a utility class that helps to parse user
 * input into a Command.
 */
public class CommandParser {
    public static final String SPACE = " ";
    
    /**
     * Takes an input and parses it into a command.
     *
     * @param input User input.
     * @return Resulting command.
     */
    public static Command parseToCommand(String input) throws CommandException {
        String commandWord = input.split(SPACE)[0].toLowerCase();
        String arguments = input.substring(commandWord.length()).strip();
        switch (commandWord) {
        case "hello":
            return new HelloCommand();
        case "poke":
            return new PokeCommand();
        case "bye":
            return new ByeCommand();
        case "help":
            return new HelpCommand();
        case "save":
            return new SaveCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new TodoCommand(arguments);
        case "deadline":
            return new DeadlineCommand(arguments);
        case "event":
            return new EventCommand(arguments);
        case "done":
            return new DoneCommand(arguments);
        case "undo":
            return new UndoCommand(arguments);
        case "delete":
            return new DeleteCommand(arguments);
        case "find":
            return new FindCommand(arguments);
        default:
            throw new CommandException("???????? gomenasai wakarimasen :((( Enter `help` to get a list of commands.");
        }
    }
}
