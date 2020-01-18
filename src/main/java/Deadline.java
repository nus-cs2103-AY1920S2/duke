public class Deadline extends Task {

    protected String dateTime;

    public Deadline(String taskName, String dateTime) {
        super(taskName.trim());
        this.dateTime = dateTime.trim();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTime + ")";
    }

}
