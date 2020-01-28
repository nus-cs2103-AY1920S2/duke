package duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @version 1.0
 * @since 2020-01-28
 */
public class Event extends Task {

    public Event (LocalDateTime dateTime, String taskDescription) {

        super(dateTime, taskDescription);

    }

    /**
     * Constructs a string representation of task
     * @return string representation of task
     */
    @Override
    public String toString() {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String date = super.getDateTime().format(format);

        return "[" + Types.Event + "]"
                + "[" + super.getStatus() + "]"
                + " " + super.getTaskDescription()
                + "(at:" + date + ")";
    }

    /**
     * Gets the type of the task
     * @return type of task
     */
    @Override
    public Types getType() {

        return Types.Event;

    }
}
