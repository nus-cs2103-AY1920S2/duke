public class Event extends Task {
    public final String dateTime;

    public Event(String name, String dateTime) {
        super(name);
        this.dateTime = dateTime;
    }

    public Event(String name, boolean completed, String dateTime) {
        super(name, completed);
        this.dateTime = dateTime;
    }

    @Override
    public Event complete() {
        return new Event(this.name, true, this.dateTime);
    }

    @Override
    public String toString() {
        String doneCheck = "[✓] ";
        String notDoneCheck = "[✗] ";

        if (completed) {
            return "[E]" + doneCheck + this.name + " (at: " + dateTime + ")";
        } else {
            return "[E]" + notDoneCheck + this.name + " (at: " + dateTime + ")";
        }
    }
}