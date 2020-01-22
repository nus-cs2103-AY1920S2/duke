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
     * Parses the.
     *
     * @param taskString the task string
     * @return the todo
     */
    public static Todo parse(String taskString) {
        String[] parts = taskString.split("\\|");
        boolean d = parts[1].trim().equals("1");
        String desc = parts[2].trim();
        return new Todo(desc);
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Prints the.
     *
     * @return the string
     */
    @Override
    public String print() {
        return getType() + " | " + (isDone ? String.valueOf(1) : String.valueOf(0)) + " | " + getDescription();
    }
}
