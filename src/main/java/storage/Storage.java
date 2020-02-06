package main.java.storage;

import main.java.exceptions.IllegalDateTimeFormatException;
import main.java.exceptions.InvalidStorageFilePathException;
import main.java.exceptions.NoDescriptionException;
import main.java.exceptions.StorageOperationException;
import main.java.model.Task;
import main.java.model.TaskList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Storage {
    public static final String DEFAULT_STORAGE_PATH = "tasks.txt";

    public Path path;

    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_PATH);
    }

    public Storage(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    public static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }

    public void save(TaskList taskList) throws IOException{
        List<String> encodedTaskList = TaskListEncoder.encodeTask(taskList);
        Files.write(path, encodedTaskList);
    }

    public TaskList load() throws
            StorageOperationException, IOException, NoDescriptionException, IllegalDateTimeFormatException {
        //TODO: throw new exception
        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }

        return TaskListDecoder.decodeTaskList(Files.readAllLines(path));
    }
}
