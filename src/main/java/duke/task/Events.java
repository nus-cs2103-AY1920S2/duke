package duke.task;

import duke.utils.Parser;

import java.util.Date;

public class Events extends ExecutableTask {

    private boolean done = false;
    private String name;
    private Date time;

    public Events(String name, Date time) {
        this.name = name;
        this.time = time;
    }

    @Override
    public boolean isDone() {
        return done;
    }

    @Override
    public String getTaskName() {
        return name;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    public Date getTaskTime() {
        return time;
    }

    @Override
    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + getDoneString() + " " + getTaskName() + " (at: " +
                Parser.dateToString(getTaskTime()) + ")";
    }
}
