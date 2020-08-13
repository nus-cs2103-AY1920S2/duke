package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDate date;
    protected String time;
    protected String rawDate;

    /**
     * Constructs new Event object.
     * @param taskName name of event.
     * @param dateTime date and time when event occurs.
     */
    public Event(String taskName, String dateTime) {
        super(taskName);
        String[] dt = dateTime.split(" ");
        this.rawDate = dt[0];
        this.date = LocalDate.parse(getRawDate());
        this.time = dt[1];
    }

    public String getDate() {
        return date.format(DateTimeFormatter.ofPattern("d MMM yyyy"));
    }

    public String getTime() {
        return this.time;
    }

    public String getDateTime() {
        return getDate() + " @ " + getTime();
    }

    public String getRawDate() {
        return rawDate;
    }

    public String getType() {
        return "[E]";
    }

    public String writeFormat() {
        return getType() + getStatus() + " " + getTaskName() + " /at " + getRawDate() + " " + getTime();
    }

    @Override
    public boolean equals(Object task) {
        if (task instanceof Event) {
            if (((Event)task).getTaskName().equals(this.getTaskName())) {
                return (((Event) task).getDateTime().equals(this.getDateTime()));
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + getDateTime() + ")";
    }
}
