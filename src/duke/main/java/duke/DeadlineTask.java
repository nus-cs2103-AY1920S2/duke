package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    private LocalDate due;
    private final TaskType taskType = TaskType.TODO;

    private DeadlineTask(String taskName, LocalDate due) {
        super(taskName, false);
        this.due = due;
    }

    public static DeadlineTask createDeadlineTask(String taskName, LocalDate due) {
        return new DeadlineTask(taskName, due);
    }

    @Override
    public String toString() {
        return super.toString().concat(String.format(" by %s", due.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))));
    }

    @Override
    public String getTaskIcon() {
        return "D";
    }
}
