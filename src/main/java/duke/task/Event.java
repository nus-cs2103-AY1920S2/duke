package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event program is a type of Task with specified start and end date. It has a string representation
 * and is able to return the type of task it is.
 *
 * @version 1.0
 * @since 28/1/2020
 */
public class Event extends Task {

    /**
     * Constructor.
     *
     * @param dateTime        refers to the date and time of the task.
     * @param taskDescription refers to the contents of the task.
     */
    public Event(LocalDateTime[] dateTime, String taskDescription) {

        super(dateTime, taskDescription);

    }

    /**
     * Gets the type of the task.
     *
     * @return type of task.
     */
    @Override
    public Types getType() {

        return Types.E;

    }

    /**
     * Constructs a string representation of task.
     *
     * @return string representation of task.
     */
    @Override
    public String toString() {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String dateStart = super.getDateTime()[0].format(format);
        String dateEnd = super.getDateTime()[1].format(format);

        return "[" + Types.E + "]"
                + "[" + super.getStatus() + "]"
                + " " + super.getTaskDescription()
                + "(at: " + dateStart + " to " + dateEnd + ")";
    }
}
