package app.core;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import app.core.tasks.Task;
import app.exceptions.StorageFileException;

/**
 * This class manages all storage related tasks in Duke.
 */
public class StorageManager {
    /**
     * Default Storage Directory.
     */
    public static final String STORAGE_DIR = "./data/";
    public static final String STORAGE_FILENAME = "tasks.txt";
    private static final String STORAGE_FILEPATH = STORAGE_DIR + STORAGE_FILENAME;

    /**
     * Saves a list of tasks in storage.
     * @param tasks The list of tasks
     */
    public void save(List<Task> tasks) throws StorageFileException {
        try {
            File outputDir = new File(STORAGE_DIR);
            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            FileWriter writer = new FileWriter(STORAGE_FILEPATH);
            for (Task task : tasks) {
                writer.write(task.toStorage());
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new StorageFileException(
                String.format(Messages.STORAGE_SAVE_ERROR_MESSAGE, STORAGE_FILEPATH)
            );
        }
    }

    /**
     * Loads a list of tasks from the storage.
     * @return a parsed list of tasks
     */
    public List<Task> load() {
        try {
            List<Task> output = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(STORAGE_FILEPATH));
            reader.lines()
                .forEach(line -> output.add(Task.fromStorage(line)));
            reader.close();
            return output;
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}