import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task with a deadline when it is due.
 * A deadline task has a description, a deadline date, and a true or false on whether it is completed.
 */
public class DeadlineTask extends Task {
    private Date deadline;

    /**
     * Creates a DeadlineTask object.
     *
     * @param description The String description of the deadline task.
     * @param isDone      The boolean variable on whether the deadline task is completed.
     * @param deadline    The due Date of the deadline task.
     */
    private DeadlineTask(String description, boolean isDone, Date deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Static method to create a DeadlineTask object.
     * Throws an exception if the input text is not in the required format.
     *
     * @param description The String description of the deadline task.
     * @param text        The String of the date and time of the deadline.
     * @return A DeadlineTask.
     * @throws ParseException if parsing of String into Date fails
     */
    static DeadlineTask createDeadlineTask(String description, String text) throws ParseException {
        assert description != null;
        assert text != null;

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        String[] arr = text.split(" ");
        String hour = arr[1].substring(0, 2);
        String minute = arr[1].substring(2);
        String[] date = arr[0].split("/");

        if (date.length != 3) {
            throw new InvalidTaskDateTimeException("   The date and/or time format is invalid.\n"
                    + "   Please format your input as: DD/MM/YYYY HHmm.");
        }

        String day = date[0];
        String month = date[1];
        String year = date[2];
        Date deadline = format.parse(day + "/" + month + "/" + year + " " + hour + ":" + minute);
        return new DeadlineTask(description, false, deadline);
    }

    /**
     * Converts a DeadlineTask's deadline to a String.
     *
     * @return A String of the DeadlineTask's deadline.
     */
    private String deadlineToString() {
        SimpleDateFormat format = new SimpleDateFormat("E HHmm, MMM d, YYYY");
        String string = format.format(deadline);
        String[] arr = string.split(",");

        int day = deadline.getDate();
        if (day == 1) {
            arr[1] += "st";
        } else if (day == 2) {
            arr[1] += "nd";
        } else if (day == 3) {
            arr[1] += "rd";
        } else {
            arr[1] += "th";
        }

        return arr[0] + "," + arr[1] + "," + arr[2];
    }

    /**
     * Sets this DeadlineTask's complete status to true.
     * Overrides the abstract method in Task.
     *
     * @return A new complete DeadlineTask with the same description, deadline as the DeadlineTask instance.
     */
    @Override
    DeadlineTask completeTask() {
        return new DeadlineTask(this.description, true, this.deadline);
    }

    @Override
    public String toString() {
        return "[D] [" + super.getStatusIcon() + "] " + this.description
                + " (by: " + this.deadlineToString() + ")";
    }
}