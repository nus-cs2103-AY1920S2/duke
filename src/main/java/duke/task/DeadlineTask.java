package duke.task;
import duke.storage.CSV;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task {
    protected static final String TYPE_STR = "D";
    private static final DateTimeFormatter defaultDateF = DateTimeFormatter.ofPattern("d'/M/L'/y");
    LocalDate ld;
    String prepos;
    String dateLine;

    public DeadlineTask(String name, String prepos, String time) {
        this(name, prepos, time, false);
    }

    private String timeRemStr() {
        try {
            return this.prepos + ":" + (this.ld == null ? this.dateLine
                    : ld.format(DateTimeFormatter.ofPattern("E, d-M/L-y")));
        } catch (DateTimeParseException dtpe) {
            return this.prepos + ":" + this.dateLine;
        }
    }

    private DeadlineTask(String name, String prepos, String time, boolean done) {
        super(name, TaskType.DEADLINE_TASK);
        this.prepos = prepos;
        this.dateLine = time;
        this.done = done;
        try {
            this.ld = LocalDate.parse(time, defaultDateF);
        } catch (DateTimeParseException dtpe) {
            this.ld = null;
        }
    }

    @Override
    public CSV toCSV() {
        return new CSV(new CSV(DeadlineTask.TYPE_STR), new CSV(this.prepos), new CSV(this.dateLine),
                new CSV(Boolean.toString(isDone())), new CSV(getName()));
    }

    public static DeadlineTask parseFromCSV(CSV csv) {
        return new DeadlineTask(csv.getStr(4), csv.getStr(1), csv.getStr(2), Boolean.parseBoolean(csv.getStr(3)));
    }

    @Override
    public String toString() {
        return sqB(DeadlineTask.TYPE_STR) + sqB(gou()) + " " + getName() + " (" + timeRemStr() + ")";
    }
}