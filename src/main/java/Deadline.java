public class Deadline extends Task {
    protected String deadline;

    Deadline(String unparsed) {
        String[] split = unparsed.split("/by");
        this.description = split[0].trim();
        this.deadline = split[1].trim();
        super.TYPE = TaskType.DEADLINE;
    }

    Deadline(String status, String description, String deadline) {
        super(status, description);
        super.TYPE = TaskType.DEADLINE;
        this.deadline = deadline;
    }

    public String getTaskTime() {
        return deadline;
    }

    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
