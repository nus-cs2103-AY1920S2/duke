import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline object which extends from Task. Requires a timing and description for the event to take place.
 */
public class Deadline extends Task {

    protected String type = "D";
    protected String deadline;
    protected LocalDate date;
    protected int index;
    protected LocalDate currentDate = LocalDate.now();
    private boolean isDateSaved = true;
    private Ui ui;

    /**
     * Constructor for Deadline that takes in a description of the task, the time whereby the Deadline should
     * be completed by, as well as the index of the Deadline in the list.
     *
     * @param description the description of the task
     * @param deadline the deadline of the task
     * @param index the index of the task in the list
     */
    public Deadline(String description, String deadline, int index) {
        super(description, index);
        ui = new Ui();
        deadline = deadline.trim();
        this.deadline = deadline;
        try {
            format_Date();
        } catch (DateTimeParseException e) {
            isDateSaved = false;
        }
    }

    /**
     * Returns the status of whether the date has been saved within the deadline object.
     *
     * @return true if the date has been saved, false if it has not.
     */
    public boolean getDateSavedStatus() {
        return this.isDateSaved;
    }

    public LocalDate get_Date() {
        return date;
    }

    /**
     * Sets the pattern of the required input date and time.
     */
    public void format_Date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm").withLocale(Locale.ENGLISH);
        date = LocalDate.parse(deadline,formatter);
    }

    /**
     * Returns the full details of the deadline, including start date and the date of the deadline.
     *
     * @return full details of the deadline in a string format.
     */
    public String details() {
        return this.get_Index() + ". " + "[" + type + "]" + "[" + getStatusIcon() + "] " + description
                + "\n(started on: " + currentDate.toString() + ") \n(by: " +  deadline + ")";
    }


    @Override
    public String toString() {
        String temp =  get_Index() + ". " + "[" + type + "]" + "[" + getStatusIcon() + "] "
                + description + " (by: " +  deadline + ")";
        return temp;
    }
}