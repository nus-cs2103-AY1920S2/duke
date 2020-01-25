public class Deadline extends Task {
    private String date;

    public TType getType() {
        return TType.DEADLINE;
    }

    public String getDate() {
        return date;
    }

    public Deadline(int id, String task, String date) {
        super(id, task);
        this.date = date;
    }

    public Deadline(int id, boolean done, String task, String date) {
        super(id, done, task);
        this.date = date;
    }

    @Override
    public String toString() {
        if (done) return  "[D][✓] " + task + " (by: " + date + ")";
        else return "[D][✗] " + task + " (by: " + date + ")";
    }
}
