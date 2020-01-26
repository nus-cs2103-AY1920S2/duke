package task;

public class Deadline extends Task {
    protected String dueDate;

    public Deadline(String identifier, String dueDate) {
        super(identifier);
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Deadline: " + super.toString() + " (please complete by " + dueDate + ")";
    }
}
