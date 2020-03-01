import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Class Todo to represent a Todo Task. A Todo Task
 * consists of a description and a time.
 */
public class Todo extends Task {
    /**
     * Creates a new Todo Task with {@code description}.
     *
     * @param description the description/details of our task
     */

    protected static LocalDate DEFAULT_DATE = LocalDate.parse("2099-12-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    public Todo(String description) {
        super(description);
    } // time attribute is not needed in this class

    @Override
    public String getTypeName() {
        return "T";
    }

    @Override
    public String getTimeOutput() {
        return "";
    }

    @Override
    public String getTimeToDatabase() {
        return "";
    }

    @Override
    public LocalDate getDate() {
        return DEFAULT_DATE;
    }

    /**
     * returns a String representation of a Todo instance.
     *
     * @return String String representation
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /**
     * returns a String representation of a Todo instance in our database.
     *
     * @return String a Todo representation in our database
     */
    @Override
    public String toStringFile() {
        return "T" + " | " + super.toStringFile();
    }
}
