public class Deadline extends Task {
    private String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    Deadline(String description, String deadline, boolean isCompleted) {
        super(description, isCompleted);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
