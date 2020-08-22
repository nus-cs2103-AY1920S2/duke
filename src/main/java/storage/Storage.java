package storage;

import exceptions.DukeException;
import parser.Parser;
import tasks.Task;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage object is used as a storage for the list of Task objects by user in Duke.
 * Storage class provides implementation of methods to load, write to and overwrite
 * existing file on hard drive.
 */
public class Storage {
    /**
     * Storage objects have attributes of list of Task objects to store Task objects that
     * is loaded from file in hard drive and updated when user is using Duke and the file
     * path to file in hard drive.
     */
    protected static List<Task> tasks = new ArrayList<>();
    private String filePath;

    /**
     * Constructs a Storage object.
     * @param filePath path to file with saved list of tasks in hard drive.
     */
    public Storage(String filePath) {
        assert !filePath.isEmpty() : "File path should not be empty.";
        this.filePath = filePath;
    }

    /**
     * Overwrites current existing data in saved file.
     * @param newTaskList List of Task objects used to populate safe file.
     */
    public void overwrite(List<Task> newTaskList) {
        // Assigns new input list to object's list.
        tasks = newTaskList;
        // Overwrite current save file with new list.
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(
                    filePath, false)));
            for (Task task : tasks) {
                printWriter.println(task.writeToFile());
            }
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Appends newest Task object to save file's list of saved Task.
     * @param task Task object to be added to list.
     */
    public void write(Task task) {
        // Append new Task to save file.
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(
                    filePath, true)));
            printWriter.println(task.writeToFile());
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads list of tasks in save file into list of Task objects. Throws Duke exception.
     * @return List of Task objects.
     * @throws DukeException Thrown when any line in the safe file does not fit the required Task object requirements.
     */
    public List<Task> load() throws DukeException {
        List<Task> loadedTasks = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filePath));
            String readLine;
            Parser parser = new Parser();

            //Check if save file has reached EOF
            while ((readLine = bufferedReader.readLine()) != null) {
                Task task = parser.parseLine(readLine);
                loadedTasks.add(task);
            }
            bufferedReader.close();
        } catch (IOException ie) {
            new File("./data").mkdir();
            new File("./data/duke.txt");
        }
        return loadedTasks;
    }
}
