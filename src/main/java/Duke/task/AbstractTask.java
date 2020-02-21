package Duke.task;

import Duke.exception.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class AbstractTask implements Task {
    protected String taskName;
    protected boolean isDone;
    protected String preposition;
    protected LocalDate date;
    protected String time;

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

    public String taskStateString() {
        return (this.isDone) ? "[✓]" : "[✗]";

    }

    public String formattedDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    protected abstract String taskType();

    public boolean markDone() throws DukeException {
        if (this.isDone) {
            throw new DukeException("Good job! Task has already been done!");
        }
        this.isDone = true;
        return this.isDone;
    }

    public String getTaskType() {
        return this.getClass().getName();
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
