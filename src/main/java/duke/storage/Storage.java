package duke.storage;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

import duke.exceptions.DukeException;
import duke.tasks.Task;

/**
 * Allows <code>Duke</code> to persist data across sessions.
 */
public interface Storage {
    /**
     * Saves data in the current session.
     *
     * @param tasks List of Tasks.
     */
    void save(List<Task> tasks) throws IOException, DukeException;

<<<<<<< HEAD
    /**
     * Loads data from the previous session (if any).
     *
     * @return List of Tasks from previous session.
     */
=======
>>>>>>> branch-A-CodingStandard
    List<Task> load() throws FileNotFoundException, DukeException;
}