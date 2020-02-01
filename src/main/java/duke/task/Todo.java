package duke.task;


/** Entity class representing a task of type Todo */
public class Todo extends Task {

    /** as good practice every class should have it's own private serialVersionUID */
    private static final long serialVersionUID = -1559261707306825517L;

    public Todo(String description) {
        super(description.strip());
    }

    /** toString implementation */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}