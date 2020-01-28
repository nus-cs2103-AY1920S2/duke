import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a Deadline object which extends from Task. Requires a timing and description for the event to take place.
 */

public class Deadline extends Task {

    protected String type = "D";
    protected String time;
    protected LocalDate date;
    protected int index;

    public Deadline(String description, String time, int index) {
        super(description, index);
        time = time.trim();
        this.time = time;

        try {
            format_Date();
        } catch (Exception e) {
            System.out.println("PS! Timing is not recorded... Follow the format dd/MM/YYYY HHMM!");
        }
    }

    public LocalDate get_Date() {
        return date;
    }

    /**
     * Sets the pattern of the required input date and time.
     *
     */
    public void format_Date() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm").withLocale(Locale.ENGLISH);
        date = LocalDate.parse(time,formatter);
        //System.out.println(date);
    }


    @Override
    public String toString() {
        String temp = "[" + type + "]" + "[" + getStatusIcon() + "] " + description + " (by: " +  time + ")";
        return temp;
    }
}