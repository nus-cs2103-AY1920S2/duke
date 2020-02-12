package storage;

import exceptions.IllegalDateTimeFormatException;
import exceptions.InvalidStorageFilePathException;
import exceptions.NoDescriptionException;
import exceptions.StorageOperationException;
import model.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * This class interacts with text files in the local directory
 * such that the program can load data from local disk or save data to files.
 */
public class Storage {
    private final static String DEFAULT_STORAGE_PATH = "tasks.txt";
    private Path path;

    /**
     * Default constructor with the default path of local data file.
     * @throws InvalidStorageFilePathException
     */
    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_PATH);
    }

    /**
     * Set the path of the data file to the customized path.
     * Throws exception if the path is not valid. E.g. Doesn't end with ".txt"
     * @param filePath customized file path.
     * @throws InvalidStorageFilePathException
     */
    public Storage(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    /**
     * Returns true if the inputted path ends with ".txt"
     * @param filePath
     * @return
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    /**
     * Encode the tasklist to a list of string by calling the TaskListEncoder then
     * write the list to a local file.
     *
     * @param taskList inner-list of the program.
     * @throws IOException
     */
    public void save(TaskList taskList) throws IOException {
        List<String> encodedTaskList = TaskListEncoder.encodeTask(taskList);
        Files.write(path, encodedTaskList);
    }

    /**
     * Reads the data from local file. Returns empty task list if the path doesn't exist or
     * the path doesn't point to a regular file.
     * @return tasklist
     * @throws StorageOperationException If there is encoded task list is in invalid format.
     * @throws IOException If there is error while reading or writing the file.
     * @throws NoDescriptionException If the loaded description for any task is empty.
     * @throws IllegalDateTimeFormatException If any loaded date time string is empty.
     */
    public TaskList load() throws
            StorageOperationException, IOException, NoDescriptionException, IllegalDateTimeFormatException {
        //TODO: throw new exception
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }

        return TaskListDecoder.decodeTaskList(Files.readAllLines(path));
    }
}
