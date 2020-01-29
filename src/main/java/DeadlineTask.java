public class DeadlineTask extends Task {
    private Date deadline;

    public DeadlineTask(String description, Date deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toStorage() {
        return String.format("D,%s,%b,%s", this.description, this.isDone, this.deadline.withFormat(Date.INPUT_FORMAT));
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", this.getStatusIcon(), this.description, this.deadline.toString());
    }
}