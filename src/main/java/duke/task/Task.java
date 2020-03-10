package duke.task;

import java.io.Serializable;

public abstract class Task implements Serializable {

    public enum Priority {
        PRIORITY_HIGH("High"),
        PRIORITY_MEDIUM("Medium"),
        PRIORITY_LOW("Low"),
        PRIORITY_DEFAULT("");

        private String name;

        private Priority(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name;
        }

        public String getIcon() {
            String icon = "[";
            switch (name) {
            case "High":
                icon += "*";
                // Fallthrough
            case "Medium":
                icon += "*";
                // Fallthrough
            case "Low":
                icon += "*";
                break;
            default:
                return "";
            }

            return icon + "]";
        }

        public static Priority getEnumByString(String value) {
            for (Priority p : Priority.values()) {
                if (p.name.toLowerCase().equals(value.toLowerCase())) {
                    return p;
                }
            }

            return null;
        }
    }

    public enum TaskType {
        TASK_TYPE_TODO,
        TASK_TYPE_EVENT,
        TASK_TYPE_DEADLINE
    }

    protected String description;
    protected boolean isDone;
    protected Priority priority;

    /**
     * Constructor of the Task.
     *
     * @param description Description of the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.PRIORITY_DEFAULT;
    }

    /**
     * Sets the isDone flag of the task.
     *
     * @param isDone Whether the task should be marked as done or not done.
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void setPriority(Priority newPriority) {
        this.priority = newPriority;
    }

    /**
     * Gets the isDone flag of the task.
     *
     * @return true if the task is done; false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    protected String getStatusIcon() {
        return "[" + (isDone ? "\u2713" : "\u2718") + "]"; //return [tick] or [X] symbols
    }

    protected abstract String getTypeIcon();

    protected abstract TaskType getTaskType();

    @Override
    public String toString() {
        return priority.getIcon() + getTypeIcon() + getStatusIcon() + " " + description;
    }
}
