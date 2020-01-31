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

    public EventTask(String name, String prepos, String time) {
        this(name, prepos, time, false);
    }

    private EventTask(String name, String prepos, String time, boolean done) {
        super(name, TaskType.EVENT_TASK);
        this.prepos = prepos;
        this.time = time;
        this.done = done;
        try {
            this.ld = LocalDate.parse(time, defaultDateF);
        } catch (DateTimeParseException dtpe) {
            this.ld = null;
        }
    }

    @Override
    public CSV toCSV() {
        return new CSV(new CSV(EventTask.TYPE_STR), new CSV(this.prepos), new CSV(this.time),
                new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

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

    @Override
    public String toString() {
        return sqB(EventTask.TYPE_STR) + sqB(gou()) + " " + getName() + " (" + timeRemStr() + ")";
    }
}