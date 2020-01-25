public class Deadline extends Task{
    private String byDate = "";

    public Deadline(String description, String byDate) {
        super(description, false);
        this.byDate = byDate;
    }

    public Deadline(String description, boolean isDone, String byDate) {
        super(description, isDone);
        this.byDate = byDate;
    }

    @Override
    public String getSaveRepresentation() {
        return "D|||" + getIsDone() + "|||" + getDescription() + "|||" + byDate + "\n";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (by: %s)", "D", (getIsDone() ? "\u2713" : "\u2718"), getDescription(), byDate);
    }
}