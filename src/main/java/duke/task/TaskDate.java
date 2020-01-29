package duke.task;

import java.time.LocalDate;

public abstract class TaskDate extends Task{
    public TaskDate(String description, boolean isDone) {
        super(description, isDone);
    }
    abstract LocalDate getDate();
}
