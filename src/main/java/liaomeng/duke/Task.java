package liaomeng.duke;

/**
 * An abstract class representing a task.
 */
public abstract class Task {
    static String DONE = "    Done   ";
    static String NOT_DONE = "Not Done";
    private boolean isDone;
    private String task;
    private PriorityLevel level;

    /**
     * Create a task.
     *
     * @param isDone boolean indicating whether the task is marked as done.
     * @param description string description of the task.
     * @param level priority level of the task.
     */
    public Task(boolean isDone, String description, PriorityLevel level) {
        this.isDone = isDone;
        task = description;
        this.level = level;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String priorityIndication;
        switch (level) {
        case LOW:
            priorityIndication = "[  not important ] ";
            break;
        case HIGH:
            priorityIndication = "[     important!    ] ";
            break;
        case TOP:
            priorityIndication = "[very important!!] ";
            break;
        default:
            priorityIndication = "[      ordinary      ] ";
            break;
        }
        if (isDone) {
            return "[" + Task.DONE + "]" + priorityIndication + task;
        } else {
            return "[" + Task.NOT_DONE + "]" + priorityIndication + task;
        }
    }

    /**
     * Returns a string representation of the task, which will be written
     * into the file that stores tasks contained in the task list.
     */
    public String toSimplerString() {
        String priorityIndication;
        switch (level) {
        case LOW:
            priorityIndication = "l";
            break;
        case HIGH:
            priorityIndication = "h";
            break;
        case TOP:
            priorityIndication = "t";
            break;
        default:
            priorityIndication = "n";
            break;
        }
        String isDone;
        if (this.isDone) {
            isDone = "T";
        } else {
            isDone = "F";
        }
        return isDone + "//" + priorityIndication + "//" + getDescription();
    }

    public String getDescription() {
        return task;
    }

    public PriorityLevel getPriorityLevel() {
        return level;
    }

    public void setPriority(PriorityLevel level) {
        this.level = level;
    }
}
