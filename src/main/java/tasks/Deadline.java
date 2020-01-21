package tasks;

public class Deadline extends Task {
    private String finishBy;

    public Deadline(String description, String finishBy) {
        super(description);
        this.finishBy = finishBy;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.finishBy + ")";
    }
}
