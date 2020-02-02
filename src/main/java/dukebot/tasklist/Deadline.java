package dukebot.tasklist;

import java.time.LocalDateTime;

public class Deadline extends Task {

    /**
     * Generates a new Deadline.
     *
     * @param description  The name of the Deadline.
     * @param dateTime  Due date of Deadline
     */
    public Deadline(String description, LocalDateTime dateTime) {
        super(description, TaskType.DEADLINE, dateTime);
    }

    @Override
    public String toString() {
        return (this.description + " (by: " + this.dateTimeToString() + ")");
    }
}
