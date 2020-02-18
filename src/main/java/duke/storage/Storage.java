package duke.storage;

import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.exception.DukeInvalidDateException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file used to store the task list.
 */
public class Storage {

    private File file;

    /**
     * Creates a file object, and creating the necessary file or directory if file does not exist.
     *
     * @param filePath the file path of the file that stores the task list.
     * @throws DukeIoException when there is I/O error or when parent directory does not exist.
     */
    public Storage(String filePath) throws DukeIoException {
        String[] path = splitString(filePath, "/");
        String root = Paths.get("").toAbsolutePath().toString();
        this.file = new File(filePath);
        if (!file.exists()) {
            try {
                Files.createDirectories(Paths.get(root + File.separator + path[0]));
                Files.createFile(Paths.get(root + File.separator + path[0] + File.separator + path[1]));
            } catch (IOException e) {
                throw new DukeIoException();
            }
        }
    }

    /**
     * Saves the list of tasks to the storage file.
     *
     * @param tasks the list of tasks to save.
     * @throws DukeIoException when there is I/O error or when parent directory does not exist.
     */
    public void writeToFile(ArrayList<Task> tasks) throws DukeIoException {
        assert file.exists() : "The storage file doesn't exist.";
        try {
            FileWriter fw = new FileWriter(file);
            for (Task task : tasks) {
                fw.write(task.formatSavingName());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeIoException();
        }
    }

    /**
     * Loads the task list from the storage file.
     *
     * @return an empty ArrayList if file does not exist.
     * @throws DukeException if there are errors loading from file and/or converting data from file.
     */
    public ArrayList<Task> load() throws DukeException {
        assert file.exists() : "The storage file doesn't exist.";
        ArrayList<Task> tasks = new ArrayList<Task>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String[] task = splitString(s.nextLine(), ",");
                switch (task[0]) {
                case "todo":
                    tasks.add(new Todo(task[1], Boolean.parseBoolean(task[2])));
                    break;
                case "deadline":
                    tasks.add(new Deadline(task[1], Boolean.parseBoolean(task[2]), LocalDate.parse(task[3])));
                    break;
                case "event":
                    tasks.add(new Event(task[1], Boolean.parseBoolean(task[2]), LocalDate.parse(task[3])));
                    break;
                default:
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Loading error");
        } catch (DateTimeException e) {
            throw new DukeInvalidDateException();
        }
        return tasks;
    }

    private String[] splitString(String string, String regex) {
        return string.split(regex);
    }

}
