package duke.stub;

import duke.util.Task;
import duke.util.TaskListInterface;
import duke.util.Todo;

import java.util.ArrayList;

public class TaskListStub implements TaskListInterface {
    ArrayList<Task> tasks;
    public TaskListStub() {
        this.tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tasks.add(new Todo("This is a dummy task."));
        }
    }

    @Override
    public int size() {
        return tasks.size();
    }
}
