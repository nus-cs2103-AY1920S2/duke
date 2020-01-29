import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate dateAt;

    Event(String taskName, LocalDate dateAt) {
        super(taskName);
        this.dateAt = dateAt;
    }

    @Override
    public String toString() {
            return "[E]" + super.toString() + "(at:" + dateAt.toString() + ")";
    }
}
