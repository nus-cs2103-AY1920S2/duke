/**
 * Represents a normal task with only a description.
 */
public class Todo extends Task {

    /**
     * Constructor that takes in the description of the task.
     *
     * @param description the description of the task.
     */
    public Todo(String description) {
        super(description);
        type = "Todo";
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
