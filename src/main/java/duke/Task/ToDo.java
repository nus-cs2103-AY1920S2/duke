package duke.Task;

import java.time.LocalDateTime;

public class ToDo extends Task {


    public ToDo(LocalDateTime dateTime, String taskDescription) {

        super(dateTime, taskDescription);

    }

    @Override
    public String toString() {

        return "[" + Types.ToDo + "]"
                + "[" + super.getStatus() + "]"
                + " " + super.getTaskDescription();
    }

    @Override
    public Types getType() {

        return Types.ToDo;

    }
}
