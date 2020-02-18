package e0148811.duke;

public abstract class Task {
    static String DONE = "  Done  ";
    static String NOT_DONE = "Not Done";
    private boolean isDone;
    private String task;
    private PriorityLevel level;

    public Task(boolean isDone, String description, PriorityLevel level) {
        this.isDone = isDone;
        task = description;
        this.level = level;
    }

    public void setHighPriority() {
        level = PriorityLevel.HIGH;
    }

    public void setTopPriority() {
        level = PriorityLevel.TOP;
    }

    public void setNormalPriority() {
        level = PriorityLevel.NORMAL;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String priorityIndication = " ";
        switch (level) {
        case NORMAL:
            priorityIndication = "[    ordinary    ] ";
            break;
        case HIGH:
            priorityIndication = "[   important!   ] ";
            break;
        case TOP:
            priorityIndication = "[very important!!] ";
        }
        if (isDone) {
            return "[" + Task.DONE + "]" + priorityIndication + task;
        } else {
            return "[" + Task.NOT_DONE + "]" + priorityIndication + task;
        }
    }

    public String toSimplerString() {
        String priorityIndication = " ";
        switch (level) {
        case NORMAL:
            priorityIndication = "!";
            break;
        case HIGH:
            priorityIndication = "!!";
            break;
        case TOP:
            priorityIndication = "!!!";
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
}
