package core;

import dukexception.DukeException;
import dukexception.StorageException;
import task.Task;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {
    private File file;
    private DataParser dataParser;

    /**
     * Constructor to initialize the path of the external storage.
     */
    public Storage(String filepath) throws StorageException {
        try {
            file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            throw new StorageException("Error in locate/creating the file.");
        }
        dataParser = new DataParser();
    }

    /**
     * Saves the states of the system to external file.
     *
     * @param tasks is the states of the system.
     * @throws DukeException when writing to file is unsuccessful.
     */
    public void save(ArrayList<Task> tasks) throws DukeException {
        writeTo(file, tasks);
    }

    /**
     * Loads the states from the external file to the system.
     *
     * @return the states of the system.
     * @throws DukeException when reading from the file is unsuccessful.
     */
    public ArrayList<Task> load() throws DukeException {
        return readFrom(file);
    }

    /**
     * Clears all the data in the external file.
     *
     * @throws DukeException when reading from the file is unsuccessful.
     */
    public void clearData() throws DukeException {
        try (FileWriter fw = new FileWriter(file)) {
            BufferedWriter bw = new BufferedWriter(fw);
            bw.close();
        } catch (IOException ex) {
            throw new DukeException("Encounter error in resetting data");
        }
    }

    /**
     * Obtains the task lsit from the external file.
     *
     * @param thisFile indicates the file to be read from.
     * @return the task list.
     * @throws DukeException when reading from the file is unsuccessful.
     */
    private ArrayList<Task> readFrom(File thisFile) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try (FileReader fr = new FileReader(thisFile)) {
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                Task task = dataParser.parseToTask(line);
                tasks.add(task);
            }
            br.close();
        } catch (FileNotFoundException e) {
            throw new StorageException("The stated file is not found.");
        } catch (EOFException e) {
            //empty file
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            throw new StorageException("Encountered error in reading from the file.");
        }
        return tasks;
    }

    /**
     * Saves the readable tasks to the external file.
     *
     * @param thisFile indicates the file to be written to.
     * @param tasks    the tasks to be written to the file.
     * @throws DukeException when the writing to the file is unsuccessful.
     */
    private void writeTo(File thisFile, ArrayList<Task> tasks) throws DukeException {
        try (FileWriter fw = new FileWriter(thisFile)) {
            BufferedWriter bw = new BufferedWriter(fw);
            for (Task task : tasks) {
                String formattedTask = dataParser.parseToString(task);
                bw.write(formattedTask + "\n");
            }
            bw.close();
        } catch (IOException ex) {
            throw new DukeException("Encountered error in writing into the file");
        }
    }
}
