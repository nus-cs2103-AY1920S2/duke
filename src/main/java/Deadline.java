public class Deadline extends Task {
    protected String deadlineTime;

    public Deadline(String description, String deadlineTime) {
        super(description);
        this.deadlineTime = deadlineTime;
    }

    @Override
    public String toString() {
        String formattedDeadlineTime = " (by: " + this.deadlineTime + ")";
        return "[D]" + super.toString() + formattedDeadlineTime;
    }
}
