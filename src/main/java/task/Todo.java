package task;
/**
 * Represents a todo task. A <code>Todo</code> object corresponds to a todo task e.g.,
 * <code>"project meeting"</code>
 */
public class Todo extends Task {
    public Todo(String description){
        super (description);
    }
    /**
     * Returns the text representation of the todo task.
     * @return The text representation of the todo task.
     */
    @Override
    public String toStringTasks(){
        return "T/" + getStatusIconInBin() + "/" + description + "\n";
    }
    /**
     * Returns the text representation of the todo task to be displayed to the user.
     * @return The text representation of the todo task.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
