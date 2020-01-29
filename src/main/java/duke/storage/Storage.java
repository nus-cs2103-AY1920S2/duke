package duke.storage;

import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/** Storage for tasks stored in task list. */
public class Storage {

    /** Filepath to store the text file to. */
    private String filepath;

    /** File object to represent the file destination. */
    private File file;

    /**
     * Constructs the storage for the Duke program.
     * Creates a new text file for data storage if it doesn't exist.
     *
     * @param filepath The destination for the saved text file.
     * @throws IOException Exception if there is an error in creating the specified filepath for saving.
     */
    public Storage(String filepath) throws IOException {
        this.filepath = filepath;
        file = new File(filepath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            file = new File(filepath);
        }
    }

    /**
     * Loads the task list from saved text file (if any).
     *
     * @throws IOException Exception if there is an error in reading task list data from saved text file.
     */
   public TaskList loadTaskList() throws DukeException, IOException {
       Scanner scanner = new Scanner(this.file);
       ArrayList<String> data = new ArrayList<>();
       while (scanner.hasNext()) {
           data.add(scanner.nextLine());
       }
       return new TaskList(data);
   }

    /**
     * Saves the task list to the filepath specified.
     *
     * @param taskList The task list to be saved.
     * @throws IOException Exception if there is an error saving task list data to the specified filepath.
     */
   public void saveTaskList(TaskList taskList) throws IOException {
       FileWriter fileWriter = new FileWriter(file);
       fileWriter.write(taskList.getTaskListSaveFormat());
       fileWriter.flush();
   }

}
