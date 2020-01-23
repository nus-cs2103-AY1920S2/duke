public class Event extends Task {
    protected String dayTime;

    public Event(String description, String dayTime) {
        super(description);
        this.dayTime = dayTime;
    }

    public String getDayTime() {
        return dayTime;
    }
}
