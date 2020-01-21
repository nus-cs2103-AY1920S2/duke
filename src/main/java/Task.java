import java.util.*;

public class Task {
    protected String task;
    protected boolean done; //✘

    public Task(String task) {
        this.task = task;
        this.done = false;
    }

    public void setDone() {
        this.done = true; //✓
    }

    public String getStatusIcon() {
        return (done ? "\u2713" : "\u2718");  //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.task;
    }
}
