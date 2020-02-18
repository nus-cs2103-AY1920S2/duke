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
     * Returns information about a task, stating that it is D(Deadline) task, whether it is marked done, the
     * description of the task and the deadline date and time, for the updating of the file saved in hard disk.
     *
     * @return information about the task.
     */
    public String updateFile() {
        return "D - " + getDoneInt() + " - " + getCommand() + " - " + getDeadlineDateAndTime();
    }

    /**
     * Updates the date and time details for the Deadline task.
     *
     * @param newDateAndTime date and time to be updated.
     */
    public void updateDetails(String newDateAndTime) throws DukeException {
        String[] strArr = newDateAndTime.split(" ", 2);
        if (strArr.length == 1) {
            throw new DukeException("UPDATE_DEADLINE_DATE_TIME_NEEDED");
        }
        String newDeadlineDate = strArr[0];
        String newDeadlineTime = strArr[1];
        this.deadlineDate = newDeadlineDate;
        this.deadlineTime = newDeadlineTime;
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

    @Override
    public String toString() {
        return "[D][" + getDoneSymbol() + "] " + getCommand() + "(by: " + deadline + ")";
    }
}
