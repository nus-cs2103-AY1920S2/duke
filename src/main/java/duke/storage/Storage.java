package duke.storage;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the file used to store existing task list data.
 */
public class Storage{
    private final static DateTimeFormatter IN_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private String filePath;

    /**
     * @param filePath filepath of the local file to store data
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load the tasks data from the file if exists.
     *
     * @return ArrayList of the task read from file
     * @throws DukeException exception for error while reading
     */
    public ArrayList<Task> load() throws DukeException {
        File file = new File(Paths.get(filePath).toUri());
        if (file.exists()) {
            return readFile(file);
        } else {
            throw new DukeException("Reading Error");
        }
    }

    /**
     * Save single line of data at the end of the data file.
     *
     * @param task task to save to the file
     * @throws DukeException exception for error while writing data to the file
     */
    public void saveSingle(Task task) throws DukeException {
        saveCheck();
        try {
            FileWriter fw = new FileWriter(Paths.get(filePath).toString(), true);
            fw.write(task.saveString() + System.lineSeparator());
            fw.close();
        } catch (IOException ex) {
            throw new DukeException("Save single error.");
        }

    }

    /**
     * Save list of tasks to the data file.
     *
     * @param tasks list of task to save to the file
     * @throws DukeException exception for error while writing data to the file
     */
    public void saveAll(TaskList tasks) throws DukeException {
        saveCheck();
        try {
            FileWriter fw = new FileWriter(Paths.get(filePath).toString());
            for (Task task : tasks.getList()) {
                fw.write(task.saveString() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException ex) {
            throw new DukeException("Save all error.");
        }
    }

    /**
     * Check if the file and directory exist before saving. Create the file and directory if do not exist.
     *
     * @throws DukeException exception for error while creating the file or directory
     */
    private void saveCheck() throws DukeException {
        File file = new File(Paths.get(filePath).toUri());
        boolean success;
        if (!file.exists()) {
            File dir = new File(file.getParentFile().getAbsolutePath());
            if (!dir.exists()) {
                success = dir.mkdir();
                if (!success) {
                    throw new DukeException("Create file directory error");
                }
            }
            try {
                success = file.createNewFile();
                if (success) {
                    throw new DukeException("Create file error");
                }
            } catch (IOException ex) {
                throw new DukeException("Create file error");
            }
        }
    }

    /**
     * Read the data from the file, return all of the corresponding objects by a list.
     *
     * @param file File object of the target file
     * @return list of tasks based on the data
     * @throws DukeException exception for error while accessing the file
     */
    private ArrayList<Task> readFile(File file) throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String temp[] = s.nextLine().split(" \\| ");
                switch (temp[0]) {

                case "T":
                    Task todo = new Todo(temp[2]);
                    checkMarkDone(todo, temp[1]);
                    tasks.add(todo);
                    break;

                case "E":
                    Task event = new Event(temp[2], LocalDateTime.parse(temp[3], IN_FORMATTER));
                    checkMarkDone(event, temp[1]);
                    tasks.add(event);
                    break;

                case "D":
                    Task deadline = new Deadline(temp[2], LocalDateTime.parse(temp[3], IN_FORMATTER));
                    checkMarkDone(deadline, temp[1]);
                    tasks.add(deadline);
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            throw new DukeException("Unable find the file!");
        }
        return tasks;
    }

    /**
     * Check and mark the task done if the status is true.
     *
     * @param task task to check and mark
     * @param status status of the given task
     */
    private void checkMarkDone(Task task, String status) {
        if (status.equals("1")) {
            task.markDone();
        }
    }
}
