import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EventTask extends Task {
    private LocalDate due;
    private final TaskType taskType = TaskType.TODO;

    private EventTask(String taskName, LocalDate due) {
        super(taskName, false);
        this.due = due;
    }

    public static EventTask createEventTask(String taskName, LocalDate due) {
        return new EventTask(taskName, due);
    }

    @Override
    public String toString() {
        return super.toString().concat(String.format(" at %s", due.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"))));
    }

    @Override
    public String getTaskIcon() {
        return "E";
    }
}
