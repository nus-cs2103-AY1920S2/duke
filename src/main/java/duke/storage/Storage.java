package duke.storage;

import duke.task.TaskList;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.charset.Charset;

import java.util.List;

/**
 * Storage for tasks stored in task list.
 */
public class Storage {

    /**
     * Filepath to store the text file to.
     */
    private String filepath;

    /**
     * File object to represent the file destination.
     */
    private File file;

    /**
     * Constructor for the storage object.
     * Creates a new text file for data storage if there isn't an existing one.
     *
     * @param filepath The destination for the saved text file.
     * @throws IOException Throws exception if there is an error in creating the specified filepath for saving.
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
     * To load the task list from saved text file (if any).
     * @throws IOException Throws exception if there is an error in reading task list data from saved text file.
     */
   public TaskList loadTaskList() throws IOException {
       List<String> data = FileUtils.readLines(file, Charset.defaultCharset());
       return new TaskList(data);
   }

    /**
     * To save the task list to the filepath specified.
     * @throws IOException Throws exception if there is an error saving task list data to the specified filepath.
     */
   public void saveTaskList(TaskList taskList) throws IOException {
       FileWriter fileWriter = new FileWriter(file);
       fileWriter.write(taskList.getTaskListSaveFormat());
       fileWriter.flush();
   }

}
