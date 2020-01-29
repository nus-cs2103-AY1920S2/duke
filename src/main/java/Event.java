public class Event extends Task{
    //tasks that start at a specific time and ends at a specific time
    // e.g., team project meeting on 2/10/2019 2-4pm
    protected String time;
    private TaskType type = TaskType.EVENT;

    public Event(String description, String time) {
        super(description, time);
        this.time = time;
    }

    public TaskType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }

}
