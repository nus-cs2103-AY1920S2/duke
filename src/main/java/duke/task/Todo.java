package duke.task;

public class Todo extends Task {
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Returns us a String representation of the Todo
     * useful for printing our task details.
     */
    @Override
    public String toString(){
        return "[T]" + super.toString();
    }
}
