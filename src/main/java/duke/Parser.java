package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
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
            return parseExit(input);

        case "deadline":
            return parseDeadline(input);

        case "delete":
            return parseDelete(input);

        case "done":
            return parseDone(input);

        case "event":
            return parseEvent(input);

        case "find":
            return parseFind(input);

        case "list":
            return parseList(input);

        case "todo":
            return parseTodo(input);

        default:
            if (command.strip().isEmpty()) {
                throw new DukeException("I'm sorry. You didn't ask me anything.");
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :(");
            }
        }
    }

    private static ExitCommand parseExit(String[] input) throws DukeException {
        checkArgumentCount(input, 1); // Exception if not one word
        return new ExitCommand();
    }

    private static AddCommand parseDeadline(String[] input) throws DukeException {
        checkArgumentCount(input, 2);
        String[] args = input[1].split("\\s+/by\\s+");

        checkArgumentCount(args, 2);
        Task task = new Deadline(args[0], parseDate(args[1]));
        return new AddCommand(task);
    }

    private static AddCommand parseTodo(String[] input) throws DukeException {
        checkArgumentCount(input, 2);
        Task task = new Todo(input[1]);
        return new AddCommand(task);
    }

    private static AddCommand parseEvent(String[] input) throws DukeException {
        checkArgumentCount(input, 2);
        String[] args = input[1].split("\\s+/at\\s+");

        checkArgumentCount(args, 2);
        int timeSlotIndex = args[1].lastIndexOf(" ");

        if (timeSlotIndex > 0) {
            Task task = new Event(args[0],
                    args[1].substring(0, timeSlotIndex).strip(),
                    args[1].substring(timeSlotIndex + 1));
            return new AddCommand(task);
        } else {
            // Exception if command is incorrectly typed
            throw new DukeException("Could not parse input"
                    + " because the number of arguments is invalid.");
        }
    }

    private static DoneCommand parseDone(String[] input) throws DukeException {
        checkArgumentCount(input, 2);
        int taskId = parseInt(input[1]);
        return new DoneCommand(taskId);
    }

    private static DeleteCommand parseDelete(String[] input) throws DukeException {
        checkArgumentCount(input, 2);
        int taskId = parseInt(input[1]);
        return new DeleteCommand(taskId);
    }

    private static ListCommand parseList(String[] input) throws DukeException {
        checkArgumentCount(input, 1);
        return new ListCommand();
    }

    private static FindCommand parseFind(String[] input) throws DukeException {
        checkArgumentCount(input, 2);
        return new FindCommand(input[1]);
    }

    /**
     * Will verify the number of arguments in a line of user input.
     *
     * @param input a tokenized array of input arguments.
     * @param length the desired number of input arguments.
     * @throws DukeException if command arguments do not match.
     */
    public static void checkArgumentCount(String[] input, int length)
            throws DukeException {
        if (input.length != length) {
            // Exception if full command is incorrectly typed
            throw new DukeException("Could not parse input"
                    + " because the number of arguments is invalid.");
        }
    }

    /**
     * Parses an integer from a string.
     *
     * @param input a string to convert into an integer.
     * @return the integer representation of the string.
     * @throws DukeException if input is not an integer.
     */
    public static int parseInt(String input) throws DukeException {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            // Input was not an integer
            throw new DukeException("Please ensure your input is an integer.");
        }
    }

    /**
     * Parses a date from a string.
     *
     * @param input a string to convert into a date, in yyyy-mm-dd format.
     * @return the date representation of the string.
     * @throws DukeException if input cannot be parsed as a date object.
     */
    public static LocalDate parseDate(String input)
            throws DukeException {
        try {
            return LocalDate.parse(input, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please ensure your input matches the date format:\n"
                    + " yyyy-mm-dd.");
        }
    }
}
