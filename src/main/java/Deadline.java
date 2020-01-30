import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task which is a user's Deadline task given by user.
 *
 * @author Kenny Ho
 */
public class Deadline extends Task{

    protected final String deadlineLogo = "D";
    private LocalDate by;
    private DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructor of DeadLine class.
     *
     * @param description Description describing the Deadline task.
     * @param by LocalDate object of when the deadline is.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    /**
     * Return a String object of the task deadline.
     *
     * @return Date of task deadline in the format of MMM-dd-YYYY.
     */
    public String getBy() {
        return by.format(outputFormat);
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
        String formattedDate = by.format((outputFormat));
        return String.format("[%s]%s (by: %s)", deadlineLogo, super.toString(), formattedDate);
    }
}
