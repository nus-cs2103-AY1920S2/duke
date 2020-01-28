package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The type Storage.
 */
public class Storage {

    /**
     * The File path.
     */
    String filePath;

    /**
     * Instantiates a new Storage.
     *
     * @param filePath the file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Save task into the file. Will append to whatever is already inside the file
     *
     * @param task the task
     * @throws IOException the io exception
     */
    public void saveTask(Task task) throws IOException {

        FileWriter fw = new FileWriter(this.filePath,true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }
}
