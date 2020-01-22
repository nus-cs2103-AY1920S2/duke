public class Deadline extends Task{
    protected String byDate;

    public Deadline(String command, String byDate) {
        super(command);
        this.byDate = byDate;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " +  byDate + ")";
    }
}