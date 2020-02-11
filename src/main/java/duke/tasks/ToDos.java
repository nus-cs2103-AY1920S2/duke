/**
 * ToDos is a type of task that can be stored in the task list
 */


package duke.tasks;

public class ToDos extends Task {

    public ToDos(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}