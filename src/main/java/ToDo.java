public class ToDo extends Task {
    public ToDo(String todoName) {
        super(todoName);
    }

    /**
     * A method to return the details of the current Task object.
     * Contains the task done status and the task name.
     *
     * @return A String representation of the task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}