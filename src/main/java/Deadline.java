public class Deadline extends Task {
    //tasks that need to be done before a specific date/time
    // e.g., submit report by 11/10/2019 5pm

    protected String time;
    private TaskType type = TaskType.DEADLINE;

    public Deadline(String description, String time) {
        super(description, time);
        this.time = time;
    }

    public TaskType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + time + ")";
    }
}
