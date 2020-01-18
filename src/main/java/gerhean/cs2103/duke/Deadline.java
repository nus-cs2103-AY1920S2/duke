package gerhean.cs2103.duke;

public class Deadline extends Task {
    private String time;

    protected Deadline(String description, String time) {
        super(description);
        this.time = time;
        this.taskType = TaskType.DEADLINE;
    }

    //    public String getTime() {
    //        return (this.time);
    //    }

    @Override
    public String toString() {
        return (this.description + " (by: " + this.time + ")");
    }
}

