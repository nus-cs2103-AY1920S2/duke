package duke.Task;

import java.time.LocalDateTime;

/**
 * @version 1.0
 * @since 2020-01-28
 */
public class ToDo extends Task {

    public ToDo(LocalDateTime dateTime, String taskDescription) {

        super(dateTime, taskDescription);

    }

    /**
     * Constructs a string representation of task
     * @return string representation of task
     */
    @Override
    public String toString() {

        return "[" + Types.ToDo + "]"
                + "[" + super.getStatus() + "]"
                + " " + super.getTaskDescription();
    }

    /**
     * Gets the type of the task
     * @return type of task
     */
    @Override
    public Types getType() {

        return Types.ToDo;

    }
}
