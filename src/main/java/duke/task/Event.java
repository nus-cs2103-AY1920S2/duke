package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Event program is a type of Task with specified start and end date. It has a string representation
 * and is able to return the type of task it is.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class Event extends Task {

    /**
     * Constructor.
     * @param dateTime refers to the date and time of the task.
     * @param taskDescription refers to the contents of the task.
     */
    public Event(LocalDateTime[] dateTime, String taskDescription) {

        super(dateTime, taskDescription);

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

        return "[" + Types.Event + "]"
                + "[" + super.getStatus() + "]"
                + " " + super.getTaskDescription()
                + "(at:" + dateStart + " to " + dateEnd + ")";
    }

    /**
     * Gets the type of the task.
     *
     * @return type of task.
     */
    @Override
    public Types getType() {

        return Types.Event;

    }
}
