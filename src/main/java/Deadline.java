public class Deadline extends Task {
    private String deadline;
    public Deadline(String task, String deadline) {
        super(task);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D]%s(by: %s)", super.toString(), this.deadline);
    }

}
