package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Deadline program is a type of task with specified deadline. It has a string representation
 * and is able to return the type of task it is.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class Deadline extends Task {

    /**
     * Constructor.
     * @param dateTime refers to the date and time of the task.
     * @param taskDescription refers to the contents of the task.
     */
    public Deadline(LocalDateTime dateTime, String taskDescription) {

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
        LocalDateTime dateTime = super.getDateTime();
        String date = super.getDateTime().format(format);

        return "[" + Types.Deadline + "]"
                + "[" + super.getStatus() + "]"
                + " " + super.getTaskDescription()
                + "(by:" + date + ")";
    }

    /**
     * Gets the type of the task.
     *
     * @return type of task.
     */
    @Override
    public Types getType() {

        return Types.Deadline;

    }
}
