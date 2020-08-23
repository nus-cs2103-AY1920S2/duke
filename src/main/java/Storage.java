import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import dukeexception.DukeException;
import dukeexception.DukeIoException;

import task.Task;
import task.Deadline;
import task.Event;
import task.Todo;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    static Path filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filePath The file path to the file to load the tasks in from.
     */
    public Storage(Path filePath) {
        if (!Files.exists(filePath)) {
            Path dataPath = Paths.get("", "data");
            if (!Files.exists(dataPath)) {
                try {
                    Files.createDirectories(dataPath);
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
            String emptyString = "";
            try {
                Files.write(filePath, emptyString.getBytes());
            } catch (IOException e) {
                System.err.println(e);
            }
        }
        this.filePath = filePath;
    }

    /**
     * Reads line by line the tasks in the file, constructs the Task Object.
     * Returns an ArrayList of the Task objects constructed.
     *
     * @return ArrayList of the tasks read.
     * @throws DukeIoException If the file path cannot be read.
     * @throws DukeException If there is a task that does not make sense.
     */
    public static ArrayList<Task> loadFromFile() throws DukeException, DukeIoException {
        ArrayList<Task> listOfTasks = new ArrayList<>();
        try {
            Scanner sc = new Scanner(filePath);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (line.equals("")) {
                    continue;
                }
                // From every line (task) read,
                // Get command type which is either T, D or E
                String commandTye = line.substring(1, 2);
                // Get status of task if done or not (Y or N)
                boolean isDone = line.substring(4, 5).equals("Y");
                // Get whatever is left of the task after the [T][Y]
                String taskArgs = line.substring(7);
                Task task = buildTask(commandTye, taskArgs, isDone);
                listOfTasks.add(task);
            }
            return listOfTasks;
        } catch (IOException e) {
            throw new DukeIoException("File does not exist in file path, load from file failed.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Do not understand task(s) in file, load from file failed");
        }
    }

    /**
     * Updates the file in the path with the current tasks.
     *
     * @param tasks TaskList object containing all the tasks to use to update file.
     * @param numOfTasks Number of tasks in the TaskList currently.
     * @throws DukeIoException If path cannot be read e.g. due to missing file.
     */
    public static void updateFile(TaskList tasks, int numOfTasks) throws DukeIoException {
        ArrayList<String> tasksForFile = new ArrayList<>();
        for (int i = 0; i < numOfTasks; i++) {
            tasksForFile.add(tasks.getTask(i) + "\n");
        }
        try {
            Files.write(filePath, tasksForFile);
        } catch (IOException e) {
            throw new DukeIoException("File does not exist in file path, file update failed.");
        }
    }

    /**
     * Constructs the task after line has been read from file.
     *
     * @param commandTye The first letter in the task to tell the type of task.
     * @param args String where it's everything after the initial [X][X].
     * @param isDone The second letter in the task to tell if the task has been completed.
     * @return Task constructed.
     * @throws DukeIoException If the code does not match T, D or E.
     */
    public static Task buildTask(String commandTye, String args, boolean isDone) throws DukeIoException {
        if (commandTye.equals("T")) {
            // args lik "borrow book"
            Todo todo = new Todo(args, isDone);
            return todo;
        } else if (commandTye.equals("D")) {
            // deadlineArgs like "homework (by: 22/12/2019 1800)"
            String[] deadlineArgs = args.split("[ ][//(][b][y][//:][ ]");
            String description = deadlineArgs[0];
            String byWhen = deadlineArgs[1].substring(0, deadlineArgs[1].length() - 1);
            Deadline deadline = new Deadline(description, byWhen, isDone);
            return deadline;
        } else if (commandTye.equals("E")) {
            // eventArgs like {"attend festival", "2pm-4pm"}
            String[] eventArgs = args.split("[ ][//(][a][t][//:][ ]");
            String description = eventArgs[0];
            String atWhen = eventArgs[1].substring(0, eventArgs[1].length() - 1);
            Event event = new Event(description, atWhen, isDone);
            return event;
        } else {
            throw new DukeIoException("Do not understand task(s) in file, load from file failed");
        }
    }
}