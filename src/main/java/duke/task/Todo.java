package duke.task;

import duke.task.Task;

/**
 * duke.task.Todo class which is a duke.task.Task but more specific
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}