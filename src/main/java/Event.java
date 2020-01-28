public class Event extends Task {
    protected String deadline;


    public Event(String description, String tasktype, String deadline, String raw_input) {
        super(description, tasktype, raw_input);
        this.deadline = deadline;
    }

    @Override
    public String getDeadline() {
        return " (at: " + deadline + ")";
    }

}