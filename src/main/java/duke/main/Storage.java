package duke.main;

import duke.exception.CannotReadFileException;
import duke.exception.CannotSaveFileException;
import duke.task.Task;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    //Custom dataRead Method to read from file
    static List<Task> dataRead() throws CannotReadFileException {
        try {
            Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
            FileInputStream dataFile = new FileInputStream(String.valueOf(path));
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
    public static void dataSave(List<Task> taskList) throws CannotSaveFileException {
        try {
            Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
            FileOutputStream dataFile = new FileOutputStream(String.valueOf(path));
            ObjectOutputStream out = new ObjectOutputStream(dataFile);
            out.writeObject(taskList);
            out.close();
            dataFile.close();
        } catch (IOException ignored) {
            throw new CannotSaveFileException();
        }
    }
}
