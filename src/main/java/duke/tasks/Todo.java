/**
 * Todo task with a description of task to be done
 */
package duke.tasks;

public class Todo extends Task {

    /**
     * Creates a Todo task
     * @param description Information of task to be done
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of task to be done
     * @return A string representation of task to be done
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
