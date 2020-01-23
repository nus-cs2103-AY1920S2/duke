public class Event extends Task {
    private String date;

    public Event(String name, String date) {
        super(name);
        this.date = date.substring(date.indexOf(" "));
    }

    private Event(String name, String date, boolean isDone) {
        super(name, isDone);
        this.date = date;
    }

    public String toString() {
        return "[E]" + super.toString() + "(at:" + date + ")";
    }

    public Event setDone() {
        return new Event(this.name, this.date, true);
    }
}
