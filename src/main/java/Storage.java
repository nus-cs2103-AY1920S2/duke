import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    //Custom dataRead Method to read from file
    static List<Task> dataRead() throws CannotReadFileException {
        try {
            FileInputStream dataFile = new FileInputStream("./data/duke.txt");
            ObjectInputStream in = new ObjectInputStream(dataFile);
            ArrayList<Task> taskList = (ArrayList<Task>) in.readObject();
            in.close();
            dataFile.close();

            return taskList;
        } catch (IOException | ClassNotFoundException ignored) {
            throw new CannotReadFileException();
        }
    }

    //Custom dataSave Method to save to file
    static void dataSave(List<Task> taskList) throws CannotSaveFileException {
        try {
            FileOutputStream dataFile = new FileOutputStream("./data/duke.txt");
            ObjectOutputStream out = new ObjectOutputStream(dataFile);
            out.writeObject(taskList);
            out.close();
            dataFile.close();
        } catch (IOException ignored) {
            throw new CannotSaveFileException();
        }
    }
}
