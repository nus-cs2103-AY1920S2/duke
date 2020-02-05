/**
 * Represents a Task object which description is a String representing the task to do.
 */
class ToDo extends Task {
    public ToDo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        String status = super.getStatusIcon();
        return "[T]" + "[" + status + "] " + super.getDescription();
    }
}
