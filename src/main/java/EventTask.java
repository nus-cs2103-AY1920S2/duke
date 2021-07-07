import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents an event.
 * An event has a description, a date, and a true or false on whether it is completed.
 */
public class EventTask extends Task {
    private Date deadline;

    /**
     * Creates a EventTask object.
     *
     * @param description The String description of the event.
     * @param isDone      The boolean variable on whether the event is completed.
     * @param deadline    The due Date of the event.
     */
    private EventTask(String description, boolean isDone, Date deadline) {
        super(description, isDone);
        this.deadline = deadline;
    }

    /**
     * Static method to create an EventTask object.
     * Throws an exception if the input text is not in the required format.
     *
     * @param description The String description of the event.
     * @param text        The String of the date and time of the event.
     * @return An Event.
     * @throws ParseException if parsing of String into Date fails
     */
    static EventTask createEventTask(String description, String text) throws
            ParseException, ArrayIndexOutOfBoundsException {
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
        return new EventTask(description.trim(), false, deadline);
    }

    /**
     * Converts an EventTask's date to a String.
     *
     * @return A String of the EventTask's deadline.
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
     * Sets this EventTask's complete status to true.
     * Overrides the abstract method in Task.
     *
     * @return A new complete EventTask with the same description, deadline as the EventTask instance.
     */
    @Override
    EventTask completeTask() {
        return new EventTask(this.description, true, this.deadline);
    }

    @Override
    public String toString() {
        return "[E] [" + super.getStatusIcon() + "] " + this.description
                + " (at: " + this.deadlineToString() + ")";
    }
}