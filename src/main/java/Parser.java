package duke;

import duke.commands.Command;
import duke.commands.ByeCommand;
import duke.commands.ListCommand;
import duke.commands.DoneCommand;
import duke.commands.DeleteCommand;
import duke.commands.AddCommand;
import duke.exception.DukeException;

import java.util.Arrays;

/**
 * Parser class takes in and parses user input to return commands.
 */
public class Parser {
    public static String[] tasksCommandList = {"todo", "deadline", "event"};

    /**
     * Parses user input to return a command.
     *
     * @param command user's input to specify command.
     * @return Command.
     * @throws DukeException if user input does not follow input format.
     */
    public static Command parse(String command) throws DukeException {
        String[] split = command.split(" ", 2);
        String commandType = split[0];

        if (split.length < 1) {
            throw new DukeException("The description cannot be empty.");
        }

        if (commandType.equals("bye")) {
            return new ByeCommand();
        } else if (commandType.equals("list")) {
            return new ListCommand();
        } else if (commandType.equals("done") || commandType.equals("delete")) {
            checkNumberException(split, commandType);
            if (commandType.equals("done")) {
                return new DoneCommand(Integer.parseInt(split[1]) - 1);
            } else {
                return new DeleteCommand(Integer.parseInt(split[1]) - 1);
            }
        } else if (Arrays.asList(tasksCommandList).contains(commandType)) {
            checkNumberException(split, commandType);
            return new AddCommand(commandType, split[1]);
        } else {
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void checkNumberException(String[] split, String commandType) throws DukeException {
        if (commandType.equals("done") || commandType.equals("delete")) {
            if (split.length <= 1) {
                throw new DukeException("Task number cannot be empty.");
            }
        }

        if (Arrays.asList(tasksCommandList).contains(commandType)) {
            if (split.length <= 1) {
                throw new DukeException("Task description cannot be empty.");
            }
        }
    }
}