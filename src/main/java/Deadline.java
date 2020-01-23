public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getFullDescription() {
        return "[D]" + super.getDescriptionWithIsDone() + " (by: " + this.by + ")";
    }
}