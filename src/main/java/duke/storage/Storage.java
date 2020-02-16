package duke.storage;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The type Storage.
 */
public class Storage {

    private String filePath;
    private String storedItems = "";
    private File file;

    /**
     * Instantiates a new Storage.
     *
     * @param filePath the file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath);
    }

    /**
     * Save task into the file. Will append to whatever is already inside the file
     *
     * @param task the task
     * @throws IOException the io exception
     */
    public void saveTask(Task task) throws IOException {
        file.getParentFile().mkdirs();
        FileWriter fw = new FileWriter(file, true);
        fw.write(task.toString() + System.lineSeparator());
        fw.close();
    }

    /**
     * Gets file path.
     *
     * @return the file path
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Gets number of tasks.
     *
     * @return the number of tasks
     * @throws FileNotFoundException the file not found exception
     */
    public int getNumberOfTasks() throws FileNotFoundException {
        int counter = 0;
        File object = getFile();
        Scanner myScanner = new Scanner(object);
        storedItems = "";
        while (myScanner.hasNextLine()) {
            storedItems += myScanner.nextLine() + System.lineSeparator();
            counter++;
        }
        return counter;
    }

    /**
     * Gets stored items.
     *
     * @return the stored items
     */
    public String getStoredItems() {
        return storedItems;
    }

    /**
     * Gets file.
     *
     * @return the file
     */
    public File getFile() {
        return file;
    }


}
