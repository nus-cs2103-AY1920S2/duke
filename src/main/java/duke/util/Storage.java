package duke.util;

import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeInvalidTaskFormatException;

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

public class Storage {
    private TaskReader reader;
    private TaskWriter writer;

    /**
     * Constructs a Storage instance.
     * @param filePath The file path where the file is located.
     */

    public Storage(String filePath) {
        this.reader = new TaskReader(filePath);
        this.writer = new TaskWriter(filePath);
    }

    /**
     * Loads the tasks from the file.
     * @return The ArrayList of all the tasks loaded from the file.
     * @throws DukeInvalidDateFormatException If there is a not properly formatted date.
     * @throws DukeInvalidTaskFormatException If there is a task there is not properly formatted.
     */

    public ArrayList<Task> loadTasks() throws DukeInvalidDateFormatException, DukeInvalidTaskFormatException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            tasks = reader.loadTasks();
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

    public void writeTask(Task task, boolean isApppendMode) {
        try {
            this.writer.writeTask(task, isApppendMode);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Rewrites the list of tasks to the file.
     */

    public void rewriteTasksToFile(ArrayList<Task> tasks) {
        this.writer.setBlank();
        try {
            for (int i = 0; i < tasks.size(); i++) {
                this.writer.writeTask(tasks.get(i), i != 0);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
