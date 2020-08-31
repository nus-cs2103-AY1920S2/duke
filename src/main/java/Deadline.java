import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.StringTokenizer;

/**
 * This class encompasses Deadlines, a specific subtype of Task objects.
 * They comprise a task name accompanied by a deadline,
 * before which the task must be completed.
 */
public class Deadline extends Task {
    protected String input;
    protected String deadline;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Creates a Deadline representing the task the user
     * wishes to store. If the deadline is entered in a
     * recognisable format, then the deadline is stored
     * as a date and time rather than simply a String.
     *
     * @param name Name of task to be completed.
     * @param input Deadline of task.
     */
    public Deadline(String name, String input) {
        super(name);

        this.input = input;
        StringTokenizer st = new StringTokenizer(input);
        String dateString = st.nextToken();
        try {
            date = LocalDate.parse(dateString);
        } catch (DateTimeParseException e) {
            try {
                date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("d/M/yyyy"));
            } catch (DateTimeParseException x) {
                // do nothing
            }
        }

        if (st.hasMoreTokens()) {
            String timeString = st.nextToken();
            try {
                time = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HHmm"));
            } catch (DateTimeParseException e) {
                // do nothing
            }
        }

        if (date != null) {
            deadline = date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            if (time != null) {
                deadline = deadline + " " + time.format(DateTimeFormatter.ofPattern("K.mma"));
            }
        } else {
            deadline = input;
        }
    }

    /**
     * Formats the Deadline task for display to the user.
     *
     * @return The output string to be displayed.
     */
    public String getTaskDisplay() {
        assert deadline != null : "Missing deadline";
        return " [D][" + getStatusIcon() + "] " + name + " (by: " + deadline + ")";
    }

    /**
     * Formats the Deadline task as it is to be stored/saved
     * on the hard disk.
     *
     * @return The string the task is to be saved as.
     */
    public String getSaveDisplay() {
        assert deadline != null : "Missing deadline";
        return "D|" + (isDone ? "1" : "0") + "|" + name + "|" + deadline + "\n";
    }

    public String getType() {
        return "D";
    }
    public String getInput() {
        return input;
    }
}