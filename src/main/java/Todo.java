/**
 * Represents a Todo object which extends from Task. Requires a description of the task.
 */

public class Todo extends Task {

    public Todo(String description, int index) {
        super(description, index);
        type = "T";
    }
}