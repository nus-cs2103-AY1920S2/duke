import java.time.LocalDate;
import java.util.Comparator;

/**
 * Represents a task to be done. It stores the description of the task and whether it has been
 * done or not.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns a symbol representing whether the task has been completed
     *
     * @return a tick (if done) or a cross (if not done)
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Returns a string representation of the task
     *
     * @return a string representation of the task in the format "[X] Description"
     */
    @Override
    public String toString() {
        return "["
                + getStatusIcon()
                + "] "
                + description;
    }

    /**
     * Returns a string representation of the in the database format
     *
     * @return a string in the form "| 1 | description"
     */
    public String convert() {
        int done = isDone ? 1 : 0;
        return " | "
                + done
                + " | "
                + description;
    }

    public String getDescription() {
        return this.description;
    }
}

class NameComp implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        return t1.description.compareTo(t2.getDescription());
    }

    public boolean equals(Object obj) {
        return this == obj;
    }
}

class DateComp implements Comparator<Task> {
    public int compare(Task t1, Task t2) {
        if (t1 instanceof ToDo && t2 instanceof ToDo) {
            return t1.description.compareTo(t2.getDescription());
        } else if (t1 instanceof ToDo) {
            return 1;
        } else if (t2 instanceof ToDo) {
            return -1;
        } else {
            LocalDate d1, d2;

            if (t1 instanceof Event) {
                d1 = ((Event) t1).at;
            } else {
                d1 = ((Deadline) t1).by;
            }

            if (t2 instanceof Event) {
                d2 = ((Event) t2).at;
            } else {
                d2 = ((Deadline) t2).by;
            }

            return d1.compareTo(d2);
        }
    }

    public boolean equals(Object obj) {
        return this == obj;
    }
}