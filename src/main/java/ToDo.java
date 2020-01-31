/**
 * This class extends from Task and is created when users input starts with "todo".
 */
public class ToDo extends Task {
    /**
     * Constructor for a ToDo object takes in a String with the name of the task.
     * @param taskName
     */
    ToDo(String taskName) {
        super(taskName);
    }

    /**
     * The toString method returns [T] which indicates it is a ToDo Task and the name of the task.
     * @return The String containing the details of the ToDo Task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
