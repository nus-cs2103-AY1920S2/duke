package Duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class AbstractTask implements Task {
    String taskName;
    boolean isDone;
    String preposition;
    LocalDate date;
    String time;

    /**
     * Constructor for AbstractTask class with additional info provided after date taken to be time.
     * @param taskName Name or description of the event
     * @param preposition English word string to indicate the date clearly (eg. at, by, before, between)
     * @param date Date of event
     * @param time Time of event (OPTIONAL)
     */
    public AbstractTask(String taskName, String preposition, LocalDate date, String... time) {
        this.taskName = taskName;
        this.preposition = preposition;
        this.date = date;
        this.time = (time.length == 0) ? null : time[0];
        this.isDone = false;
    }

    /**
     * Constructor for AbstractTask class with additional date field.
     * @param taskName Name or description of the event
     * @param preposition English word string to indicate the date clearly (eg. at, by, before, between)
     * @param date Date of event
     */
//    public AbstractTask(String taskName, String preposition, LocalDate date) {
//        this.taskName = taskName;
//        this.preposition = preposition;
//        this.date = date;
//        this.time = null;
//        this.isDone = false;
//    }

    /**
     * Constructor for AbstractTask class.
     * @param taskName Name or description of the event
     */
    public AbstractTask(String taskName) {
        this.taskName = taskName;
        this.preposition = null;
        this.date = null;
        this.time = null;
        this.isDone = false;
    }

    protected String taskStateString() {
        return (this.isDone) ? "[✓]" : "[✗]";

    }

    protected String formattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    protected abstract String taskType();

    public boolean markDone() {
        this.isDone = true;
        return this.isDone;
    }

    @Override
    public String toString() {
        return (this.date == null) ? taskType() + taskStateString() + " " + this.taskName
                : (this.time == null) ? taskType() + taskStateString() + " " + this.taskName + " " + this.preposition
                + " " + formattedDate()
                : taskType() + taskStateString() + " " + this.taskName + " " + preposition + " " + formattedDate()
                + " " + this.time;
    }

}
