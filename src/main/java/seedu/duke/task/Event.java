package seedu.duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    public static final String TYPE_SYMBOL = "E";
    private LocalDate date;

    public Event(String taskDescription, LocalDate date) {
        super(taskDescription);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
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

    @Override
    public int compareTo(Task task) {
        if (task instanceof Todo) {
            return -1;
        } else if (task instanceof Deadline) {
            return this.getDate().compareTo(((Deadline) task).getDate());
        } else { // event
            return this.getDate().compareTo(((Event) task).getDate());
        }
    }
}
