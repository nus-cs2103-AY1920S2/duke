public class Deadline extends Task {

    protected String deadline;

    public Deadline(String taskTitle, String deadline) {
        super(taskTitle);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    public String getDeadline() {
        return deadline;
    }
}
