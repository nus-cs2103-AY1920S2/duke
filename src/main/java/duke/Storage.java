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

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    @SuppressWarnings("unchecked")
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> result = new ArrayList<>();
        try {
            File savedFile = new File(filePath);
            // read from save file
            FileInputStream readFile = new FileInputStream(savedFile);
            ObjectInputStream readData = new ObjectInputStream(readFile);
            Object object = readData.readObject();
            result = (ArrayList<Task>) object;
            readData.close();
            readFile.close();
        } catch (ClassNotFoundException e) {
            System.out.println("Class Name is not matching input!");
        } catch (IOException e) {
            throw new DukeException("There is no save file to load.");
        }
        return result;
    }
}
