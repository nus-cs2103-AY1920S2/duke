package duke;

import duke.task.Task;
import duke.exception.DukeException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Responsible for storing and reading list data into/from disk respectively.
 *<code>filePath</code> is the location of the file with the list data.
 */
public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves list data of Duke application into disk whenever bye command is executed.
     * @param list The list of tasks inputted by user.
     */
    public void save(ArrayList<Task> list) {
        try {
            FileOutputStream savedFile = new FileOutputStream(new File(filePath));
            ObjectOutputStream savedData = new ObjectOutputStream(savedFile);
            savedData.writeObject(list);
            savedData.close();
            savedFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Used when starting the Duke Application.
     * @return List of Tasks stored in disk.
     * @throws DukeException when filePath is incorrect.
     */
    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> result = new ArrayList<>();
        try {
            File savedFile = new File(filePath);
            // read from save file
            if (!savedFile.exists()) {
                File directory = new File(savedFile.getParent());
                if (!directory.exists()) {
                    boolean mkdirs = directory.mkdirs();
                }
                boolean newFile = savedFile.createNewFile();
            } else {
                FileInputStream readFile = new FileInputStream(savedFile);
                ObjectInputStream readData = new ObjectInputStream(readFile);
                Object object = readData.readObject();
                result = (ArrayList<Task>) object;
                readData.close();
                readFile.close();
            }
        } catch (ClassNotFoundException e) {
            throw new DukeException("Class Name is not matching input!");
        } catch (IOException e) {
            throw new DukeException("There is no save file to load.");
        }
        return result;
    }
}
