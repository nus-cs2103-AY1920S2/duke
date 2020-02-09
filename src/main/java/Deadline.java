import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Represents a task to be completed.
 * A <code>Deadline</code> object contains description of a task e.g., <code>Assignment 1</code>, the deadline date
 * e.g., <code>2020-02-01</code> and the deadline time e.g., <code>1000</code> of the task.
 */
public class Deadline extends Task {
    protected String deadlineDate;
    protected String deadlineTime;
    protected String deadline;

    /**
     * A Deadline object has a description of the Deadline task as well as the date and time at which the task is due.
     *
     * @param command description of the Deadline task
     * @param deadlineDate date when the Deadline task is due
     * @param deadlineTime time when the Deadline task is due
     */
    public Deadline(String command, String deadlineDate, String deadlineTime) {
        super(command);
        this.deadlineDate = deadlineDate;
        this.deadlineTime = deadlineTime;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("HHmm");
            Date deadlineTimeParsed = sdf.parse(deadlineTime);
            SimpleDateFormat sdftoFormat = new SimpleDateFormat("hhmm aa");
            this.deadline = LocalDate.parse(deadlineDate).format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " "
                    + sdftoFormat.format(deadlineTimeParsed);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Returns the deadline date and time of a Deadline task.
     *
     * @return deadline date and the deadline time.
     */
    public String getDeadlineDateAndTime() {
        return deadlineDate + " " + deadlineTime;
    }

    /**
     * Returns whether a task is marked done in int form instead of symbol form, to be displayed in the file
     * saved in hard disk.
     *
     * @return 1 if task is marked done, or 0 if the task is marked undone.
     */
    public int getDoneInt() {
        return isDone() ? 1 : 0;
    }

    /**
     * Returns information about a task, stating that it is D(Deadline) task, whether it is marked done, the
     * description of the task and the deadline date and time, for the updating of the file saved in hard disk.
     *
     * @return information about the task.
     */
    public String updateFile() {
        return "D - " + getDoneInt() + " - " + getCommand() + " - " + getDeadlineDateAndTime();
    }

    @Override
    public String toString() {
        return "[D][" + getDoneSymbol() + "] " + getCommand() + "(by: " + deadline + ")";
    }
}
