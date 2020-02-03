public class Task {
    protected String description;
    protected boolean isDone;
    protected String time;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, String time) {
        this.description = description;
        this.isDone = false;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public boolean isTaskDone() { return isDone; }

    public String getTime() { return time; }

    public String getTypeName() {
        return "Task";
    }
    @Override
    public String toString() {
        String timeOptional = (time.equals(""))? "" : ", " + time;
        return "[" + getStatusIcon() + "]" + " " + description + timeOptional;
    }

    public String toStringFile() {
        int isDoneInt = (isDone)? 1 : 0;
        String timeOptional = (time.equals(""))? "" : " | " + time;
        return isDoneInt + " | " + description + timeOptional;
    }
}