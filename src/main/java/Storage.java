import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;


/**
 * Represents the interface to data storage in memory.
 * Enables task list data to be save dto or loaded from a file.
 */
public class Storage {
    /**
     * Path specifying the location of the storage file.
     */
    String filePath;

    /**
     * The parser that encodes and decodes between JSON and objects.
     */
    Gson jsonParser = new Gson();

    /**
     * Constructor for Storage that takes in the path to the intended storage file.
     * @param filePath the path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns TaskList obtained from the file specified by filePath.
     *
     * @return the TaskList loaded from the data
     * @throws FileNotFoundException if the file does not exist
     */
    public TaskList load() throws FileNotFoundException {
        BufferedReader br = new BufferedReader(new FileReader(getFile()));

        return jsonParser.fromJson(br, TaskList.class);
    }

    /**
     * Saves the given TaskList as a JSON string to the file specified by filePath.
     *
     * @param tasks the TaskList to be saved
     */
    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(getFile());
            fw.append(jsonParser.toJson(tasks));
            fw.close();
        } catch (FileNotFoundException e) {
            getFile().getParentFile().mkdirs();
            save(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getFile() {
        return new File(filePath);
    }
}
