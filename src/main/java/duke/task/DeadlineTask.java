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

    /**
     * Main constructor used when creating this type of task
     *
     * @param name   = task name
     * @param prepos = preposition to describe the time (eg. by , on...)
     * @param time   = string inputted by user that describe the time
     */
    public DeadlineTask(String name, String prepos, String time) {
        this(name, prepos, time, false, false);
    }

    private String timeRemStr() {
        try {
            return this.prepos + ":" + (this.ld == null ? this.dateLine
                    : ld.format(DateTimeFormatter.ofPattern("E, d-M/L-y")));
        } catch (DateTimeParseException dtpe) {
            return this.prepos + ":" + this.dateLine;
        }
    }

    private DeadlineTask(String name, String prepos, String time, boolean done, boolean isScrapped) {
        super(name, done, TaskType.DEADLINE_TASK, isScrapped);
        this.prepos = prepos;
        this.dateLine = time;
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
        return new CSV(new CSV(DeadlineTask.TYPE_STR), new CSV(this.prepos), new CSV(this.dateLine),
                new CSV(Boolean.toString(isDone())), new CSV(Boolean.toString(isScrapped())), new CSV(getName()));
    }

    /**
     * Load task from local file
     *
     * @param csv = csv file parsed from local file
     * @return previously saved task
     */
    public static DeadlineTask parseFromCSV(CSV csv) {
        return new DeadlineTask(csv.getStr(5), csv.getStr(1), csv.getStr(2), Boolean.parseBoolean(csv.getStr(3)), Boolean.parseBoolean(csv.getStr(4)));
    }

    /**
     * String that describe the task when listing all the tasks
     */
    @Override
    public String toString() {
        return sqB(DeadlineTask.TYPE_STR) + sqB(gou()) + " " + getName() + " (" + timeRemStr() + ")";
    }

    public DeadlineTask getCopy() {
        return new DeadlineTask(this.name, this.prepos, this.dateLine, this.isDone, this.isScrapped);
    }
}