public class Event extends Task {
    private String time;
    protected final String taskType = "Deadline";

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return(this.time);
    }
}