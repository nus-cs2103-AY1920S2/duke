public class Deadline extends Task{
    private String byDate = "";

    public Deadline(String description, String byDate) {
        super(description);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", "D", (getIsDone() ? "\u2713" : "\u2718"), getDescription(), byDate);
    }
}