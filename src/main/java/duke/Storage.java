package duke;

import duke.exception.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
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
    /** ID for a completed task. */
    private static final String TASK_COMPLETE = "1";
    /** ID for a task that has not been completed. */
    private static final String TASK_INCOMPLETE = "0";

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

        // Check if the tokens can form one of the task types
        if (isTask(args)) {
            Task task = readTaskType(args);
            task = readTaskIsDone(args[1], task);

            return task;

        } else {
            // Task could not be read properly
            throw new DukeException("Arguments cannot be used to construct a valid task.");
        }
    }

    /**
     * Checks if a group of tokens form the minimum requirements to construct a task.
     * The first token must be the character id of the underlying task type.
     * The second token must either be a "0" or "1" to represent whether the task has been
     * completed or not.
     *
     * @param args tokens read from a file input.
     * @return true if the tokens can construct a generic task, otherwise false.
     */
    private boolean isTask(String[] args) {
        // There should be at least 3 arguments
        boolean hasThreeArguments = args.length >= 3;

        if (hasThreeArguments) {
            // Second argument is either "0" or "1"
            return args[1].equals(TASK_INCOMPLETE) || args[1].equals(TASK_COMPLETE);

        } else {
            return false;
        }
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
    private Todo readTodo(String[] args) throws DukeException {
        Parser.checkArgumentCount(args, 3);
        return new Todo(args[2]);
    }

    /**
     * Returns a deadline constructed from a tokenized file input.
     *
     * @param args tokens read from a file input.
     * @return a deadline read from file input.
     * @throws DukeException if the file input contains invalid arguments.
     */
    private Deadline readDeadline(String[] args) throws DukeException {
        Parser.checkArgumentCount(args, 4);
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
        Parser.checkArgumentCount(args, 5);
        return new Event(args[2], args[3], args[4]);
    }

    /**
     * Marks a task as completed or not based on a token read from a file input.
     *
     * @param isCompleted a token read from file input representing whether
     *                    the task has been completed or not.
     * @param task the task read from a file input.
     * @return a task that has been marked as completed based on a token read from
     *         a file input.
     */
    private Task readTaskIsDone(String isCompleted, Task task) {
        // TODO: Abstract out this check
        assert isCompleted.equals(TASK_INCOMPLETE)
                || isCompleted.equals(TASK_COMPLETE) : "Invalid token - " + isCompleted;

        assert !task.isDone() : "Task should not be marked as completed when constructed";

        if (isCompleted.equals(TASK_COMPLETE)) {
            return task.markDone();

        } else {
            assert isCompleted.equals(TASK_INCOMPLETE) : "Invalid token - " + isCompleted;

            return task;
        }
    }
}