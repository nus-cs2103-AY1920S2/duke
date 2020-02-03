package duke.task;

import java.util.Date;

public class Todos extends ExecutableTask {

    private boolean done = false;
    private String name;

    public Todos(String name) {
        this.name = name;
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
        return "T";
    }

    @Override
    public Date getTaskTime() {
        return null;

    }

    @Override
    public void markAsDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + getDoneString() + " " + getTaskName();
    }
}
