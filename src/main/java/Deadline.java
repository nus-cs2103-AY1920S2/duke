public class Deadline extends Task {
    private String deadline;
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadline);
    }

    @Override
    public String toFormatString() {
        return super.getStatus() ? String.format("D | 1 | %s | %s", super.getTask(), this.deadline)
                : String.format("D | 0 | %s | %s", super.getTask(), this.deadline);
    }
}