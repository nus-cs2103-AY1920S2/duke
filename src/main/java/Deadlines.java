public class Deadlines extends Task{
    protected String by;

    public Deadlines(String description, Boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
        System.out.println(this);
    }

    public String getDeadline() {
        return this.by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")";
    }
}
