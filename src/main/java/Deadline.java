public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String getFullDescription() {
        return "[D]" + super.getDescriptionWithIsDone() + " (by: " + this.by + ")";
    }
}