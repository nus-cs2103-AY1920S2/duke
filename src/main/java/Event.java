import java.time.LocalDate;

public class Event extends DateTimeTask {

    public Event(String description, LocalDate at) {
        super(description, at);
    }

    public String getType() {
        return "E";
    }

    @Override
    public Task getCopy() {
        Event e = new Event(this.description, this.dateTime);
        if (this.isDone()) {
            e.setDone();
        }
        return e;
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.getDateTime() + ")";
    }
}
