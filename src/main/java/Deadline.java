public class Deadline extends Task{
    String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        totalTasks++;
    }

    public String getType() {
        return "[D]";
    }

    @Override
    public void taskSummary() {
        System.out.println(getType() + getStatusIcon() + " " + description+ "(by: " + by + ")");
    }
}
