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
            Task task;

            // First argument is the character ID of the task type.
            // Exceptions will be thrown if the task could not be read properly
            switch (args[0]) {
            case "T":
                // To-do
                task = readTodo(args);
                break;
            case "D":
                // Deadline
                task = readDeadline(args);
                break;
            case "E":
                // Event
                task = readEvent(args);
                break;
            default:
                throw new DukeException("Unknown task type: " + args[0]);
            }

            // Second argument indicates whether the task has been completed
            if (args[1].equals(TASK_COMPLETE)) {
                task = task.markDone();
            }

            return task;

        } else {
            throw new DukeException("Arguments cannot be used to construct a valid task.");
        }
    }

    private boolean isTask(String[] args) {
        // Arguments form a task if there are at least 3 arguments
        // and the second argument is either a "0" or "1"
        return args.length >= 3
                && (args[1].equals(TASK_COMPLETE)
                    || args[1].equals(TASK_INCOMPLETE));
    }

    private Todo readTodo(String[] args) throws DukeException {
        Parser.checkArgumentCount(args, 3);
        return new Todo(args[2]);
    }

    private Deadline readDeadline(String[] args) throws DukeException {
        Parser.checkArgumentCount(args, 4);
        return new Deadline(args[2], Parser.parseDate(args[3]));
    }

    private Event readEvent(String[] args) throws DukeException {
        Parser.checkArgumentCount(args, 5);
        return new Event(args[2], args[3], args[4]);
    }
}