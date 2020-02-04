package duke.main;

import duke.task.Task;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles the storage of TaskList object
 */
public class Storage {

    /**
     * String filepath of file used to save and load the TaskList into the file
     */
    String filepath;

    /**
     * List of Tasks
     */
    List<Task> taskList;

    /**
     * Default constructor for Storage class
     */
    public Storage() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructor for Storage class with file specified
     *
     * @param filepath Path of file to be used for storage
     */
    public Storage(String filepath) {
        this();
        this.filepath = filepath;
    }
  
    /**
     * Loads TaskList records from a specified file
     *
     * @return This Storage object
     */
    public Storage load() {
        loadFile();
        return this;
    }

    /**
     * Get List of Tasks
     *
     * @return List of Tasks
     */
    public List<Task> getTasks() {
        return this.taskList;
    }

    /**
     * Process a file and loads the records into a List of Tasks
     *
     * @return A List of Tasks
     */
    private List<Task> loadFile() {
        FileInputStream fi = null;
        ObjectInputStream oi = null;
        File file = null;
        try {
            file = new File(this.filepath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            fi = new FileInputStream(file);
            oi = new ObjectInputStream(fi);
            while (true) {
                Task t = (Task) oi.readObject();
                taskList.add(t);
            }
        } catch (EOFException e) {

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                if (oi != null) {
                    oi.close();
                }
                if (fi != null) {
                    fi.close();
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        return taskList;
    }

    public void save(List<Task> taskList) {
        saveFile(taskList);
    }

    /**
     * Saves TaskLists to a specified file
     */
    private void saveFile(List<Task> taskList) {
        FileOutputStream fi = null;
        ObjectOutputStream oi = null;
        File file = null;
        try {
            file = new File(this.filepath);
            if (file.exists()) {
                file.delete();
            }
            file.getParentFile().mkdirs();
            file.createNewFile();
            fi = new FileOutputStream(file);
            oi = new ObjectOutputStream(fi);
            for (Task t : taskList) {
                oi.writeObject(t);
            }
        } catch (EOFException e) {
            e = e;
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream: " + e.getMessage());
            try {
                if (oi != null) {
                    oi.close();
                }
                if (fi != null) {
                    fi.close();
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}
