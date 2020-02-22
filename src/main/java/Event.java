import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.time.format.DateTimeParseException;

/**
 * Represents an Event object which extends from Task. Requires a timing and description for the event to take place.
 */
public class Event extends Task {

    protected String type = "E";
    protected String time;
    private boolean isDateSaved = true;

    /**
     * Constructor for Event that takes in a description of the task, the time of the Event,
     * as well as the index of the Event in the list.
     *
     * @param description the description of the task
     * @param time the timing of the task
     * @param index the index of the task in the list
     */
    public Event(String description, String time, int index) {
        super(description, index);
        time = time.trim();
        this.time = time;

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


    /**
     * Sets the pattern of the required input date and time.
     *
     */
    public void format_Date() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm").withLocale(Locale.ENGLISH);
        LocalDate date = LocalDate.parse(time,formatter);
    }

    @Override
    public String toString() {
        String temp =  get_Index() + ". " + "[" + type + "]" + "[" + getStatusIcon() + "] "
                + description + " (at: " +  time + ")";
        return temp;
    }


}