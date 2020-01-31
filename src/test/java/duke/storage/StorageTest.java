import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class StorageTest {
    private static Storage storage = new Storage("data/duke.txt");
    private static Ui ui = new Ui();
    private static TaskList tasks = new TaskList();

    /**
     * Initialise the test environment with seed data.
     *
     * @throws DukeException DukeException thrown when adding tasks to TaskList fails.
     */
    @BeforeAll
    public static void initAll() throws DukeException {
        // Load duke.txt with correct seed data
        tasks.addTask(new ToDo("Todo 1"));
        tasks.addTask(new Deadline("Deadline 1 /by 2020-02-03"));
        tasks.addTask(new Event("Event 1 /at 2020-05-01"));
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
        // tasks.getTaskList().forEach(item -> System.out.println(item.toString()));
        // storage.load().forEach(item -> System.out.println(item.toString()));
        // assertEquals(tasks.getTaskList(), storage.load());
    }
}