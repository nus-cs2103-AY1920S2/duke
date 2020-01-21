public class Event extends Task {
    private String date;

    public Event(int id, String task, String date) {
        super(id, task);
        this.date = date;
    }

    @Override
    public String toString() {
        if (done) return  "[E][✓] " + task + " (at: " + date + ")";
        else return "[E][✗] " + task + " (at: " + date + ")";
    }
}
