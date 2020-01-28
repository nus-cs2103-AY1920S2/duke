package duke.stub;

import duke.util.Task;
import duke.util.TaskListInterface;
import duke.util.Todo;

import java.util.ArrayList;

/*
 * TaskListStub
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * <p>TaskListStub is a Stub for simulating the required
 * behavior of a TaskList class.</p>
 * @author Mario Lorenzo
 */

public class TaskListStub implements TaskListInterface {
    ArrayList<Task> tasks;

    /**
     * Constructs a TaskListStub instance.
     */

    public TaskListStub() {
        this.tasks = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            tasks.add(new Todo("This is a dummy task."));
        }
    }

    /**
     * Implements the size method of TaskListImplementation.
     * @return The size of the list.
     */
    @Override
    public int size() {
        return tasks.size();
    }
}
