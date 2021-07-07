package testutils;

import model.EventTask;

public class EventTaskStub extends EventTask {
    private static final String DEFAULT_DESCRIPTION = "cs2103 lecture";
    private static final String DEFAULT_TIME = "week 3 tutorial";

    private String description;
    //set the at as a string as the LocalDateTime object is only tested in task object.
    private String at;

    /**
     * Constructs an event stub.
     */
    public EventTaskStub() {
        description = DEFAULT_DESCRIPTION;
        at = DEFAULT_TIME;
        isDone = false;
    }

    public EventTaskStub withDescription(String description) {
        this.description = description;
        return this;
    }

    public EventTaskStub withTime(String time) {
        this.at = time;
        return this;
    }

    @Override
    public String toString() {
        return description + " at " + at;
    }

    @Override
    public void markAsDone() {
        this.isDone = true;
    }
}
