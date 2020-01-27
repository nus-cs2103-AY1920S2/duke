import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Event extends Task {

    static final String TYPE_SYMBOL = "E";
    private LocalDate date;

    Event(String taskDescription, LocalDate date) {
        super(taskDescription);
        this.date = date;
    }

    @Override
    public String toStringForSaving() {
        return TYPE_SYMBOL + Task.SEPERATOR
                + (isDone ? TRUE_SYMBOL : FALSE_SYMBOL) + Task.SEPERATOR
                + taskDescription + Task.SEPERATOR
                + date;
    }

    @Override
    public String toString() {
        return String.format("[%s]%s (at: %s)",
                TYPE_SYMBOL,
                super.toString(),
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }
}
