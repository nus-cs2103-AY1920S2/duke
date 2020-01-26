public class Deadline extends DateTimeTask {
    public Deadline(String description, String by) {
        super(description, by);
    }

    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.getDateTime() + ")";
    }
}