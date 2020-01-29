package duke.task;

import duke.*;


public class Task {
    public String description;
    public boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "Y" : "X"); //return Y or X symbols
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "["+ getStatusIcon() + "] " + getDescription();
    }
}
