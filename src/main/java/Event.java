import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {

    private LocalDate date;

    Event(String taskDescription, LocalDate date) {
        super(taskDescription);
        this.date = date;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
