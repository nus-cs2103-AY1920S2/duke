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

public class Storage {
    private static final String DEFAULT_STORAGE_PATH = "tasks.txt";

    private Path path;

    public Storage() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_PATH);
    }

    public Storage(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.txt'");
        }
    }

    private static boolean isValidPath(Path filePath) {
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
