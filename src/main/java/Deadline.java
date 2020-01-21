public class Deadline extends Task{
    protected String dL;
    public Deadline(String description, String dL) {
        super(description);
        this.dL = dL;
    }
    public String toString() {
        return ("[D] [" + this.getStatusIcon() + "] " + this.description + " (by: " + this.dL + ")");
    }
}
