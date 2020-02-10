package duke.storage;

import duke.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.tasklist.TaskList;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * <h1>Storage Class</h1>
 * This class is used to save data into hard disk and retrieve data from the hard disk.
 *
 * @author Eng Xuan En
 */
public class Storage {
    protected String home;
    protected FileWriter fw;
    protected Path filePath;
    protected String absolutePath;
    protected Scanner taskScanner;

    /**
     * Class constructor of Storage which take in filePath in String format.
     *
     * @param filePath path that the data store to.
     */
    public Storage(String filePath) throws DukeException {
        setHomeAndFilePath(filePath.split("/"));
        initialiseScanner();
    }

    /**
     * Set home and file path based on the current directory and path given.
     *
     * @param path Indicate the path of the file located.
     */
    private void setHomeAndFilePath(String[] path) {
        this.home = System.getProperty("user.dir");
        this.filePath = Paths.get(this.home, "src", path[0], path[1]);
        this.absolutePath = this.filePath.toAbsolutePath().toString();
    }

    /**
     * Initialise Scanner to the filepath, if file not exists, create new file.
     *
     * @throws DukeException Occur when file is not exist at that path.
     */
    private void initialiseScanner() throws DukeException {
        try {
            this.taskScanner = new Scanner(this.filePath);
        } catch (IOException e) {
            createFile(this.absolutePath);
        }
    }

    /**
     * Get Home Directory of the programme.
     */
    public String getHomeDirectory() {
        return home;
    }

    /**
     * Check file exist at that path.
     *
     * @param path path the file located
     * @return true: File exists, false: File not exists
     */
    public boolean checkFileExists(Path path) {
        return Files.exists(path);
    }

    /**
     * Retrieve the data from the taskFile.
     *
     * @return List of tasks which the tasks retrieve from the hard disk
     * @throws DukeException occurs when when wrong format of date and time found
     */
    public List<Task> getTaskListing() throws DukeException {
        String[] line;
        List<Task> listing = new ArrayList<>();
        try {
            while (taskScanner.hasNext()) {
                line = taskScanner.nextLine().split("\\s\\|\\s");
                listing.add(generateTaskBasedOnData(line));
            }
        } catch (NullPointerException e) {
            return listing;
        }
        return listing;
    }

    /**
     * Determine which task it belong to and generate the task based on the data given.
     *
     * @param line Data use for processing.
     * @return Task that is generated.
     * @throws DukeException occurs when when wrong format of date and time found
     */
    private Task generateTaskBasedOnData(String[] line) throws DukeException {
        switch (line[0]) {
        case "T":
            return generateTodo(line);
        case "E":
            return generateEvent(line);
        case "D":
            return generateDeadline(line);
        default:
            throw new DukeException("Data corrupted! I am unable to determine which task it belongs to.");
        }
    }

    /**
     * Generate todo and set done if the index 1 of the data given is "1".
     *
     * @param data Description of Todo is at index 2 and done status at index 1.
     * @return Todo task that is generated.
     */
    private Task generateTodo(String[] data) {
        Task task = new Todo(data[2]);
        if (data[1].equals("1")) {
            task.setStatusDone();
        }
        return task;
    }

    /**
     * Generate event and set done if the index 1 of the data given is "1".
     *
     * @param data Description of Event is at index 2, date of event is at index 3 amd done status at index 1.
     * @return Event task that is generated.
     */
    private Task generateEvent(String[] data) throws DukeException {
        Task task = new Event(data[2], data[3]);
        if (data[1].equals("1")) {
            task.setStatusDone();
        }
        return task;
    }

    /**
     * Generate event and set done if the index 1 of the data  given is "1".
     *
     * @param data Description of Event is at index 2, date and time are at index 3 and done status at index 1.
     * @return Deadline task that is generated.
     */
    private Task generateDeadline(String[] data) throws DukeException {
        Task task = new Deadline(data[2], data[3]);
        if (data[1].equals("1")) {
            task.setStatusDone();
        }
        return task;
    }

    /**
     * Close the scanner.
     */
    public void closeScanner() {
        taskScanner.close();
    }

    /**
     * Save tasks to hard disk.
     *
     * @param taskList taskList to retrieve the tasks
     * @throws DukeException occurs when encounter IO Exception
     */
    public void saveTasks(TaskList taskList) throws DukeException {
        int numOfTasks = taskList.getAmountOfTask();
        int num = 1;
        createFile(absolutePath);
        clearFile(absolutePath);
        while (numOfTasks != 0) {
            addTask(taskList.getTask(num));
            num++;
            numOfTasks--;
        }
    }

    /**
     * Clear all contents inside the file at the path.
     *
     * @param path file located at
     */
    public void clearFile(String path) throws DukeException {
        writeToHardDisk("", path, false);
    }

    /**
     * Create file when no file exist at the path.
     *
     * @param path path the file located at
     * @throws DukeException occurs when IOException happens
     */
    public void createFile(String path) throws DukeException {
        try {
            File file = new File(path);
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Sorry! I am unable to create new file at the path!");
        }
    }

    /**
     * Add task to the file in hard disk.
     *
     * @param task task to be added
     * @throws DukeException occurs when IO Exception occurs
     */
    public void addTask(Task task) throws DukeException {
        String taskType;
        int status;
        String period;
        String taskDescription;
        switch (task.getType()) {
        case "todo":
            taskType = "T";
            status = task.getStatus() ? 1 : 0;
            taskDescription = task.getDescription();
            writeToHardDisk(taskType + " | " + status + " | " + taskDescription + "\n",
                    absolutePath, true);
            break;
        case "event":
            taskType = "E";
            status = task.getStatus() ? 1 : 0;
            taskDescription = task.getDescription();
            period = task.getPeriod();
            writeToHardDisk(taskType + " | " + status + " | " + taskDescription + " | " + period + "\n",
                    absolutePath, true);
            break;
        case "deadline":
            taskType = "D";
            status = task.getStatus() ? 1 : 0;
            taskDescription = task.getDescription();
            period = task.getPeriod();
            writeToHardDisk(taskType + " | " + status + " | " + taskDescription + " | " + period + "\n",
                    absolutePath, true);
            break;
        default:
            throw new DukeException("Warning! I do not know what format to save the task as!");
        }
    }

    /**
     * Get path where the files stored at hard disk.
     *
     * @return path object
     */
    public Path getPath() {
        return filePath;
    }

    /**
     * Write a line to the file in hard disk.
     *
     * @param sentence   sentence that write to the file
     * @param path       path that the file located
     * @param appendMode true: append, false: overwrite the file
     * @throws DukeException occurs when IO Exception occurs
     */
    public void writeToHardDisk(String sentence, String path, boolean appendMode) throws DukeException {
        try {
            fw = new FileWriter(path, appendMode);
            fw.write(sentence);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Oops! There are error occurs: " + e.getMessage());
        }

    }
}
