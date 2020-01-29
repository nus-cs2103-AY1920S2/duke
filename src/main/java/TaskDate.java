import java.time.LocalDate;

public abstract class TaskDate extends Task{
    public TaskDate(String description) {
        super(description);
    }
    abstract LocalDate getDate();
}
