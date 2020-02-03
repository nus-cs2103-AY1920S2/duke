package duke.task;

import duke.utils.Parser;

import java.util.Date;

public class Deadlines extends ExecutableTask {

    private boolean done = false;
    private String name;
    private Date time;

    public Deadlines(String name, Date time) {
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
        return "D";
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
        return "[" + getTaskType() + "]" + getDoneString() + " " + getTaskName() + " (by: " +
                Parser.dateToString(getTaskTime()) + ")";
    }
}
