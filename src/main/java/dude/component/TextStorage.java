package dude.component;

import dude.task.Task;
import dude.task.Todo;
import dude.task.Deadline;
import dude.task.Event;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Hard-coded plaintext file-based implementation of IStorage interface.
 * Session TaskList data is saved to data/dude.txt relative to where the CLI is run from.
 * Requires the app to have file and directory reading and writing permissions.
 */
public class TextStorage implements IStorage {
    private static final File storeFile = new File("data/dude.txt");

    /**
     * Loads the TaskList from the previous Dude session from memory.
     * If data from the previous session cannot be found, returns an empty TaskList.
     *
     * @param ui User Interface which Dude chatbot uses to report errors when loading data.
     * @return TaskList with all tasks from previous session if successful, else shows an error message
     *         and returns TaskList with tasks that were successfully parsed from data/dude.txt.
     */
    @Override
    public TaskList restoreSession(IUserInterface ui) {
        TaskList session = new TaskList();
        try (Scanner sc = new Scanner(storeFile)) {
            while (sc.hasNext()) {
                String[] entry = sc.nextLine().split("\\|");
                parseEntry(ui, session, entry);
            }
        } catch (FileNotFoundException e) {
            ui.respond("I didn't find any previous session, starting empty");
        }
        return session;
    }

    /**
     * Saves the TaskList from the current session into some form of persistent memory.
     * If an error occurs in writing the data, reports the error and warns users that data may be lost.
     *
     * @param ui User Interface which Dude chatbot uses to report errors when saving data.
     * @param session TaskList containing Tasks to save to data/dude.txt.
     */
    @Override
    public void saveSession(IUserInterface ui, TaskList session) {
        // Create "/data/" directory if it doesn't exist
        if (!storeFile.getParentFile().exists()) {
            try {
                boolean isMkdirSuccessful = storeFile.getParentFile().mkdir();
                if (!isMkdirSuccessful) {
                    ui.respond("Warning: Could not create /data/ directory to save tasks",
                            "I won't be able to save your tasks");
                    return;
                }
            } catch (SecurityException e) {
                ui.respond("Warning: A security violation occurred when trying to save your tasks",
                        "I won't be able to save your tasks");
                return;
            }
        }

        try (FileWriter fw = new FileWriter(storeFile)) {
            for (Task task : session.getAllTasks()) {
                fw.write(task.storeFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            ui.respond("Warning: An error occurred when saving your tasks. "
                    + "Some of your data may have been lost");
        }
    }

    private void parseEntry(IUserInterface ui, TaskList session, String[] entry) {
        try {
            boolean isDone = entry[1].equals("O");
            switch (entry[0]) {
            case "T":
                session.addTask(new Todo(entry[2], isDone));
                break;
            case "D":
                LocalDate by = LocalDate.parse(entry[3]);
                session.addTask(new Deadline(entry[2], by, isDone));
                break;
            case "E":
                LocalDate from = LocalDate.parse(entry[3]);
                LocalDate to = LocalDate.parse(entry[4]);
                session.addTask(new Event(entry[2], from, to, isDone));
                break;
            default:
                throw new ParsingException();
            }
        } catch (DateTimeParseException
                | ArrayIndexOutOfBoundsException
                | ParsingException e) {
            ui.respond("Warning: An error occurred when reading your tasks. "
                    + "Some of your data may have been lost");
        }
    }
}
