package duke;

import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.File;
import java.util.ArrayList;

/**
 * Storage manages reading and writing of data from and to txt file.
 */
public class Storage {
    protected String filePath;

    /**
     * Takes in path of txt tile.
     *
     * @param filePath of stored data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data from txt file and put into ArrayList.
     *
     * @return Data of ArrayList stored
     */
    public ArrayList<Task> load() {
        File file = new File(this.filePath);
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            if (file.length() != 0) {
                FileInputStream fin = new FileInputStream(file);
                ObjectInputStream oit = new ObjectInputStream(fin);
                taskList = (ArrayList<Task>) oit.readObject();
            }

        } catch (Exception ex) {
            Ui ui = new Ui();
            ui.showLoadingError();
        }

        return taskList;
    }

    /**
     * Stores TaskList into txt file.
     *
     * @param taskList of data to be stored
     */
    public void store(TaskList taskList) {
        try {
            FileOutputStream fout = new FileOutputStream(this.filePath);
            ObjectOutputStream oot = new ObjectOutputStream(fout);
            oot.writeObject(taskList.tasks);
            oot.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
