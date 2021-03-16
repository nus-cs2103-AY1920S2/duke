package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import duke.task.Task;

/**
 * Serves as the class that helps in saving Duke program data into the disk as well as load
 * data back into the program from the disk when Duke starts up again.
 *
 * @author Dargo
 */
public class Storage {

    // filepath of the stored data
    private String filePath;

    /**
     * Loads data into Duke program at startup.
     * Saves data into the disk when Duke program terminates.
     *
     * @param filepath Filepath of saved data in Duke.
     */
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Loads previously added tasks from last usage when Duke program starts.
     *
     * @return ArrayList<Task></Task> of previously added tasks.
     */
    public ArrayList<Task> load() {
        File file = new File(this.filePath);
        ArrayList<Task> storedData = new ArrayList<Task>();

        try {
            if (file.length() != 0) {

                FileInputStream fileIn = new FileInputStream(this.filePath);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn);
                storedData = (ArrayList<Task>) objectIn.readObject();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return storedData;
    }

    /**
     * Saves added tasks into the disk when Duke program terminates.
     *
     * @param savedData ArrayList<Task></Task> of current state of added tasks.
     */
    public void save(ArrayList<Task> savedData) {
        try {
            FileOutputStream fileOut = new FileOutputStream(this.filePath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(savedData);
            objectOut.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
