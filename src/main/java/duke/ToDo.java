package duke;

/**
 * A todo object, has a name and a date of the event.
 */
public class ToDo extends Task {

    /**
     * Constructor for a todo task, which is not done.
     * @param name The name of event task
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Constructor for a todo task, in which the done status can be specified.
     * @param name The name of event task
     * @param isDone The done status of event task
     */
    public ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * String to be displayed for a todo task.
     * @return The string of todo task to be displayed to user.
     */
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * String to be returned when written and saved to drive for a todo task.
     * @return The string of todo task to be written to the file and saved.
     */
    public String writeDrive() {
        return "T|" + (super.isDone()? "1|" : "0|") + this.name;
    }

    /**
     * A new todo object with done property being set.
     * @return A new todo object.
     */
    public ToDo setDone() {
        return new ToDo(this.name, true);
    }
}
