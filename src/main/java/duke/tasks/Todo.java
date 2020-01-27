package duke.tasks;

import duke.enums.TType;

import java.time.LocalDate;

public class Todo extends Task {

    public TType getType() {
        return TType.TODO;
    }

    public LocalDate getDate() {
        return null;
    }

    public Todo(String task) {
        super(task);
    }

    public Todo(boolean isDone, String task) {
        super(isDone, task);
    }

    @Override
    public String toString() {
        if (isDone) {
            return  "[T][✓] " + task;
        } else {
            return "[T][✗] " + task;
        }
    }
}
