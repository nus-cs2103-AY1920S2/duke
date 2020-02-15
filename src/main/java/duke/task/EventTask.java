package duke.task;

import duke.storage.CSV;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    protected static final String TYPE_STR = "E";
    private static final DateTimeFormatter defaultDateF = DateTimeFormatter.ofPattern("yyyy-mm-dd");
    LocalDate ld;
    String prepos;
    String time;

    /**
     * Main constructor used when creating this type of task
     *
     * @param name   = task name
     * @param prepos = preposition to describe the time (eg. by , on...)
     * @param time   = string inputted by user that describe the time
     */
    public EventTask(String name, String prepos, String time) {
        this(name, prepos, time, false);
    }

    private EventTask(String name, String prepos, String time, boolean done) {
        super(name, TaskType.EVENT_TASK);
        this.prepos = prepos;
        this.time = time;
        this.isDone = done;
        try {
            this.ld = LocalDate.parse(time, defaultDateF);
        } catch (DateTimeParseException dtpe) {
            this.ld = null;
        }
    }

    /**
     * Convert all the relevant data to CSV in order to save to local file
     */
    @Override
    public CSV toCSV() {
        return new CSV(new CSV(EventTask.TYPE_STR), new CSV(this.prepos), new CSV(this.time),
                new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

    /**
     * Load task from local file
     *
     * @param csv = csv file parsed from local file
     * @return previously saved task
     */
    public static EventTask parseFromCSV(CSV csv) {
        return new EventTask(csv.getStr(4), csv.getStr(1), csv.getStr(2), Boolean.parseBoolean(csv.getStr(3)));
    }

    private String timeRemStr() {
        try {
            return this.prepos + ": " + (this.ld == null ? this.time
                    : ld.format(DateTimeFormatter.ofPattern("E, d-M/L-y")));
        } catch (DateTimeParseException dtpe) {
            return this.prepos + ": " + this.time;
        }
    }

    /**
     * String that describe the task when listing all the tasks
     */
    @Override
    public String toString() {
        return sqB(EventTask.TYPE_STR) + sqB(gou()) + " " + getName() + " (" + timeRemStr() + ")";
    }
}