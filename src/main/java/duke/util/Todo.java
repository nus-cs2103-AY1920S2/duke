package duke.util;

/*
 * Todo
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * The Todo class extends the Task class and it defines
 * the properties that a todo task has.
 * @author Mario Lorenzo
 */

public class Todo extends Task {

    /**
     * <p>Constructs a Todo instance, given the description
     * of the task.</p>
     * @param description The description of the task.
     */

    public Todo(String description) {
        super(description);
    }

    /**
     * toString method overrides the Object's toString method
     * and it contains the mark, as well as the description of
     * a task.
     * @return The String that represents the task's details.
     */

    @Override
    public String toString() {
        return String.format("[T] [%s] %s", getStatusIcon(), this.description);
    }
}
