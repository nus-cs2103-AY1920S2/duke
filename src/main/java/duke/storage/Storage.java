package duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import duke.exception.StorageOperationException;
import duke.task.TaskList;

import static duke.common.Messages.generateReadFromFileErrorMessage;
import static duke.common.Messages.generateWriteToFileErrorMessage;

/**
 * Represents the storage operation handler of the application.
 */
public class Storage {
    private static final String DEFAULT_STORAGE_FILEPATH = "data/duke.txt";

    private final File file;

    /**
     * Constructs a new Storage instance with the default storage file path of "data/duke.txt".
     *
     */
    public Storage() {
        file = new File(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Saves the specified TaskList to the storage file.
     *
     * @param taskList The TaskList to be saved.
     * @throws StorageOperationException If there are errors converting and/or storing data to the storage file.
     */
    public void saveTaskListToStorage(TaskList taskList) throws StorageOperationException {
        file.getParentFile().mkdirs();
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(TaskEncoder.encodeTasksList(taskList));
            fileWriter.close();
        } catch (IOException e) {
            throw new StorageOperationException(generateWriteToFileErrorMessage(file.getAbsolutePath()));
        }
    }

    /**
     * Returns the loaded TaskList from the storage file.
     * Returns an empty TaskList if the storage file does not exist.
     *
     * @return The TaskList that is loaded from the storage file.
     * @throws StorageOperationException If there are errors reading and/or converting data from the storage file.
     */
    public TaskList loadTaskListFromStorage() throws StorageOperationException {
        if (!file.exists()) {
            return new TaskList();
        }

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            List<String> taskListInString = bufferedReader
                    .lines()
                    .collect(Collectors.toList());
            bufferedReader.close();
            return TaskDecoder.decodeTasksList(taskListInString);
        } catch (FileNotFoundException e) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
        } catch (IOException e) {
            throw new StorageOperationException(generateReadFromFileErrorMessage(file.getAbsolutePath()));
        }
    }
}
