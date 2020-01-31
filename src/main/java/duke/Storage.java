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

/** deals with loading tasks from the file and saving tasks in the file */
class Storage {
    Path dir = null;
    
    public Storage (Path filePath) {
        dir = filePath;
    }

    @SuppressWarnings("unchecked")
    public boolean loadFromFile(TaskList tasks) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream fileIn = null;
        ObjectInputStream objIn = null;
        ArrayList<Task> savedTasks = null;
        boolean loadSuccessful = false;

        if (java.nio.file.Files.exists(dir)) {
            try {
                fileIn = new FileInputStream(new File(dir.toString())); // read serialized object from file as a String
                objIn = new ObjectInputStream(fileIn);
                savedTasks = (ArrayList<Task>) objIn.readObject(); // supressed uncheck cast warning: no way to verify type of generic at runtime
            } finally {
                if(fileIn != null) {
                    fileIn.close();
                }
                if(objIn != null) {
                    objIn.close();
                }
                if (tasks != null && tasks.load(savedTasks)) {
                    loadSuccessful = true;
                    System.out.println("    Tasklist loaded!");
                }
            }
        }
        return loadSuccessful;
    }

    public boolean saveToFile(TaskList tasks) throws IOException {
        FileOutputStream  fileOut = new FileOutputStream(new File(dir.toString())); // read serialized object from file as a String
        ObjectOutputStream  objOut = new ObjectOutputStream(fileOut);
        objOut.writeObject(tasks.save());
        fileOut.close();
        objOut.close();
        return true;
    }
}