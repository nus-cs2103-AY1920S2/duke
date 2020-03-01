package duke.parser;

//import duke.DukeException;
import duke.parser.exception.DateFormatException;
import duke.parser.exception.MissingParserArgumentsException;
import duke.parser.exception.ParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class StorageParser extends Parser {
    /** Separates a line of text for file IO processing. */
    private static final String DELIMITER = " \\s*\\|\\s*";

    /**
     * Selects the appropriate method to read a task from a file.
     *
     * @param line a line from a file input.
     * @throws ParseException if task type is unknown.
     * @throws ParseException if arguments cannot make any valid task.
     */
    public static Task readTask(String line) throws ParseException {
        // Tokenize the string
        String[] args = line.split(DELIMITER);

        int minimumArguments = 3;
        boolean hasMinimumArguments = args.length >= minimumArguments;

        if (!hasMinimumArguments) {
            throw new MissingParserArgumentsException();
        }

        Task task = readTaskByType(args);

        boolean isDone = StringParser.parseBoolean(args[1], "1", "0");

        if (isDone) {
            task = task.markDone();
        }
        return task;
    }

    /**
     * Reads and constructs a task from a tokenized file input.
     *
     * @param args tokens read from a file input.
     * @return a task read from file input.
     * @throws ParseException if the file input has an invalid task id.
     */
    static Task readTaskByType(String[] args) throws ParseException {
        // First argument is the character ID of the task type.
        switch (args[0]) {
        case "T":
            return readTodo(args); // To-do
        case "D":
            return readDeadline(args); // Deadline
        case "E":
            return readEvent(args); // Event
        default:
            throw new ParseException("There's no such task type: " + args[0]);
        }
    }

    /**
     * Returns a to-do constructed from a tokenized file input.
     *
     * @param args tokens read from a file input.
     * @return a to-do read from file input.
     * @throws MissingParserArgumentsException if the file input contains invalid arguments.
     */
    static Todo readTodo(String[] args) throws MissingParserArgumentsException {
        if (!hasNumArguments(args, 3)) {
            throw new MissingParserArgumentsException();
        }

        return new Todo(args[2]);
    }

    /**
     * Returns a deadline constructed from a tokenized file input.
     *
     * @param args tokens read from a file input.
     * @return a deadline read from file input.
     * @throws MissingParserArgumentsException if the file input contains invalid arguments.
     * @throws DateFormatException if the date input is in an invalid format.
     */
    static Deadline readDeadline(String[] args) throws MissingParserArgumentsException,
            DateFormatException {

        if (!hasNumArguments(args, 4)) {
            throw new MissingParserArgumentsException();
        }

        return new Deadline(args[2], StringParser.parseDate(args[3]));
    }

    /**
     * Returns an event constructed from a tokenized file input.
     *
     * @param args tokens read from a file input.
     * @return an event read from file input.
     * @throws MissingParserArgumentsException if the file input contains invalid arguments.
     */
    static Event readEvent(String[] args) throws MissingParserArgumentsException {
        if (!hasNumArguments(args, 5)) {
            throw new MissingParserArgumentsException();
        }

        return new Event(args[2], args[3], args[4]);
    }
}
