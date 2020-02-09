package common;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import parser.FileParser;
import exception.DukeException;
import task.Task;

/**
 * Represents a storage object.
 */
public class Storage {

    private String filePath;

    /**
     * Constructor of the class Storage.
     *
     * @param filePath the file path of the storage file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads from the storage file and generates an array list of task objects.
     *
     * @return an array list of tasks based on the file content
     */
    public ArrayList<Task> readFromFile() {
        try {
            String thisLine;
            ArrayList<Task> builtList = new ArrayList<>();
            File f = new File(this.filePath);
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                thisLine = fileScanner.nextLine();
                builtList.add(new FileParser(thisLine).lineToTask());
            }
            return builtList;
        } catch (IOException exp) {
            return new ArrayList<>();
        }
    }

    /**
     * Writes to the file based on the content of the array list of tasks.
     *
     * @param existedList the task list
     * @throws DukeException when the storage file cannot be accessed
     */
    public void writeToFile(ArrayList<Task> existedList) throws DukeException {
        try {
            FileWriter fw = new FileWriter(this.filePath);
            for (Task task : existedList) {
                fw.write(task.toStringFileFormat() + "\n");
            }
            fw.close();
        } catch (IOException exp) {
            throw new DukeException("IOException detected");
        }
    }
}
