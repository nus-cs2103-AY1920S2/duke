import java.time.format.DateTimeParseException;

/**
 * Todo handles todo task for Duke.
 */
public class ToDo extends Task {
    protected String by;

    /**
     * Constructs a ToDo object for task added with todo type.
     * @param description Description of todo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Formats the task details into text file for saving and loading.
     * @return Task of the event.
     */
    @Override
    public String format() {
        return "T " + super.getStatusInNumber() + " " + super.description;
    }

    /**
     * Returns the task details for Duke to print.
     * @return Task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}