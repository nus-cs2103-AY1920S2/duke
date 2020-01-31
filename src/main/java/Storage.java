import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.io.FileNotFoundException;
import java.io.IOException;

/** Handles storing and loading of TaskList to and from persistent storage. */
public class Storage {

    protected String filePath;

    /**
     * Constructor for Storage class.
     *
     * @param filepath path where TaskList is stored.
     */
    public Storage(String filepath) {
        this.filePath = filepath;
    }

    /**
     * Retrieves a stored file from persistent storage if there is one, if not retrieve a new TaskList.
     *
     * @return stored TaskList if available, else empty TaskList.
     */
    public TaskList load() {
        TaskList res = new TaskList();

        try {
            File savedData = new File(filePath);
            FileInputStream fis = new FileInputStream(savedData);
            ObjectInputStream ois = new ObjectInputStream(fis);
            TaskList lstSaved = (TaskList) ois.readObject();
            ois.close();
            System.out.println("    Retrieving my little boy's history..");
            res = lstSaved;

        } catch (FileNotFoundException e) {
            System.out.println("    Initialising new list for my little boy..");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * Stores a TaskList to persistent storage.
     *
     * @param lst TaskList to be stored.
     */
    public void save(TaskList lst) {
        try {
            FileOutputStream fos = new FileOutputStream (filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(lst);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
