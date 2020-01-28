package duke;

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
                String[] task = s.nextLine().split(" \\| ");
                Task temp = null;
                switch (task[0]) {
                    case "T":
                        temp = new ToDo(task[2]);
                        break;
                    case "D":
                        temp = new Deadline(task[2], task[3]);
                        break;
                    case "E":
                        temp = new Event(task[2], task[3]);
                        break;
                    default:
                        break;
                }
                if (task[1].equals("1")) {
                    temp.markAsDone();
                }
                list.add(temp);
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
    private void createNewFile() throws DukeException{
        try {
            Path path = Paths.get(filePath);
            Path parent = path.getParent();
            Files.createDirectories(parent);
            Files.createFile(path);
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Could not create file for data.");
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
            FileWriter fw = new FileWriter("data/duke.txt");
            for (Task task: tasks.getTasks()) {
                fw.write(task.toSaveName());
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Fail to save tasks to storage.");
        }

    }
}
