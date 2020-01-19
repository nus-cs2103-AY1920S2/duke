class Deadline extends Task {
    private final String deadline;

    Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    private Deadline(String description, String deadline, boolean isDone) {
        super(description, isDone);
        this.deadline = deadline;
    }

    @Override
    public Deadline complete() {
        return new Deadline(description, deadline, true);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), deadline);
    }
}
