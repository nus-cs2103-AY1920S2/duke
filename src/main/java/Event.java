import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task object with a set location that the event is occurring at, denoted by the attribute String place.
 */
class Event extends Task {
    private LocalDate time;

    public Event(String task) {
        // Split the input task string by the delimiter /by
        // first element in arr will be the description of the task, second elem will be the deadline, both are String
        // Pass the description as the argument to the constructor
        super(task.split("/at")[0]);
        String[] arr = task.split("/at");
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
            this.time = LocalDate.parse(arr[1].trim(), formatter);
        } catch (DateTimeParseException e) {
            throw e;
        }

    }

    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public String toString() {
        String description = super.getDescription();
        String status = super.getStatusIcon();
        String time = this.getTime();

        return "[E]" + "[" + status + "] " + description + "at: " + time;
    }
}
