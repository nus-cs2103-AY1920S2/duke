public class Event extends Task{
    //tasks that start at a specific time and ends at a specific time
    // e.g., team project meeting on 2/10/2019 2-4pm
    protected String time;

    public Event(String description, String time) {
        super(description);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + time + ")";
    }

}
