package duke;

import duke.task.*;

import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 * Saves files in a directory data under the project root path
 */
public class Storage {
    protected final String FILE_SEPARATOR = File.separator;
    // Map project path to the directory from which you run your program
    public final String PROJECT_ROOT_PATH = Paths.get("").toAbsolutePath().toString();
    protected String dataDirectoryPath = PROJECT_ROOT_PATH + FILE_SEPARATOR + "data";
    protected String saveFilePath;

    public Storage(String fileName) {
        saveFilePath = dataDirectoryPath + FILE_SEPARATOR + fileName;
        // Setup data directory
        setupDataDirectory();
        createSaveFile();
    }

    protected void setupDataDirectory() {
        try {
            // Create directories along path if they don't exist
            Files.createDirectories(Paths.get(dataDirectoryPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
                switch(taskWords[0].toLowerCase()) {
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
