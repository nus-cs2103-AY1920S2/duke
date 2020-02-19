package duke;

import duke.DukeException;
import duke.Task;
import duke.TaskList;
import duke.ToDo;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {
    private TaskList taskList;
    private ArrayList<Task> dummy;

    @Test
    public void dukeTaskTest() {
        dummy = new ArrayList<>();
        dummy.add(new Task("read book", ""));

        try {
            taskList = new TaskList(dummy);
        } catch (DukeException d) {
            System.out.println(d);
        }
        assertEquals("[✘] read book", taskList.retrieveTask(0).toString());
    }

    @Test
    public void dukeTodoTest() {
        ToDo test = new ToDo("go out", "");
        assertEquals("[T][✘] go out", test.toString());
    }
}
