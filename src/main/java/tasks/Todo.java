package tasks;

import tasks.Tag;

import java.util.Set;

/**
 * Task without any date/time attached to it.
 */
public class Todo extends Task {

    /**
     * Creates a task without any date/time attached to it.
     *
     * @param name task to be completed.
     */

    public Todo(Name name, Set<Tag> tags) {
        super(name, tags);
    }

    /**
     * Returns the string of the task containing the name.
     *
     * @return the string of the task containing the name.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}