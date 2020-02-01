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
        String[] path = filePath.split("/");
        this.home = System.getProperty("user.dir");
        this.filePath = Paths.get(this.home, "src", path[0], path[1]);
        this.absolutePath = this.filePath.toAbsolutePath().toString();
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
        Task task;
        try {
            while (taskScanner.hasNext()) {
                line = taskScanner.nextLine().split("\\s\\|\\s");
                switch (line[0]) {
                case "T":
                    task = new Todo(line[2]);
                    if (line[1].equals("1")) {
                        task.setStatusDone();
                    }
                    listing.add(task);
                    break;
                case "E":
                    task = new Event(line[2], line[3]);
                    if (line[1].equals("1")) {
                        task.setStatusDone();
                    }
                    listing.add(task);
                    break;
                case "D":
                    task = new Deadline(line[2], line[3]);
                    if (line[1].equals("1")) {
                        task.setStatusDone();
                    }
                    listing.add(task);
                    break;
                default:
                    throw new DukeException("Data corrupted! I am unable to determine which task it belongs to.");
                }
            }
        } catch (NullPointerException e) {
            return listing;
        }
        return listing;
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
