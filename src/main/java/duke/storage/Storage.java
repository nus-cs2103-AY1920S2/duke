package duke.storage;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * Allows Duke to persist data across sessions.
 */
public interface Storage {
    /**
     * Saves data in the current session.
     * Overwrites existing save file.
     * 
     * @param tasks List of Tasks.
     */
    void save(List<Task> tasks) throws IOException, DukeException;

    /**
     * Loads data from the existing save file (if any).
     *
     * @return List of Tasks from previous session.
     */
    List<Task> load() throws FileNotFoundException, DukeException;
}