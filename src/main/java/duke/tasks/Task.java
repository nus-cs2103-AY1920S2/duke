package duke.tasks;

import duke.utils.StringParser;

/**
 * The <code>Task</code> class is an abstract class with a name and a boolean to represent whether
 * it is completed.
 *
 * @author Zhu Yijie
 */
public abstract class Task {
    private String name;
    private boolean isCompleted;
    
    /**
     * Creates a new Task object.
     *
     * @param name The name of the task.
     */
    public Task(String name) {
        assert !name.isEmpty() : "Empty task name.";
        this.name = name;
        this.isCompleted = false;
    }
    
    /**
     * Creates a new Task object with non-default isCompleted.
     *
     * @param name The name of the task.
     * @param isCompleted Whether the task is completed.
     */
    public Task(String name, boolean isCompleted) {
        assert !name.isEmpty() : "Empty task name.";
        this.name = name;
        this.isCompleted = isCompleted;
    }
    
    /**
     * Marks the task as completed.
     */
    public void markAsCompleted() {
        isCompleted = true;
    }
    
    /**
     * Marks the task as incomplete.
     */
    public void markAsIncomplete() {
        isCompleted = false;
    }
    
    ////////////////////////////// PRINTING TASK
    
    /**
     * Returns a string representing the name of the task.
     *
     * @return The name of the task.
     */
    protected String getName() {
        return name;
    }
    
    /**
     * Returns string for whether the task is completed or not.
     *
     * @return The string representing the whether the task is completed or not.
     */
    protected String getStatus() {
        return isCompleted ? "/" : " ";
    }
    
    /**
     * Returns string for whether the task is completed or not in binary.
     *
     * @return The string representing the whether the task is completed or not.
     */
    protected String getStatusInBinary() {
        return isCompleted ? "1" : "0";
    }
    
    /**
     * Returns status icon for whether the task is completed or not.
     *
     * @return The icon string representing the whether the task is completed or not.
     */
    protected String getStatusIcon() {
        return StringParser.addSquareBracket(getStatus());
    }
    
    /**
     * Returns identifier string for the type of task.
     *
     * @return The string representing the task type.
     */
    protected abstract String getIdentifier();
    
    /**
     * Returns identifier icon for the type of task.
     *
     * @return The icon string representing the task type.
     */
    protected abstract String getIdentifierIcon();
    
    /**
     * Returns a string formatting the task for saving in a file.
     *
     * @return The string representing the task.
     */
    public abstract String format();
    
    @Override
    public abstract String toString();
    
    ////////////////////////////// EQUALITY CHECKS
    
    /**
     * Checks if provided task name matches this task's name partially, ignoring case.
     *
     * @param taskName Task name to check.
     * @return True if taskName is contained in this task's name, false otherwise.
     */
    public boolean matchPartial(String taskName) {
        return name.toLowerCase().contains(taskName.toLowerCase());
    }
    
    /**
     * Checks if provided task name matches this task's name fully, ignoring case.
     *
     * @param taskName Task name to check.
     * @return True if taskName is equivalent to this task's name, false otherwise.
     */
    public boolean equals(String taskName) {
        return name.equalsIgnoreCase(taskName);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Task) {
            Task task = (Task) obj;
            return task.equals(this.name) && task.getIdentifier().equals(this.getIdentifier());
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        int nameHash = getName().toLowerCase().hashCode() * 277;
        int identifierHash = getIdentifier().hashCode() * 23;
        return nameHash + identifierHash;
    }
}
