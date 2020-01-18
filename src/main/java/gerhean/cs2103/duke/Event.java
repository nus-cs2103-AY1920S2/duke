package gerhean.cs2103.duke;

public class Event extends Task {
    private String time;

    protected Event(String description, String time) {
        super(description);
        this.time = time;
        this.taskType = TaskType.Event;
    }

    //    public String getTime() {
    //        return (this.time);
    //    }

    @Override
    public String toString() {
        return (this.description + " (at: " + this.time + ")");
    }
}