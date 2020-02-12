package duke;

/**
 * The ToDo Class extends the Task Class to store objects with a description but no time and date attached.
 * @author qiujingying
 * @version 1.0
 */
public class ToDo extends Task {

    private TaskType type = TaskType.TODO;
    protected String time = "";

    /**
     * Creates a ToDo object with a description and empty time.
     * @param description details of the ToDo
     * @param time empty string
     */
    public ToDo(String description, String time) {
        super(description, time);
    }

    /**
     * Returns the type of the Task.
     * @return TaskType.TODO
     */
    public TaskType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
