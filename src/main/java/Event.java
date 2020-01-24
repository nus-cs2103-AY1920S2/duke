public class Event extends Task {
    private String date;

    public TType getType() {
        return TType.EVENT;
    }

    public String getDate() {
        return date;
    }

    public Event(int id, String task, String date) {
        super(id, task);
        this.date = date;
    }

    public Event(int id, boolean done, String task, String date) {
        super(id, done, task);
        this.date = date;
    }

    @Override
    public String toString() {
        if (done) return  "[E][✓] " + task + " (at: " + date + ")";
        else return "[E][✗] " + task + " (at: " + date + ")";
    }
}
