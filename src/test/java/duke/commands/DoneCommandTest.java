package duke.commands;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import duke.commands.Command;
import duke.commands.DoneCommand;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.tasks.Event;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class DoneCommandTest {
    private static TaskList tasks = new TaskList();
    private static Ui ui = new Ui();
    private static Storage storage = new Storage("data/duke.txt");

    @BeforeAll
    public static void initAll() throws DukeException {
        tasks.addTask(new ToDo("Todo 1"));
        tasks.addTask(new Deadline("Deadline 1 /by 2020-02-03"));
        tasks.addTask(new Event("Event 1 /at 2020-05-01"));
    }

    @Test
    public void testDoneOutOfBounds() {
        Command testDone = new DoneCommand(10);
        Exception e = assertThrows(DukeException.class,
            () -> testDone.execute(tasks, ui, storage));
        assertEquals("Boss, you do know there's not that many tasks right?", e.getMessage());
    }

    @Test
    public void testDoneAssertion() {
        Command testDone = new DoneCommand(1);
        assertDoesNotThrow(() -> testDone.execute(tasks, ui, storage));
        assertEquals(true, tasks.getTaskAt(1).getTaskDone());
    }
}