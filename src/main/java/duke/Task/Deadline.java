package duke.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @version 1.0
 * @since 2020-01-28
 */
public class Deadline extends Task {

    public Deadline(LocalDateTime dateTime, String taskDescription) {

        super(dateTime, taskDescription);

    }

    /**
     * Constructs a string representation of task
     * @return string representation of task
     */
    @Override
    public String toString() {

        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime dateTime = super.getDateTime();
        String date = super.getDateTime().format(format);

        return "[" + Types.Deadline + "]"
                + "[" + super.getStatus() + "]"
                + " " + super.getTaskDescription()
                + "(by:" + date + ")";
    }

    /**
     * Gets the type of the task
     * @return type of task
     */
    @Override
    public Types getType() {

        return Types.Deadline;

    }
}
