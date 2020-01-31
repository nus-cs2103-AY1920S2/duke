/**
 * A ToDo task that extends from the Task class.
 */
public class ToDo extends Task {

    /**
     * ToDo class constructor.
     * @param description Description for the ToDo task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Method to print ToDo task.
     * @return A string that indicates it is a ToDo task, status icon and description of the ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + " " + super.toString();
    }
}