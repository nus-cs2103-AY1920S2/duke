package duke.task;

import java.time.LocalDate;

/**
 * Represents a Todo task that extends the Task class. A Todo object is represented by a String description and boolean
 * isDone.
 */
public class Todo extends Task {
    private LocalDate date;

    /**
     * Creates a Todo Task.
     * @param description Description of task
     * @param isDone Boolean of whether it is done
     */
    public Todo(String description, boolean isDone, LocalDate date) {
        super(description, isDone, date);
        this.date = LocalDate.MAX;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}