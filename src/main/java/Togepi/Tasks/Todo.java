package Togepi.Tasks;

/** Represents a task of type todo.
 */
public class Todo extends Task {

    /** Creates a task of type todo with the specified description.
     * @param description represents the description of the task.
     */
    public Todo(String description) {
        super(description);
    }

    /** Produces string to be printed.
     * @return a string that represents the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /** Prints the string representation of the task that is to be saved in the file.
     * @return a string representation of the
     *     task to be saved in the file.
     */
    @Override
    public String saveToFile() {
        return "[T]" + super.saveToFile();
    }
}
