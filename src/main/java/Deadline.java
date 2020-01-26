public class Deadline extends Task {
    protected String deadline;


    public Deadline(String description, String tasktype, String deadline) {
        super(description, tasktype);
        this.deadline = deadline;
    }

    @Override
    public String getDeadline() {
        return " (by: " + deadline + ")";
    }

}