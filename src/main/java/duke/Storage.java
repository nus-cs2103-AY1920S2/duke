package duke;

import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import duke.task.Task;

public class Storage {

    // filepath of duke.txt
    private String filePath;

    public Storage(String filepath) {
        this.filePath = filepath;
    }

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
