package duke.stub;

import duke.util.Task;
import duke.util.Todo;

import java.util.ArrayList;

public class TaskListStub {
    ArrayList<Task> tasks;
    public TaskListStub() {
        for (int i = 0; i < 5; i++) {
            tasks.add(new Todo("This is a dummy task."));
        }
    }


}
