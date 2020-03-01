import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class Deadline to represent a Deadline Task. A Deadline
 * consists of a description and a endTime.
 */
public class Deadline extends Task implements Comparable {
    protected LocalDate endTime; // this will be startTime or endTime depending on the underlying class.

    /**
     * Creates a new Deadline with {@code description} and specified {@code endTime}.
     *
     * @param description the description/details of our task
     * @param endTime end time
     */
    public Deadline(String description, String endTime) {
        super(description);
        this.endTime = LocalDate.parse(endTime, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     * Retrieves our class type to differentiate Deadlines among the generic Task instances.
     *
     * @return "D" the initial for our Deadline Class
     */
    @Override
    public String getTypeName() {
        return "D";
    }

    @Override
    public String getTimeOutput() {
        return endTime.format(DateTimeFormatter.ofPattern("d MMM YYYY"));
    }

    @Override
    public String getTimeToDatabase() {
        return endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    @Override
    public LocalDate getDate() {
        return endTime;
    }

    /**
     * returns a String representation of a Deadline instance.
     *
     * @return String String representation
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " " + endTime.format(DateTimeFormatter.ofPattern("d MMM YYYY"));
    }

    /**
     * returns a String representation of a Deadline instance in our database.
     *
     * @return String a Deadline representation in our database
     */
    @Override
    public String toStringFile() {
        return "D" + " | " + super.toStringFile();
    }

    /**
     * Compares the two Deadlines for order.
     * Nearer deadlines are considered smaller
     */
    @Override
    public int compareTo(Object o) {
        assert (o instanceof Deadline); // can only compare between deadlines
        return this.getDate().compareTo(((Deadline) o).getDate());
    }
}
