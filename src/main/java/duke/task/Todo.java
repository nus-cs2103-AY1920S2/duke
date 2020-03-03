/*
 * @author leslieharland
 */

package duke.task;

/**
 * The Class Todo.
 */
public class Todo extends Task {

    /**
     * Instantiates a new todo.
     *
     * @param description the description
     */
    public Todo(String description) {
        super(description);
        type = "T";
    }

    /**
     * Parses the todo.
     *
     * @param taskString the task string
     * @return the todo
     */
    public static Todo parse(String taskString) {
        String[] parts = taskString.split("\\|");
        boolean d = parts[1].trim().equals("1");
        String desc = parts[2].trim();
        Todo t = new Todo(desc);
        t.isDone = d;
        return t;
    }

    /**
     * Prints they task string e.g. T | 1 | Borrow book
     *
     * @return the task string
     */
    @Override
    public String print() {
        return getType() + " | " + (isDone ? String.valueOf(1) : String.valueOf(0)) + " | " + getDescription();
    }
}
