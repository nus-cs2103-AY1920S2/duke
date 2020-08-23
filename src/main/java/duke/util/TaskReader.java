package duke.util;

import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeInvalidTaskFormatException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * TaskReader
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 27 Jan 2020
 *
 */

/**
 * <p>TaskReader class abstracts the input method
 * from a file, parses the tasks, and loads them
 * to a Duke instance.</p>
 * @author Mario Lorenzo
 */

public class TaskReader implements IReader<Task> {
    private String filename;
    private static final HashMap<String, Keyword> KEYWORD = new HashMap<>() {
        {
            put("D", Keyword.DEADLINE);
            put("E", Keyword.EVENT);
            put("T", Keyword.TODO);
        }
    };

    /**
     * Constructs a TaskReader instance that is used
     * for reading the list of tasks from a file.
     * @param filename The filename where the list of tasks is located.
     */

    public TaskReader(String filename) {
        this.filename = filename;
    }

    /**
     * Loads the list of tasks from the file.
     * @return The ArrayList containing the tasks loaded from the file.
     * @throws IOException From the InputStreamReader
     * @throws DukeInvalidTaskFormatException If there is an error in the format.
     */

    public ArrayList<Task> load() throws IOException, DukeInvalidTaskFormatException,
            DukeInvalidDateFormatException {
        FileInputStream fis = new FileInputStream(new File(filename));
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        String line;
        ArrayList<Task> tasks = new ArrayList<>();
        int counter = 1;
        while ((line = reader.readLine()) != null) {
            tasks.add(parseTask(line, counter));
        }
        reader.close();

        return tasks;
    }

    /**
     * Parses a line describing a single task from a file.
     * @param line The line describing the detail of a task.
     * @param counter The index of the line
     * @return The corresponding task of the line
     * @throws DukeInvalidTaskFormatException If the task is not properly formatted.
     */

    private Task parseTask(String line, int counter) throws DukeInvalidTaskFormatException,
            DukeInvalidDateFormatException {
        String[] lineSplitted = line.split(" \\| ");
        try {
            Keyword keyword = getKeyword(lineSplitted[0], line, counter);
            boolean isDone = getStatus(lineSplitted[1], line, counter);
            Task task;
            String description;

            switch (keyword) {
            case TODO:
                description = lineSplitted[2];
                task = new Todo(description);
                break;

            case DEADLINE:
                description = lineSplitted[2];
                String dueDate = lineSplitted[3];
                task = new Deadline(description, dueDate);
                break;

            default:
                description = lineSplitted[2];
                String eventDate = lineSplitted[3];
                task = new Event(description, eventDate);
                break;
            }

            if (isDone) {
                task.markAsDone();
            }
            return task;

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidTaskFormatException("OOPS! It seems there is a lack of argument in the task.",
                    line, counter);
        }
    }

    /**
     * Gets the corresponding type of task in form of Command enum from the string.
     * @param commandString The command in form of string.
     * @param line The line describing the detail of the task.
     * @param counter The index of the line.
     * @return The corresponding Command enum representing the type of string.
     * @throws DukeInvalidTaskFormatException If the command cannot be found.
     */

    private Keyword getKeyword(String commandString, String line, int counter) throws
            DukeInvalidTaskFormatException {
        Keyword keyword = TaskReader.KEYWORD.get(commandString);

        if (keyword == null) {
            throw new DukeInvalidTaskFormatException("OOPS! We cannot load "
                    + "the task since the command is unknown.", line, counter);
        }
        return keyword;
    }

    /**
     * Gets the corresponding boolean of whether the task is done or not.
     * @param statusString The status of the task.
     * @param line The line describing the detail of the task.
     * @param counter The index of the line.
     * @return The boolean of whether the task is done or not.
     * @throws DukeInvalidTaskFormatException If the status is not properly formatted.
     */

    private boolean getStatus(String statusString, String line, int counter) throws DukeInvalidTaskFormatException {
        try {
            int status = Integer.parseInt(statusString);
            if (status != 0 && status != 1) {
                throw new DukeInvalidTaskFormatException("OOPS! We cannot load "
                        + "the task since the status of the task is invalid.", line, counter);
            } else {
                return status == 1;
            }
        } catch (NumberFormatException e) {
            throw new DukeInvalidTaskFormatException("OOPS! We cannot load "
                    + "the task since the status of the task is invalid.", line, counter);
        }
    }
}
