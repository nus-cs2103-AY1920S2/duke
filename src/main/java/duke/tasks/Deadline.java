package duke.tasks;

public class Deadline extends Task {
    protected String dateTime;

    public Deadline(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", getStatus(), name, dateTime);
    }
}