package duke;

import duke.exception.DukeException;

import duke.exception.MissingParsedArgumentsException;
import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

public class Storage {
    /** Separates a line of text for file IO processing. */
    private static final String DELIMITER = " \\s*\\|\\s*";

    /** Relative directory of the save file for this storage object. */
    private String filePath;

    /**
     * Constructs a new saving/loading mechanism.
     *
     * @param filePath the relative directory of the save file for this storage object.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a list of tasks generated from a save file.
     *
     * @return a list of tasks generated from a save file.
     * @throws DukeException if file could not be found or opened.
     */
    public List<Task> load() throws DukeException {
        // Setup file input resources with auto-closing
        try (FileInputStream fileStream = new FileInputStream(filePath);
             InputStreamReader fileReader = new InputStreamReader(fileStream);
             BufferedReader reader = new BufferedReader(fileReader)) {

            List<Task> tasks = new ArrayList<>();

            // Read next line in file
            String line = reader.readLine();

            // While not EOF
            while (line != null) {
                try {
                    Task task = readTask(line);
                    tasks.add(task);
                } catch (DukeException e) {
                    // TODO: In future will implement logging of error lines
                    // Currently skips invalid lines
                } finally {
                    // Read next line in file
                    line = reader.readLine();
                }
            }

            return tasks;

        } catch (IOException e) {
            throw new DukeException("Could not read from file: " + filePath);
        }
    }

    /**
     * Writes a list of tasks into a save file.
     *
     * @param tasks the list of tasks to write to a save file.
     * @throws DukeException if file could not be found or opened.
     */
    public void save(TaskList tasks) throws DukeException {
        // Setup file output resources with auto-closing
        try (FileOutputStream fileStream = new FileOutputStream(filePath);
             PrintWriter writer = new PrintWriter(fileStream)) {

            List<Task> taskList = tasks.getList();

            for (int i = 0; i < taskList.size(); i++) {
                // Each task has their own save file format
                writer.println(taskList.get(i).serialize());
            }

        } catch (IOException e) {
            throw new DukeException("Could not write to file: " + filePath);
        }
    }

    /**
     * Selects the appropriate method to read a task from a file.
     *
     * @param line a line from a file input.
     * @throws DukeException if task type is unknown.
     * @throws DukeException if arguments cannot make any valid task.
     */
    private Task readTask(String line) throws DukeException {
        // Tokenize the string
        String[] args = line.split(DELIMITER);

        int minimumArguments = 3;
        boolean hasMinimumArguments = args.length >= minimumArguments;

        if (!hasMinimumArguments) {
            throw new MissingParsedArgumentsException();
        }

        Task task = readTaskType(args);

        boolean isDone = Parser.parseBoolean(args[1], "1", "0");

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
     * @throws DukeException if the file input has an invalid task id.
     */
    private Task readTaskType(String[] args) throws DukeException {
        // First argument is the character ID of the task type.
        switch (args[0]) {
        case "T":
            return readTodo(args); // To-do
        case "D":
            return readDeadline(args); // Deadline
        case "E":
            return readEvent(args); // Event
        default:
            throw new DukeException("Unknown task type: " + args[0]);
        }
    }

    // TODO: remove dependency on Parser

    /**
     * Returns a to-do constructed from a tokenized file input.
     *
     * @param args tokens read from a file input.
     * @return a to-do read from file input.
     * @throws DukeException if the file input contains invalid arguments.
     */
    private Todo readTodo(String[] args) throws MissingParsedArgumentsException {
        if (!hasNumArguments(args, 3)) {
            throw new MissingParsedArgumentsException();
        }

        return new Todo(args[2]);
    }

    /**
     * Returns a deadline constructed from a tokenized file input.
     *
     * @param args tokens read from a file input.
     * @return a deadline read from file input.
     * @throws DukeException if the file input contains invalid arguments.
     */
    private Deadline readDeadline(String[] args) throws MissingParsedArgumentsException,
            DukeException {
        if (!hasNumArguments(args, 4)) {
            throw new MissingParsedArgumentsException();
        }

        return new Deadline(args[2], Parser.parseDate(args[3]));
    }

    /**
     * Returns an event constructed from a tokenized file input.
     *
     * @param args tokens read from a file input.
     * @return an event read from file input.
     * @throws DukeException if the file input contains invalid arguments.
     */
    private Event readEvent(String[] args) throws DukeException {
        if (!hasNumArguments(args, 5)) {
            throw new MissingParsedArgumentsException();
        }

        return new Event(args[2], args[3], args[4]);
    }

    /**
     * Returns true if the input contains the correct number of arguments,
     * otherwise false.
     *
     * @param input a tokenized array of input arguments.
     * @param length the desired number of input arguments.
     * @return true if the input contains the correct number of arguments,
     *         otherwise false.
     */
    private boolean hasNumArguments(String[] input, int length) {
        return input.length == length;
    }
}