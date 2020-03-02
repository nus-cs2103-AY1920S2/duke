package duke.storage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tags.Tag;
import duke.tags.TagList;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class StorageTest {
    private static Storage storage = new Storage("data/duke.txt");
    private static Ui ui = new Ui();
    private static TaskList tasks = new TaskList();
    private static TagList tags;

    /**
     * Initialise the test environment with seed data.
     *
     * @throws DukeException DukeException thrown when adding tasks to TaskList fails.
     */
    @BeforeAll
    public static void initAll() throws DukeException {
        // Load duke.txt with correct seed data
        tasks.addTask(new ToDo("Todo 1", new ArrayList<Tag>()));
        tasks.addTask(new Deadline("Deadline 1", LocalDate.parse("2020-02-03"), new ArrayList<Tag>()));
        tasks.addTask(new Event("Event 1", LocalDate.parse("2020-05-01"), new ArrayList<Tag>()));
        tags = new TagList(tasks);
    }

    /**
     * Assertion test if loading the tasks from the storage is successfully, without exceptions.
     * Should not throw any exceptions.
     *
     * @throws DukeException DukeException thrown when loading the tasks from input file.
     */
    @Test
    public void testLoadFromFile() throws DukeException {
        assertDoesNotThrow(() -> storage.load());
    }
}