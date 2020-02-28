package task;

/**
 * Represents a task.
 */
public class Task {

    public String name;
    public boolean isDone;

    /**
     * Constructor for Task.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructor for Task.
     *
     * @param name the name of the task
     * @param isDone the boolean value representing the status of the task
     */
    public Task(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    /**
     * Marks the isDone status of the object as true.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Returns a summary of the task.
     *
     * @return Returns a summary of the task, including description and the status icon
     */
    public String toString() {
        String mark;
        if (this.isDone) {
            //mark = "[" + "\u2713" + "]";
            mark = "[" + "✅" + "]";
        } else {
            //mark = "[" + "\u2717" + "]";
            mark = "[" + "❌" + "]";
        }
        return mark + " " + this.name;
    }

    /**
     * Returns a summary of the task in the file format.
     *
     * @return Returns a summary of the task in the file format
     */
    public String toStringFileFormat() {
        int num;
        if (isDone) {
            num = 1;
        } else {
            num = 0;
        }
        return " | " + num + " | " + this.name;
    }
}
