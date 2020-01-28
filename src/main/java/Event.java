import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private String eventDate;
    private LocalDate parsedDate;

    /**
     * Event constructor.
     *
     * @param eventArgs Joined string of the user input command arguements
     * @throws DukeException Incorrect format/missing date
     */
    public Event(String eventArgs) throws DukeException {
        super(eventArgs.split(" /at ")[0]);
        try {
            this.eventDate = eventArgs.split(" /at ")[1];
            this.parsedDate = LocalDate.parse(eventDate);
        } catch (DateTimeParseException e) {
            throw new DukeException(5);
        } catch (Exception e) {
            throw new DukeException(2);
        }
    }

    /**
     * A method to return the details of the current Task object.
     * Contains the task done status and the task name.
     *
     * @return A String representation of the task details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
            + this.parsedDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }

    @Override
    public String toFileString() {
        return "E" + super.toFileString() + " | " + eventDate;
    }
}