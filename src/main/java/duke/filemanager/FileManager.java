package duke.filemanager;

import duke.DukeException;
import duke.Database;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.File;
import java.nio.file.Files;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    protected String home;
    protected FileWriter fw;
    protected Path taskPath;
    protected Path path;
    protected String absolutePath;
    protected Scanner taskScanner;

    /**
     * Constructor of the filemanager.FileManager
     */
    public FileManager() {
        home = System.getProperty("user.dir");
        path = Paths.get(home, "src", "storage");
        taskPath = Paths.get(home, "src", "storage", "tasks.txt");
        absolutePath = taskPath.toAbsolutePath().toString();
    }

    /**
     * Get Home Directory of the programme
     */
    public String getHomeDirectory() {
        return home;
    }

    /**
     * Save data into the file in hardDisk
     * @param data data to be saved
     * @param path file located at
     * @param append true: append mode, false: overwrite the file
     * @throws DukeException occurs when IO Exception happens
     */
    public void saveDataToHardDisk(String data, Path path, boolean append) throws DukeException {
        // Will create file if no file exists at the path
        createFile(path.toAbsolutePath().toString());
        writeToHardDisk(data, path.toString(), append);
    }

    /**
     * Check file exist at that path
     * @param path path the file located
     * @return true: File exists, false: File not exists
     */
    public boolean checkFileExists(Path path) {
        return Files.exists(path);
    }

    /**
     * Get task listing and add to database
     * @param database database to load the listing to
     */
    public void getTaskListing(Database database) throws DukeException {
        String[] line;
        Task task;
        try {
            taskScanner = new Scanner(taskPath);
            while (taskScanner.hasNext()) {
                line = taskScanner.nextLine().split("\\s\\|\\s");
                switch (line[0]) {
                    case "T":
                        task = new Todo(line[2]);
                        if (line[1].equals("1")) {
                            task.setStatusDone();
                        }
                        database.addTask(task);
                        break;
                    case "E":
                        task = new Event(line[2], line[3]);
                        if (line[1].equals("1")) {
                            task.setStatusDone();
                        }
                        database.addTask(task);
                        break;
                    case "D":
                        task = new Deadline(line[2], line[3]);
                        if (line[1].equals("1")) {
                            task.setStatusDone();
                        }
                        database.addTask(task);
                        break;
                }
            }
            taskScanner.close();
        } catch(IOException e) {
            // File not exists, hence create new file
            createFile(taskPath.toAbsolutePath().toString());
        }

    }

    /**
     * Save tasks to hard disk
     * @param database database to retrieve the tasks
     * @throws DukeException occurs when encounter IO Exception
     */
    public void saveTasks(Database database) throws DukeException {
        int numOfTasks = database.getAmountOfTask();
        int num = 1;
        createFile(absolutePath);
        clearFile(absolutePath);
        while (numOfTasks != 0) {
            addTask(database.getTask(num));
            num++;
            numOfTasks--;
        }
    }

    /**
     * Clear all contents inside the file at the path
     * @param path file located at
     */
    public void clearFile(String path) throws DukeException {
        writeToHardDisk("", path, false);
    }

    /**
     * Create file when no file exist at the path
     * @param path path the file located at
     * @throws DukeException occurs when IOException happens
     */
    public void createFile(String path) throws DukeException {
        try {
            File file = new File(path);
            file.createNewFile();
        } catch(IOException e) {
            throw new DukeException("Sorry! I am unable to create new file at the path!");
        }
    }

    /**
     * Add task to the file in hard disk
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
                status = task.getStatus()? 1: 0;
                taskDescription = task.getDescription();
                writeToHardDisk(taskType + " | " + status + " | " + taskDescription + "\n",
                        absolutePath, true);
                break;
            case "event":
                taskType = "E";
                status = task.getStatus()? 1: 0;
                taskDescription = task.getDescription();
                period = task.getPeriod();
                writeToHardDisk(taskType + " | " + status + " | " + taskDescription + " | " + period + "\n",
                        absolutePath, true);
                break;
            case "deadline":
                taskType = "D";
                status = task.getStatus()? 1: 0;
                taskDescription = task.getDescription();
                period = task.getPeriod();
                writeToHardDisk(taskType + " | " + status + " | " + taskDescription + " | " + period + "\n",
                        absolutePath, true);
                break;
        }
    }

    /**
     * Get path where the files stored at hard disk
     * @return path object
     */
    public Path getPath() {
        return path;
    }

    /**
     * Write a sentence to the file in hard disk
     * @param sentence sentence that write to the file
     * @param path path that the file located
     * @param appendMode true: append, false: overwrite the file
     * @throws DukeException occurs when IO Exception occurs
     */
    public void writeToHardDisk(String sentence, String path, boolean appendMode) throws DukeException {
        try {
            fw = new FileWriter(path, appendMode);
            fw.write(sentence);
            fw.close();
        } catch(IOException e) {
            throw new DukeException("Oops! There are error occurs: " + e.getMessage());
        }

    }
}
