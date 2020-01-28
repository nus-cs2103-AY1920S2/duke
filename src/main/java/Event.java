
public class Event extends Task {
    protected String deadline;


    public Event(String description, String tasktype, String deadline) {
        super(description, tasktype);
        this.deadline = deadline;
    }

    @Override
    public String getDeadline() {
        return deadline;
    }

}