package duke.main;

import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import duke.DukeException;
import duke.task.Task;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    protected String filePath;

    public Storage(String filePath) {
        assert filePath.equals(System.getProperty("user.dir") + "/src/main/resources/task-list.txt")
                : "Wrong file path";
        this.filePath = filePath;
    }

    /**
     * Loads the contents of the task list into an array list of strings.
     *
     * @return Array list of tasks (as strings) loaded from the task list
     * @throws DukeException If task list does not exist or failed to load
     */
    public ArrayList<String> load() throws DukeException {
        ArrayList<String> taskListInString = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                taskListInString.add(sc.nextLine());
            }
        } catch (IOException ioe) {
            throw new DukeException("Task list does not exist, has been moved, or failed to load.");
        }
        return taskListInString;
    }

    /**
     * Saves the latest state of the task list.
     *
     * @param list Task list
     * @throws DukeException If failed to save the list
     */
    public void save(ArrayList<Task> list) throws DukeException {
        try {
            File f = new File(filePath);
            FileOutputStream outputStream = new FileOutputStream(f);
            for (Task task : list) {
                String toWrite = task.toString() + "\n";
                outputStream.write(toWrite.getBytes());
            }
        } catch (IOException ioe) {
            throw new DukeException("Failed to save the list!");
        }
    }
}
