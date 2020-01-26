package main.java;

import main.java.Task;


public class DeadLineTask extends Task {
    protected String by;

    public DeadLineTask(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
