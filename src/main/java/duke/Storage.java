package duke;


import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 * Saves files in a directory data under the project root path.
 */
public class Storage {
    protected String fileSeparator = File.separator;
    // Map project path to the directory from which you run your program
    protected String projectRootPath = Paths.get("").toAbsolutePath().toString();
    protected String dataDirectoryPath = projectRootPath + fileSeparator + "data";
    protected String saveFilePath;

    /**
     * Returns a new Storage instance.
     *
     * @param fileName file used to save user data
     */
    public Storage(String fileName) {
        saveFilePath = dataDirectoryPath + fileSeparator + fileName;
        // Setup data directory
        setupDataDirectory();
        createSaveFile();
    }

    /**
     * Creates the required directories for saving user data.
     */
    protected void setupDataDirectory() {
        try {
            // Create directories along path if they don't exist
            Files.createDirectories(Paths.get(dataDirectoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new save file in the path created and stored by the Storage constructor.
     */
    protected void createSaveFile() {
        try {
            // Create a new file, exception will be thrown if file already exists
            Files.createFile(Paths.get(saveFilePath));
        } catch (FileAlreadyExistsException e) {
            // File exists
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a list of Tasks that represent the tasks saved in the save file.
     *
     * @return list of Tasks saved in specified save file when the Storage instance is created
     * @throws DukeException the given save file could not be loaded
     */
    protected List<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader saveFile = new BufferedReader(new FileReader(saveFilePath))) {
            // Load data into tasks
            String line = saveFile.readLine();
            /* Format of save file
            [task type],[complete status],[task information]...
            Example:
            todo,1,read book
            deadline,0,return book,June 6th
            event,0,project meeting,Aug 6th 2-4pm
            */
            while (line != null) {
                // Store task
                String[] taskWords = line.split(",");
                boolean isDone = taskWords[1].equals("1");
                String description = taskWords[2];
                switch (taskWords[0].toLowerCase()) {
                case "todo":
                    tasks.add(new Todo(taskWords[2], isDone));
                    break;
                case "deadline":
                    String deadline = taskWords[3];
                    tasks.add(new Deadline(description, deadline, isDone));
                    break;
                case "event":
                    String eventTime = taskWords[3];
                    tasks.add(new Event(description, eventTime, isDone));
                    break;
                default:
                    break;
                }
                line = saveFile.readLine();
            }
        } catch (IOException e) {
            throw new DukeException("Unable to load from given file...");
        }
        return tasks;
    }

    /**
     * Updates the lists of Tasks stored in the save file.
     *
     * @param tasks list of Tasks to be saved
     */
    public void updateSaveFile(TaskList tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveFilePath))) {
            // Write all tasks to file
            for (Task task : tasks) {
                writer.write(task.stringToSaveToDisk());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
