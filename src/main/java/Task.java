/**
 * This abstract class encompasses all the different Task objects
 * being managed by the Duke program's list.
 */
public abstract class Task {
    protected boolean isDone;
    protected String name;

    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    public abstract String getTaskDisplay();
    public abstract String getSaveDisplay();
    public abstract String getType();

    /**
     * Outputs a tick or cross symbol to indicate whether
     * or not a task has been completed.
     *
     * @return A tick if task is done, and a cross otherwise.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718");
    }

    /**
     * Allows a task to be marked as done.
     */
    public void markDone() {
        isDone = true;
    }
    public String getName() {
        return name;
    }

    /**
     * This method instantiates a new, identical copy
     * of an existing task, to be stored in ActionHistory.
     * This ensures that updates to the old task do not
     * affect the copy as well.
     * @param task The task being duplicated.
     * @return The newly-instantiated duplicate of the original task.
     */
    public static Task duplicate(Task task) {
        switch (task.getType()) {
        case "T":
            return new ToDo(task.getName());
        case "D":
            return new Deadline(task.getName(), ((Deadline) task).getInput());
        case "E":
            return new Event(task.getName(), ((Event) task).getInput());
        default:
            return null;
        }
    }
}