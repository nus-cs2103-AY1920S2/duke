package duke.command;

import duke.task.TaskList;
import duke.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DoneCommandTest {

    private TaskList tasks = new TaskList();

    @Test
    void execute() {
        tasks.addTask(new Todo("Homework", false));
        tasks.getTasks().get(0).markAsDone();
        assertEquals("[Y]", tasks.getTasks().get(0).getStatusIcon());
    }
}