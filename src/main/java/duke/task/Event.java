package duke.task;

public class Event extends Task {
    protected final String time;

    public Event(String description, String time) {
        super(description, false);
        this.time = time;
    }

    public Event(String description, String time, boolean isCompleted) {
        super(description, isCompleted);
        this.time = time;
    }

    @Override
    public Event complete() {
        return new Event(description, time, true);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), time);
    }
}
