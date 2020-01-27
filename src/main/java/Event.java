public class Event extends Task {

    public Event(String taskName, String dateTime) {
        super(taskName.trim(), dateTime.trim());
        this.taskType = "E";
    }

    // to initialise save data
    public Event(String taskName, boolean isDone, String dateTime) {
        super(taskName.trim(), isDone, dateTime.trim());
        this.taskType = "E";
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTime + ")";
    }

}
