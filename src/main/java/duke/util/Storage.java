package duke.util;

import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeInvalidTaskFormatException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Storage
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 23 Jan 2020
 *
 */

/**
 * Storage class abstracts the I/O method
 * of reading and writing tasks from a file.
 * @author Mario Lorenzo
 */

public class Storage implements IStorage<Task> {
    private TaskReader reader;
    private TaskWriter writer;

    /**
     * Constructs a Storage instance.
     * @param filePath The file path where the file is located.
     */

    public Storage(String filePath) {
        assert (new File(filePath)).exists() : "The storage file does not exist.";
        this.reader = new TaskReader(filePath);
        this.writer = new TaskWriter(filePath);
    }

    /**
     * Loads the tasks from the file.
     * @return The ArrayList of all the tasks loaded from the file.
     * @throws DukeInvalidDateFormatException If there is a not properly formatted date.
     * @throws DukeInvalidTaskFormatException If there is a task there is not properly formatted.
     */

    public ArrayList<Task> load() throws DukeInvalidDateFormatException, DukeInvalidTaskFormatException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            tasks = reader.load();
        } catch (IOException e) {
            System.err.println(e);
        } catch (DukeInvalidTaskFormatException | DukeInvalidDateFormatException e) {
            throw e;
        }
        return tasks;
    }

    /**
     * Writes the task to the file.
     * @param task The task that wants to be added to the file.
     * @param isApppendMode Whether the file wants to be appended or resetted to blank.
     */

    public void write(Task task, boolean isApppendMode) {
        try {
            this.writer.write(task, isApppendMode);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Rewrites the list of tasks to the file.
     * @param tasks The ArrayList of the tasks.
     */

    public void rewriteToFile(ArrayList<Task> tasks) {
        this.writer.setBlank();
        try {
            for (int i = 0; i < tasks.size(); i++) {
                this.writer.write(tasks.get(i), i != 0);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
