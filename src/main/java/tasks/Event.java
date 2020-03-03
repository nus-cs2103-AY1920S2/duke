package tasks;

public class Event extends Task {
    private String eventAt;

    public Event(String taskDetails, String eventAt) {
        super(taskDetails);
        this.eventAt = eventAt;
    }

    @Override
    public String writeToFile() {
        return "D /" + super.writeToFile() + " / " + eventAt;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " " + eventAt;
    }
}
