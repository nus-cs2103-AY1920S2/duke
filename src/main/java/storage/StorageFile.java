package storage;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import common.Messages;
import data.TaskManager;
import data.exceptions.StorageOperationException;
import parser.EncodeToFile;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Represents the file used to store task list data.
 * save to both text file and json file
 */
public class StorageFile {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = Messages.TXT_FILE_PATH;
    public static final String DEFAULT_STORAGE_JSON_FILEPATH = Messages.JSON_FILE_PATH;
    private static String jsonFilePath = DEFAULT_STORAGE_JSON_FILEPATH;
    private Path filePath;

    /**
     * default constructor (if the user does not specified file location
     */
    public StorageFile() throws StorageOperationException {
        this(DEFAULT_STORAGE_FILEPATH);
        this.filePath = Paths.get(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * Parses user input into command for execution.
     *
     * @param filePath
     * @throws StorageOperationException
     * @retuen parsed storage file object
     */
    public StorageFile(String filePath) throws StorageOperationException {
        this.filePath = Paths.get(filePath);
        if (!isValidPath(this.filePath)) {
            throw new StorageOperationException("Storage file should end with '.txt'");
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param taskManager taskManager management system
     * @throws StorageOperationException
     */
    public void save(TaskManager taskManager) throws StorageOperationException {
        try {
            List<String> encodedDuke = EncodeToFile.encodeAllTasks(taskManager);
            Files.write(filePath, encodedDuke);
        } catch (IOException ioe) {
            throw new StorageOperationException(
                    String.format(Messages.MESSAGE_FILE_OPERATION_IO_ERROR, filePath));
        }
    }

    /**
     * Parses user input into command for execution.
     *
     * @param filePath
     * @return (boolean) if the filePath is valid, return true
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".txt");
    }


    public static void saveJson(TaskManager taskManager) throws StorageOperationException {
        //System.out.printf(FIVE_SPACES + SAVE_DATA_TO_FILE_PROMPT_FORMAT_STRING, DATA_FILE_PATH);
        try {
            Gson gson = new GsonBuilder().create();
            FileWriter fw = new FileWriter(String.valueOf(jsonFilePath));
            String json = gson.toJson(taskManager.getTaskList().getInternalList());
            fw.write(json);
            fw.flush();
            fw.close();
            //System.out.println(FIVE_SPACES + DATA_SAVED_SUCCESSFULLY_PROMPT);
        } catch (IOException e) {
            throw new StorageOperationException(
                    String.format(Messages.MESSAGE_FILE_OPERATION_IO_ERROR, jsonFilePath));
        }
    }
}
