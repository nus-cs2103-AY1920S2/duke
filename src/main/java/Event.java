public class Event extends Task {

    protected String date;
    protected String startTime;
    protected String endTime;

    public Event(String description, String date, String startTime, String endTime) {
        super(description);
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date + " " + startTime + "-" + endTime + ")";
    }
}
