package duke.storage;

import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.exceptions.DukeException;

import java.util.ArrayList;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;

import java.nio.file.Paths;
import java.nio.file.Files;

/**
 * Encapsulates serialization and external storage of the task list state.
 */
public class Storage {
    public static final String STORAGE_SAVE_PATH = "data/duke.txt";
    
    private ArrayList<Task> loadTaskState()
        throws IOException, ClassNotFoundException, ClassCastException {
        FileInputStream f = new FileInputStream(STORAGE_SAVE_PATH);
        ObjectInputStream out = new ObjectInputStream(f);
        
        @SuppressWarnings("unchecked") //necessary due to unchecked cast to ArrayList<Task>
        ArrayList<Task> tasks = (ArrayList<Task>)out.readObject();
        
        for (Object obj : tasks) {
            assert obj instanceof Task;
        }
        
        out.close();
        
        return tasks;
    }
    
    /**
     * Loads the `ArrayList` of `Task`s from the path specified in `STORAGE_SAVE_PATH`.
     * @return List of tasks
     * @throws FileNotFoundException  if the file containing the task lists does not exist
     * @throws DukeException if the task list is malformed, or if an I/O error occured
     */
    public ArrayList<Task> load() throws DukeException, FileNotFoundException {
        try {
            return loadTaskState();
        } catch (IOException e) {
            throw new DukeException("Failed to load task list");
        } catch (ClassNotFoundException e) {
            throw new DukeException("Malformed task list file");
        }
    }

    private void saveTaskState(ArrayList<Task> tasks) throws IOException {
        assert tasks != null;
        
        //recursively create directories to save path if they don't exist
        Files.createDirectories(Paths.get(STORAGE_SAVE_PATH).getParent());
        
        //save to file
        FileOutputStream f = new FileOutputStream(STORAGE_SAVE_PATH);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(tasks);
        o.close();
    }
    
    /**
     * Saves the `ArrayList` of `Task`s to the path specified in `STORAGE_SAVE_PATH`.
     * @param tasks List of tasks
     * @throws DukeException if the `tasks` list cannot be saved to file
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        assert tasks != null;
        
        try {
            saveTaskState(tasks);
        } catch (IOException e) {
            throw new DukeException("Failed to save task list.");
        }
    }
}
