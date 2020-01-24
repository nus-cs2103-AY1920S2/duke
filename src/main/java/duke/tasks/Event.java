package duke.tasks;

public class Event extends Task {
    protected String dateTime;

    public Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String toSaveable() {
        return String.format("event\n%s\n%s\n%s", name, dateTime, isDone);
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", getStatus(), name, dateTime);
    }
}