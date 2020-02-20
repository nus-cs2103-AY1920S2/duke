package duke.logic.parser;

import duke.commons.exceptions.DukeException;
import duke.commons.exceptions.EmptyDescriptionException;
import duke.commons.exceptions.InvalidCommandException;
import duke.logic.commands.*;

import java.util.Arrays;
import java.util.ArrayList;

/**
 * Represents a parser to parse <code>String</code> input into <code>Command</code> objects.
 */
public class Parser {

    private static ArrayList<String> validCommands = new ArrayList<>(
            Arrays.asList("list", "done", "delete", "find", "todo", "event", "deadline", "bye"));

    public Parser() {
    }

    /**
     * Returns a <code>Command</code> object parsed from the input <code>String</code>.
     *
     * @param fullCommand input <code>String</code> provided by user.
     * @return <code>Command</code> object to be executed.
     * @throws DukeException If the syntax of the input <code>String</code> is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] commands = fullCommand.split(" ", 2);
        String commandWord = commands[0];
        checkCommand(commandWord, commands);
        switch (commandWord) {
        case "bye":
            return new ExitCommand(commandWord);
        case "list":
            return new ListCommand(commandWord);
        case "done":
            int doneIndex = Integer.parseInt(commands[1]);
            return new DoneCommand(commandWord, doneIndex);
        case "delete":
            int deleteIndex = Integer.parseInt(commands[1]);
            return new DeleteCommand(commandWord, deleteIndex);
        case "find":
            String keyword = commands[1];
            return new FindCommand(commandWord, keyword);
        default:
            return new AddCommand(commandWord, commands);
        }
    }

    /**
     * Checks that the syntax of the command <code>String</code> provided by user is valid.
     *
     * @param commandWord Command word provided by user.
     * @param commands array of <code>String</code> tokens from the input <code>String</code> provided by user.
     * @throws DukeException If the commandWord or the number of arguments provided is invalid.
     */
    public static void checkCommand(String commandWord, String[] commands) throws DukeException {
        checkCommandWord(commandWord);
        if (!commandWord.equals("list") && !commandWord.equals("bye")) {
            checkDetails(commands);
        } else {
            // commandWord is either "list" or "bye", with no arguments required
            assert commands.length == 1 : "invalid arguments";
        }
    }

    /**
     * Checks that the command word provided by user is valid.
     *
     * @param commandWord Command word provided by user.
     * @throws InvalidCommandException If the commandWord provided is invalid.
     */
    public static void checkCommandWord(String commandWord) throws InvalidCommandException {
        if (!validCommands.contains(commandWord)) {
            throw new InvalidCommandException("Sorry dude but that won't command me!");
        } else {
            // valid commandWord
            assert validCommands.contains(commandWord) : "invalid command";
        }
    }

    /**
     * Checks that the number of arguments provided by user is valid.
     *
     * @param commands array of <code>String</code> tokens from the input <code>String</code> provided by user.
     * @throws EmptyDescriptionException If there number of arguments provided by the user is invalid.
     */
    public static void checkDetails(String[] commands) throws EmptyDescriptionException {
        if (commands.length < 2) {
            throw new EmptyDescriptionException("Sorry dude but where are the arguments???");
        }
    }
}
