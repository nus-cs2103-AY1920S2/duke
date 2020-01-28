package duke.task;

import java.time.LocalDateTime;

/**
 * The ToDo program is a task with task description only. It has a string representation
 * and is able to return the type of task it is.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class ToDo extends Task {

    /**
     * Constructor.
     * @param dateTime refers to the date and time of the task.
     * @param taskDescription refers to the contents of the task.
     */
    public ToDo(LocalDateTime dateTime, String taskDescription) {

        super(dateTime, taskDescription);

    }

    /**
     * Constructs a string representation of task.
     *
     * @return string representation of task.
     */
    @Override
    public String toString() {

        return "[" + Types.ToDo + "]"
                + "[" + super.getStatus() + "]"
                + " " + super.getTaskDescription();
    }

    /**
     * Gets the type of the task.
     *
     * @return type of task.
     */
    @Override
    public Types getType() {

        return Types.ToDo;

    }
}
