package duke;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {
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

}
