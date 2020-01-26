package duke.storage;

import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;

import duke.exceptions.DukeException;
import duke.tasks.Task;

public interface Storage {
    void save(List<Task> tasks) throws IOException, DukeException;

    List<Task> load() throws FileNotFoundException, DukeException;
}