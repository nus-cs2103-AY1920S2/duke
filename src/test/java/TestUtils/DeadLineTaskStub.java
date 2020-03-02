package testutils;

import model.DeadLineTask;

public class DeadLineTaskStub extends DeadLineTask {
    private static final String DEFAULT_DESCRIPTION = "form a project group";
    private static final String DEFAULT_DEADLINE = "week 3 tutorial";

    private String description;
    //set the at as a string as the LocalDateTime object is only tested in task object.
    private String by;

    public DeadLineTaskStub() {
        description = DEFAULT_DESCRIPTION;
        by = DEFAULT_DEADLINE;
    }

    public DeadLineTaskStub withDescription(String description) {
        this.description = description;
        return this;
    }

    public DeadLineTaskStub withTime(String by) {
        this.by = by;
        return this;
    }

    @Override
    public String toString() {
        return description + " by " + by;
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
    }
}
