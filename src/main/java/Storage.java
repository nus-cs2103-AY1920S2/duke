import java.util.ArrayList;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

import java.nio.file.Paths;
import java.nio.file.Files;

public class Storage {
    public static final String savePath = "data/duke.txt";
    
    Storage() {
    }
    
    @SuppressWarnings("unchecked")
    private ArrayList<Task> loadTaskState() throws IOException, ClassNotFoundException, ClassCastException {
        FileInputStream f = new FileInputStream(savePath);
        ObjectInputStream o = new ObjectInputStream(f);
        
        ArrayList<Task> tasks = (ArrayList<Task>)o.readObject();
        o.close();
        
        return tasks;
    }
    
    public ArrayList<Task> load() throws DukeException, FileNotFoundException {
        try {
            return loadTaskState();
        } catch (IOException e) {
            throw new DukeException("Failed to load task list");
        } catch (ClassNotFoundException e) {
            throw new DukeException("Malformed task list file");
        }
    }

    public void saveTaskState(ArrayList<Task> tasks) throws IOException {
        //recursively create directories to save path if they don't exist
        Files.createDirectories(Paths.get(savePath).getParent());
        
        //save to file
        FileOutputStream f = new FileOutputStream(savePath);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(tasks);
        o.close();
    }
    
    public void save(ArrayList<Task> tasks) throws DukeException {
        try {
            saveTaskState(tasks);
        } catch (IOException e) {
            throw new DukeException("Failed to save task list.");
        }
    }
}
