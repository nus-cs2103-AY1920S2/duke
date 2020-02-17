package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represent a task which is a user's duke.task.Event task given by user.
 *
 * @author Kenny Ho
 */
public class Event extends Task {

    protected final String eventLogo = "E";
    private LocalDate time;
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructor for duke.task.Event class.
     *
     * @param description Description of the event.
     * @param at          LocalDate object of when event is happening.
     */
    public Event(String description, LocalDate at) {
        super(description);
        this.time = at;
    }

    public Event() {

    }

    /**
     * Return a String object of when the event is happening.
     *
     * @return Date of when the event is happening in the format of MMM-dd-YYYY.
     */
    public String getTime() {
        return time.format(outputFormat);
    }

    /**
     * Returns a string representation of the object. In general, the
     * {@code toString} method returns a string that
     * "textually represents" this object. The result should
     * be a concise but informative representation that is easy for a
     * person to read.
     * It is recommended that all subclasses override this method.
     * <p>
     * The {@code toString} method for class {@code Object}
     * returns a string consisting of the name of the class of which the
     * object is an instance, the at-sign character `{@code @}', and
     * the unsigned hexadecimal representation of the hash code of the
     * object. In other words, this method returns a string equal to the
     * value of:
     * <blockquote>
     * <pre>
     * getClass().getName() + '@' + Integer.toHexString(hashCode())
     * </pre></blockquote>
     *
     * @return a string representation of the object.
     */
    @Override
    public String toString() {
        String formattedDate = time.format(outputFormat);
        return String.format("[%s]%s\n       (by: %s)", eventLogo, super.toString(), formattedDate);
    }

    public String getHelpFormat() {
        return "Please type event task in this following format:\n" +
                "event <task_name> /by yyyy-mm-dd\n" +
                "event key word must be in lower case, spacing must be adhered and date format must include dashes!";
    }
}
