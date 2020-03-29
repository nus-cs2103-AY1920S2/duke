/**
 * Represents a task
 * @author kerwei
 * @version 1.0
 */

public class Task {
    public String type;
    public String description;
    public boolean isDone;
    public String dateTime;

    public Task(String type, String description, String dateTime) {
        this.type = type;
        this.description = description;
        this.dateTime = dateTime;
        this.isDone = false;
    }

    /**
     * Returns the status of task
     * @return Status of task with tick or cross
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    @Override
    public String toString(){
        String result = "[" + getStatusIcon() + "] " + description;
        return result;
    }

}
