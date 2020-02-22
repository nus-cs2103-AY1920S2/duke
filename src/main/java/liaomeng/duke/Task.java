package liaomeng.duke;

public abstract class Task {
    static String DONE = "    Done   ";
    static String NOT_DONE = "Not Done";
    private boolean isDone;
    private String task;
    private PriorityLevel level;

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
