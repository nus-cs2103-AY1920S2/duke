package duke;

import duke.exception.DukeException;
import duke.exception.DukeIoException;
import duke.exception.Messages;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Storage that deals with loading tasks from the file.
 * It also deals with saving tasks in the file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a Storage with the specified file path.
     *
     * @param filePath The file path to store data of tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the file and returns the list of tasks.
     *
     * @return The list of tasks loaded from the file.
     * @throws DukeException If an error occur in creating a file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String[] taskDescription = s.nextLine().split(" \\| ");
                Task currentTask = null;
                switch (taskDescription[0]) {
                case "T":
                    currentTask = new ToDo(taskDescription[2]);
                    break;
                case "D":
                    currentTask = new Deadline(taskDescription[2], taskDescription[3]);
                    break;
                case "E":
                    currentTask = new Event(taskDescription[2], taskDescription[3]);
                    break;
                default:
                    break;
                }

                if (currentTask != null && taskDescription[1].equals("1")) {
                    currentTask.markAsDone();
                }
                list.add(currentTask);
            }
            s.close();
        } catch (FileNotFoundException e) {
            createNewFile();
        }
        return list;
    }

    /**
     * Creates a file to store the data of tasks.
     *
     * @throws DukeException If an error occur in creating the file.
     */
    private void createNewFile() throws DukeException {
        try {
            Path path = Paths.get(filePath);
            Path parent = path.getParent();
            Files.createDirectories(parent);
            Files.createFile(path);
        } catch (IOException e) {
            throw new DukeIoException(Messages.MESSAGE_FAIL_CREATE_FILE);
        }
    }

    /**
     * Saves tasks in the file.
     *
     * @param tasks The TaskList which contains tasks to be saved in the file.
     * @throws DukeException If fail to save tasks in the file.
     */
    public void saveTasksToStorage(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task : tasks.getTasks()) {
                fw.write(task.toSaveName());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeIoException(Messages.MESSAGE_FAIL_SAVE_TASKS);
        }

    }
}
