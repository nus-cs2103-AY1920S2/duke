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

    @Override
    public String toSaveFormat() {
        char d = super.getIsDone() ? '1' : '0';
        return "D | " + d + " | " + super.getDescription() + " | " + this.finishBy;
    }
}
