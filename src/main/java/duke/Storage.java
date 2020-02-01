package duke;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Path;
import java.util.ArrayList;

import duke.task.Task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

/** Class which handles the underlying implementation of I/O from and to files.
 *      1. Reading of tasks from a file and updating the current TaskList object;
 *      2. Saving tasks from the current TaskList to the file in the filepath provided.
 */
class Storage {
    Path dir = null;
    
    public Storage (final Path filePath) {
        dir = filePath;
    }

    /**
     * Attempts to load from the filepath provided in the constructor a saved Tasklist.
     * @param tasks the TaskList which is to be loaded with Tasks
     * @return true if the TaskList was successfully loaded from file and false otherwise
     * @throws FileNotFoundException if FileInputStream cannot find the file
     * @throws IOException if FileInputStream can find but cannot open the file
     * @throws ClassNotFoundException if ObjectInputStream cannot convert the Object to an ArrayList<Task>
     */
    @SuppressWarnings("unchecked")
    public boolean loadFromFile(final TaskList tasks) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fileIn = null;
        ObjectInputStream objIn = null;
        ArrayList<Task> savedTasks = null;
        boolean loadSuccessful = false;

        if (java.nio.file.Files.exists(dir)) {
            try {
                // Read serialized object from file as a String
                fileIn = new FileInputStream(new File(dir.toString())); 
                objIn = new ObjectInputStream(fileIn);
                // @SuppressWarnings unchecked cast warning: no way to verify type of generic at runtime
                savedTasks = (ArrayList<Task>) objIn.readObject(); 
            } finally {
                if(fileIn != null) {
                    fileIn.close();
                }
                if(objIn != null) {
                    objIn.close();
                }
                if (tasks != null && savedTasks != null && tasks.load(savedTasks)) {
                    loadSuccessful = true;
                }
            }
        }
        return loadSuccessful;
    }

    /**
     * Saves the Tasks from the current instantiated TaskList to a file
     * @param tasks the TaskList which contains the Tasks to be saved
     * @return true if the file was successfully saved
     * @throws IOException if the file could not be opened
     */
    public boolean saveToFile(final TaskList tasks) throws IOException {
        // read serialized object from file as a String
        final FileOutputStream  fileOut = new FileOutputStream(new File(dir.toString()));
        final ObjectOutputStream  objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(tasks.save());
        fileOut.close();
        objOut.close();
        return true;
    }
}