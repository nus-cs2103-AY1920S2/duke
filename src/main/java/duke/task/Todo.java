/**
 *  This is a Todo class. Child class of Task.
 */

package duke.task;

public class Todo extends Task {
    /**
     * Constructor for Todo class.
     * @param description Content of the Task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
